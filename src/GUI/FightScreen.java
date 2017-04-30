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
	
	private LinkedList<KeyCode> combo;
	
	private KeyCode[] currentKey;
	private KeyBinder keyBinder;
	private AnimationBinder animationBinder;
	
	private boolean framePerFrame;
	
	public FightScreen(StreetFighterGame game) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/stage.fxml"));
		
		this.pane = (AnchorPane) loader.load();
		
		this.controller = loader.<StageController>getController();  
		
		this.game = game;
		
		this.currentKey = new KeyCode[2];
		this.keyBinder = new KeyBinder();
		this.animationBinder = new AnimationBinder();
		this.framePerFrame = false;
		
    	this.sprites_manager = new SpritesManager(this.game.getEngine().getCharacters());
    	this.sprites_manager.playAnimation(0,AnimationType.STAND);
    	this.sprites_manager.playAnimation(1,AnimationType.STAND);
    	
    	this.combo = new LinkedList<KeyCode>();
    	
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
            			if(currentKey[i] == null && keyBinder.isKeyOfPlayer(i,event.getCode()))
            				currentKey[i] = event.getCode();
            		//event.consume();
            	}
            }
        });
		
		this.pane.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
            	
        		for(int i=0;i<currentKey.length;i++)
        			if(currentKey[i] == event.getCode()  && keyBinder.isKeyOfPlayer(i,event.getCode()))
        				currentKey[i] = null;
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
        	long lasttimeFPS_animation = System.nanoTime();
        	int timeSpriteJ1 = sprites_manager.getCurrentSprite(0).getDuration();
        	FightCharService J1 = game.getEngine().getCharacter(0);
        	FightCharService J2 = game.getEngine().getCharacter(1);
        	
        	double timePerFrame = 1000000000 * StageController.frameTime;
        	
            @Override
            public void handle(long arg0)
            {            	
            	long currenttimeNano = System.nanoTime();
            	
            	if(framePerFrame==true)
            		timePerFrame = 1000000000;
            	else
            		timePerFrame = 1000000000 * StageController.frameTime;
            	
            	if((sprites_manager.getAnimationPlayed(0).isLooped()||game.getEngine().getCharacter(0).isTeching()) &&  currenttimeNano > lasttimeFPS_animation + timeSpriteJ1*timePerFrame)
            	{
            		lasttimeFPS_animation = currenttimeNano;
            		sprites_manager.stepAnimation(0);
            		Sprite sprite = sprites_manager.getCurrentSprite(0);
            		timeSpriteJ1 = sprite.getDuration();
            		controller.updateSprite_J1(sprite);
            	}
            	
            	if (currenttimeNano > lasttimeFPS + timePerFrame)
            	{
            		frameCount = (frameCount+1)%61;
            		if(frameCount==0)
            			frameCount=1;
            		
            		controller.setFrame(frameCount);
                    lasttimeFPS = currenttimeNano;
                    
                    Commande[] commandes = new Commande[2];
                    commandes[0] = keyBinder.getAction(0, currentKey[0]);
                    commandes[1] = keyBinder.getAction(1, currentKey[1]);
                    
                    for(int i=0;i<commandes.length;i++)
                    {
                    	FightCharService chara = game.getEngine().getCharacter(i);
                    	
	    	           	if(!chara.isBlockStunned()
	    	           	&& ! chara.isHitStunned()
	    	           	&& ! chara.isTeching()
	    	           	&& sprites_manager.getAnimationPlayed(i).getType()!=animationBinder.getAnimation(commandes[i]))
	    	           	{
	    	           		lasttimeFPS_animation = currenttimeNano;
	    	           		sprites_manager.playAnimation(i,animationBinder.getAnimation(commandes[i]));
	    	           		Sprite sprite = sprites_manager.getCurrentSprite(0);
	                		controller.updateSprite_J1(sprite);
	                		timeSpriteJ1 = sprites_manager.getCurrentSprite(0).getDuration();
	    	           	}
                    }
                    
                    game.getEngine().step(commandes[0], commandes[1]);
                    controller.updatePosition(J1.getCharBox(), J2.getCharBox());
                 }
            }
        }.start();
	}
}
