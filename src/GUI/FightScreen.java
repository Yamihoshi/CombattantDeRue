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
import engine.components.character.Personnage;
import engine.components.player.Commande;
import engine.services.FightCharService;
import game.StreetFighterGame;
import javafx.animation.AnimationTimer;
import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class FightScreen{

	private AnchorPane pane;
	
	private StageController controller;
	
	private SpritesManager sprites_manager;
	
	private StreetFighterGame game;
	
	private KeyCode[] currentKey;
	private int[] nbTimeKeyPressed;
	private KeyBinder keyBinder;
	private AnimationBinder animationBinder;
	
	private boolean framePerFrame;
	
	private boolean[] faceRight;
	
	public FightScreen(StreetFighterGame game) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/stage.fxml"));
		
		this.pane = (AnchorPane) loader.load();
		
		this.controller = loader.<StageController>getController();  
		
		this.game = game;
		
		this.currentKey = new KeyCode[2];
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
            	else
            	{
            		for(int i=0;i<currentKey.length;i++)
            		{
            			if(keyBinder.isKeyOfPlayer(i,event.getCode()))
            			{
            				nbTimeKeyPressed[i]++;
            				
                			if(currentKey[i] == null && nbTimeKeyPressed[i]<=1)
                			{
                				currentKey[i] = event.getCode();
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
            	
        		for(int i=0;i<currentKey.length;i++)
        			if((currentKey[i]==null || currentKey[i] == event.getCode())  && keyBinder.isKeyOfPlayer(i,event.getCode()))
        			{
        				nbTimeKeyPressed[i]=0;
        				currentKey[i] = null;
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
		new AnimationTimer()
        {
        	int frameCount = 1;
        	long lasttimeFPS = System.nanoTime();
        	long lasttimeFPS_keys = System.nanoTime();
        	int[] animation_frame = new int[2];
        	int[] frameAnimationCount = new int[2];
        	FightCharService J1 = game.getEngine().getCharacter(0);
        	FightCharService J2 = game.getEngine().getCharacter(1);
        	
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
            		lasttimeFPS = currenttimeNano;
            		
            		frameCount = (frameCount+1)%61;
            		
            		if(frameCount==60)
            		{
            			System.out.println((currenttimeNano-firstRun)/1000000000.0);
            			firstRun = System.nanoTime();
            		}
            		
            		if(frameCount==0)
            			frameCount=1;
            		
            		controller.setFrame(frameCount);            		
                    
                    Commande[] commandes = new Commande[2];
                    
                    for(int i=0;i<commandes.length;i++)
                    {    
                    	animation_frame[i] = sprites_manager.getCurrentSprite(i).getDuration();
                    	frameAnimationCount[i]++;
                    	
                    	FightCharService chara = game.getEngine().getCharacter(i);
                        
                    	if(!chara.isFaceRight())
                    		controller.flip(i);
                    	
                    	if(chara.isTeching())
                    	{
                    		currentKey[i]=null;
                    	}

                    	commandes[i] = keyBinder.getAction(i, currentKey[i]);
                    	
                    	/*if(commandes[i]==Commande.PUNCH && nbTimeKeyPressed[i]>1)
                        		commandes[i]=Commande.NEUTRAL;*/
                    	
	    	           	if(!chara.isBlockStunned()
	    	           	&& ! chara.isHitStunned()
	    	           	&& ! chara.isTeching()
	    	           	&& sprites_manager.getAnimationPlayed(i).getType()!=animationBinder.getAnimation(commandes[i]))
	    	           	{
	    	           		sprites_manager.playAnimation(i,animationBinder.getAnimation(commandes[i]));
	    	           		Sprite sprite = sprites_manager.getCurrentSprite(i);
	                		controller.updateSprite(i,sprite);
	                		frameAnimationCount[i]=0;
	                		if(chara.isFaceRight())
	                			controller.updateSpriteAlignement(i,sprites_manager.getAnimationPlayed(i).getTranslate_X(),sprites_manager.getAnimationPlayed(i).getTranslate_Y());
	                		else
	                			controller.updateSpriteAlignement(i,sprites_manager.getAnimationPlayed(i).getTranslate_X_reversed(),sprites_manager.getAnimationPlayed(i).getTranslate_Y());

	    	           	}
                    }
                    
                    for(int i=0;i<game.getPlayers().length;i++)
                    {
                    	FightCharService chara = game.getEngine().getCharacter(i);
                    	
                    	if(chara.isBlockStunned() || chara.isHitStunned())
                    	{
                    		
                    	}
                    	else if((sprites_manager.getAnimationPlayed(i).isLooped()||game.getEngine().getCharacter(i).isTeching()) && frameAnimationCount[i]==animation_frame[i])
	                    {
	                    	frameAnimationCount[i]=0;
	                    	sprites_manager.stepAnimation(i);
	                		Sprite sprite = sprites_manager.getCurrentSprite(i);
	                		controller.updateSprite(i,sprite);
	                    }
                    }
                    
                    game.getEngine().step(commandes[0], commandes[1]);
                    controller.updatePosition(J1.getCharBox(), J2.getCharBox());
                    controller.updateHitbox(J1, J2);
                 }          	             
            }
        }.start();
	}
}
