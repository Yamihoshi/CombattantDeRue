package GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import GUI.animations.AnimationType;
import GUI.animations.Sprite;
import GUI.animations.SpritesManager;
import GUI.controller.StageController;
import engine.components.character.CharacterImpl;
import engine.components.character.Personnage;
import engine.components.player.Commande;
import engine.components.player.IA;
import engine.services.FightCharService;
import game.StreetFighterGame;
import javafx.animation.AnimationTimer;
import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FightScreen{

	private AnchorPane pane;
	
	private StageController controller;
	
	private SpritesManager sprites_manager;
	
	private StreetFighterGame game;
	
	private List<LinkedList<KeyCode>> currentKey;
	private List<LinkedList<KeyCode>> releasedNeededKey;
	private int[] nbTimeKeyPressed;
	private KeyBinder keyBinder;
	private AnimationBinder animationBinder;
	private KeyCode[] code = new KeyCode[2];
	
	private boolean framePerFrame;
	
	private boolean[] faceRight;
	
	private AnimationTimer timer;
	
	public FightScreen(StreetFighterGame game) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/stage.fxml"));
		
		this.pane = (AnchorPane) loader.load();
		
		this.controller = loader.<StageController>getController();  
		
		this.game = game;
		
		this.currentKey = new ArrayList<LinkedList<KeyCode>>();
		this.currentKey.add(new LinkedList<KeyCode>());
		this.currentKey.add(new LinkedList<KeyCode>());
		
		this.releasedNeededKey = new ArrayList<LinkedList<KeyCode>>();
		this.releasedNeededKey.add(new LinkedList<KeyCode>());
		this.releasedNeededKey.add(new LinkedList<KeyCode>());
		
		this.nbTimeKeyPressed = new int[2];
		this.keyBinder = new KeyBinder();
		this.animationBinder = new AnimationBinder();
		this.framePerFrame = false;
		
    	this.sprites_manager = new SpritesManager(this.game.getEngine().getCharacters());
    	this.sprites_manager.playAnimation(0,AnimationType.STAND);
    	this.sprites_manager.playAnimation(1,AnimationType.STAND);
    	
    	launchTimer();
	}
	
	public AnchorPane getPane()
	{
		return this.pane;
	}
	
	public Personnage randomizeCharacter()
	{
		Random rand = new Random();
		int index = rand.nextInt(Personnage.values().length);
		
		return Personnage.values()[index];
	}
	
	public void addEventHandler()
	{
		this.pane.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			
            @Override
            public void handle(KeyEvent event) {

            	if(event.getCode()==KeyCode.F2)
            	{
            		controller.toggleHitBox();
            		event.consume();
            	}
            	else if(event.getCode()==KeyCode.F3)
            	{
            		toggleFramePerFrame();
            		event.consume();
            	}
            	else if(event.getCode()==KeyCode.BACK_SPACE)
            	{
            		try {
            			timer.stop();
            			CharacterSelection screen = new CharacterSelection(game);
            			Scene scene = new Scene(screen.getPane());
                    	
                    	Stage stage = (Stage) pane.getScene().getWindow();
                    	
                    	screen.addEventHandler();
                    	stage.setScene(scene);
            		} catch (IOException e) {
            			e.printStackTrace();
            		}
            	}
            	else
            	{
            		for(int i=0;i<currentKey.size();i++)
            		{
            			if(keyBinder.isKeyOfPlayer(i,event.getCode()))
            			{            				
                			if(!currentKey.get(i).contains(event.getCode()))
                			{
                				currentKey.get(i).addLast(event.getCode());
                				//releasedKey.get(i).remove(event.getCode());
                			}
            			}
            		}
            		//event.consume();
            	}
            }
        });
		
		this.pane.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
            	
        		for(int i=0;i<currentKey.size();i++)
        		{
        			if(keyBinder.isKeyOfPlayer(i,event.getCode()))
        			{            				
            			if(currentKey.get(i).contains(event.getCode()))
            			{
            				currentKey.get(i).remove(event.getCode());
            			}
            			
            			if(releasedNeededKey.get(i).contains(event.getCode()))
            			{
            				releasedNeededKey.get(i).remove(event.getCode());
            			}
        			}
        		}
            	//event.consume();
            }
        });
		
	}
	
	public void toggleFramePerFrame()
	{
		this.framePerFrame=!this.framePerFrame;
	}
	
	public void launchTimer()
	{
		this.timer=new AnimationTimer()
        {
        	int frameCount = 1;
        	long lasttimeFPS = System.nanoTime();
        	long lasttimeFPS_keys = System.nanoTime();
        	int[] animation_frame = new int[2];
        	int[] frameAnimationCount = new int[2];
        	FightCharService J1 = game.getEngine().getCharacter(0);
        	FightCharService J2 = game.getEngine().getCharacter(1);
        	Commande[] commandes = new Commande[2];
        	
        	double timePerFrame = 1000000000 * StageController.frameTime;
        	
        	long firstRun = System.nanoTime();
        	
			@Override
            public void handle(long arg0)
            {                	
				
            	long currenttimeNano = System.nanoTime();
            	
            	if(framePerFrame==true)
            		timePerFrame = 1000000000;
            	else
            		timePerFrame = 1000000000 * StageController.frameTime;
            	
            	if ((framePerFrame && currenttimeNano - lasttimeFPS >= timePerFrame) || !framePerFrame)
            	{   
            		/* Gestion des Frames */
            		lasttimeFPS = currenttimeNano;
            		
            		frameCount = (frameCount+1)%61;
            		
            		if(frameCount==60)
            		{
            			firstRun = System.nanoTime();
            		}
            		
            		if(frameCount==0)
            			frameCount=1;
            		
            		controller.setFrame(frameCount);     
            		
            		/*-----------------------------*/
            		
                    for(int i=0;i<commandes.length;i++)
                    {    
                    	animation_frame[i] = sprites_manager.getCurrentSprite(i).getDuration();
                    	frameAnimationCount[i]++;
                    	
                    	FightCharService chara = game.getEngine().getCharacter(i);
                    	

                    	
                    	@SuppressWarnings("unchecked")
						LinkedList<KeyCode> keys = (LinkedList<KeyCode>) currentKey.get(i).clone();
                    	
                    	if(commandes[i]==null || keys.isEmpty())
                    		commandes[i]=Commande.NEUTRAL;
                    	
                    	while(!keys.isEmpty())
                    	{                    		
                    		//System.out.println(keys);
                    		
                    		KeyCode k = keys.removeFirst();
                    		
                    		Commande cmd = keyBinder.getAction(i,k);
                    		
                    		if(releasedNeededKey.get(i).contains(k))
                    			commandes[i]=Commande.NEUTRAL;
                    		else if(commandes[i]==Commande.NEUTRAL)
	                    		{
	                    			code[i]=k;
	                    			commandes[i]=cmd;
	                    			//currentKey.get(i).removeFirst();
	                    		}
                    		else if(commandes[i]==Commande.DOWN)
                    		{                    			
                    			if(cmd==Commande.LEFT)
                    				commandes[i]=Commande.DOWNLEFT;
                    			else if(cmd==Commande.RIGHT)
                    				commandes[i]=Commande.DOWNRIGHT;
                    		}
                    		else if(commandes[i]==Commande.DOWNLEFT ||commandes[i]==Commande.DOWNRIGHT)
                    		{   
                    			if(cmd==Commande.LEFT)
                    				commandes[i]=Commande.DOWNLEFT;
                    			else if(cmd==Commande.RIGHT)
                    				commandes[i]=Commande.DOWNRIGHT;
                    			else
                    				commandes[i]=Commande.DOWN;
                    		}
                    	}
                    	
            			if(!currentKey.get(i).contains(code[i]))
            				commandes[i]=Commande.NEUTRAL;
                    	}
                    
        			if(game.isVersusIA())
        				commandes[1]=IA.getRandomCommande();
                    
                    /* STEP ENGINE*/
                    System.out.println("CMD :"+commandes[1]);
                    game.getEngine().step(commandes[0], commandes[1]);
                    controller.updatePosition(J1.getCharBox(), J2.getCharBox());
                    controller.updateHitbox(J1, J2);
                    /*-------------*/
                    
                   controller.updateCombo(game.getEngine().getCharacter(0).getCombo(),game.getEngine().getCharacter(1).getCombo());
                    
                    for(int i=0;i<game.getPlayers().length;i++)
                    {
                    	FightCharService chara = game.getEngine().getCharacter(i);
                    	AnimationType new_animation = sprites_manager.getAnimationPlayed(i).getType();
                    	
                    	/* UPDATE ANIMATION */
	    	           	if(!game.getEngine().isGameOver())
	    	           	{
	    	           		if(chara.isBlockStunned())
	    	           		{
	    	           		//	System.out.println("Block Stunned");	
	    	           		}
	    	           		else if (chara.isHitStunned())
	    	           		{
	    	           			//Anim hit
	    	           		//	System.out.println("Hit Stunned");
	    	           			new_animation=AnimationType.HIT;
	    	           		}
	    	           		else if (chara.isTeching())
	    	           		{	    	           			
	    	           			if(sprites_manager.getAnimationPlayed(i).getType()==AnimationType.STAND)
	    	           			{
	    	           				new_animation = animationBinder.getAnimation(commandes[i]);
	    	           				if(!releasedNeededKey.contains(code[i]))
	    	           					releasedNeededKey.get(i).add(code[i]);
	    	           			}
	    	           		}
	    	           		else
	    	           		{
	    	           			new_animation = animationBinder.getAnimation(commandes[i]);
	                        	if(!chara.isFaceRight())
	                        		controller.flip(i);
	    	           		}
	    	           		
    	           			if(sprites_manager.getAnimationPlayed(i).getType()!=new_animation)
	    	           		{
		    	           		sprites_manager.playAnimation(i,new_animation);
		    	           		Sprite sprite = sprites_manager.getCurrentSprite(i);
		                		controller.updateSprite(i,sprite);
		                		frameAnimationCount[i]=0;
		                		if(chara.isFaceRight())
		                			controller.updateSpriteAlignement(i,sprites_manager.getAnimationPlayed(i).getTranslate_X(),sprites_manager.getAnimationPlayed(i).getTranslate_Y());
		                		else
		                			controller.updateSpriteAlignement(i,sprites_manager.getAnimationPlayed(i).getTranslate_X_reversed(),sprites_manager.getAnimationPlayed(i).getTranslate_Y());

	    	           		}
	    	           			
	    	           	}
                    	/*---------------------*/
                    	
                    	
	    	           	/* UPDATE SPRITE */
                    	if(chara.isBlockStunned())
                    	{
                    		
                    	}
                    	else if(chara.isHitStunned())
                    	{
                    		
                    	}
                    	else if((sprites_manager.getAnimationPlayed(i).isLooped()||game.getEngine().getCharacter(i).isTeching()) && frameAnimationCount[i]==animation_frame[i])
	                    {
	                    	frameAnimationCount[i]=0;
	                    	sprites_manager.stepAnimation(i);
	                		Sprite sprite = sprites_manager.getCurrentSprite(i);
	                		controller.updateSprite(i,sprite);
	                    }
                    	/*--------------------------*/
                    	
                    	controller.setLifeBarValue(i,(double)chara.getLife()/chara.getMaxLife());
                    }
                    
            		if(game.getEngine().isGameOver())
                    {
                    	System.out.println("Terminé");
                    	int winner = 0;
                    	for(int i=1;i<game.getPlayers().length;i++)
                    		if(game.getEngine().getCharacter(i).getLife()>game.getEngine().getCharacter(winner).getLife())
                    			winner = i;
                    	
                    	launchGameOverTimer(winner);
                    	this.stop();
                    }
                    
                 }          	             
            }
        };
        
        timer.start();
	}

	public void launchGameOverTimer(int winner) {

		System.out.println("WINNER IS J"+(winner+1));
		
		this.timer=new AnimationTimer()
        {

			int[] animation_frame = new int[2];
        	int[] frameAnimationCount = new int[2];
			
			@Override
			public void handle(long now) {
			
				frameAnimationCount[winner]++;
				animation_frame[winner]=sprites_manager.getCurrentSprite(winner).getDuration()*3;
				
				
				if(sprites_manager.getAnimationPlayed(winner).getCurrentIndex()==sprites_manager.getAnimationPlayed(winner).getLength()-1)
				{
					
				}
				else if(frameAnimationCount[winner]==animation_frame[winner])
                {
                	frameAnimationCount[winner]=0;
                	sprites_manager.stepAnimation(winner);
            		Sprite sprite = sprites_manager.getCurrentSprite(winner);
            		controller.updateSprite(winner,sprite);
                }
			}
        
        };
		
		this.timer.start();
		
		/* Play winner/Looser animation ? */
		
		/*Attendre 10s ->retour écran perso */
		
	}
}
