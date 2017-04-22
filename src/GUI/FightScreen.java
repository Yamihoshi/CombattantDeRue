package GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import GUI.animations.AnimationType;
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
	
	KeyCode currentKey = null;
	
	public FightScreen(StreetFighterGame game) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/stage.fxml"));
		
		this.pane = (AnchorPane) loader.load();
		
		this.controller = loader.<StageController>getController();  
		
		this.game = game;
		
		/*if(chara_J1==CharacterType.RANDOM)
			chara_J1 = this.randomizeCharacter();
		
		if(chara_J2==CharacterType.RANDOM)
			chara_J2 = this.randomizeCharacter();*/
    	
    	List<ImageView> sprites = new ArrayList<ImageView>(); 
    	sprites.add(this.controller.getCharacterOfJ1());
    	sprites.add(this.controller.getCharacterOfJ2());
		
    	this.sprites_manager = new SpritesManager(sprites, this.game.getEngine().getCharacters());
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
            	else
            	{
            		if(currentKey == null)
            			currentKey = event.getCode();
            		event.consume();
            	}
            }
        });
		
		this.pane.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
            	
            	if(currentKey==event.getCode())
            		currentKey=null;
            	event.consume();
            }
        });
		
	}
	
	public void launchTimer()
	{
		new AnimationTimer()
        {
        	int frameCount = 0;
        	long lasttimeFPS = System.nanoTime();
        	long lasttimeFPS_keys = System.nanoTime();
        	FightCharService J1 = game.getEngine().getCharacter(0);
        	FightCharService J2 = game.getEngine().getCharacter(1);
            @Override
            public void handle(long arg0)
            {
            	frameCount++;
            	
            	long currenttimeNano = System.nanoTime();
            	
            	if (currenttimeNano > lasttimeFPS + 1000000000 * StageController.frameTime)
            	{
            		frameCount = 0;
                    lasttimeFPS = currenttimeNano;
	                    
                    if(currentKey==KeyCode.LEFT)
	                {
	    	           	game.getEngine().step(Commande.NEUTRAL, Commande.LEFT);
	    	           	if(sprites_manager.getAnimationPlayed(1)!=AnimationType.WALK_FORWARD)
	    	           		sprites_manager.playAnimation(1,AnimationType.WALK_FORWARD);
	                }
	                else if(currentKey==KeyCode.RIGHT)
	                {
	    	           	game.getEngine().step(Commande.NEUTRAL, Commande.RIGHT);
	    	           	if(sprites_manager.getAnimationPlayed(1)!=AnimationType.WALK_FORWARD)
	    	           		sprites_manager.playAnimation(1,AnimationType.WALK_FORWARD);
	                }
	                else if(currentKey==KeyCode.Q)
	                {
	    	           	game.getEngine().step(Commande.LEFT, Commande.NEUTRAL);
	    	           	if(sprites_manager.getAnimationPlayed(0)!=AnimationType.WALK_FORWARD)
	    	           		sprites_manager.playAnimation(0,AnimationType.WALK_FORWARD);
	                }
	                else if(currentKey==KeyCode.D)
	                {
	    	           	game.getEngine().step(Commande.RIGHT, Commande.NEUTRAL);
	    	           	if(sprites_manager.getAnimationPlayed(0)!=AnimationType.WALK_FORWARD)
	    	           		sprites_manager.playAnimation(0,AnimationType.WALK_FORWARD);
	                }
	                else if(currentKey==null)
	                {
	                	game.getEngine().step(Commande.NEUTRAL, Commande.NEUTRAL);
	                	if(sprites_manager.getAnimationPlayed(0)!=AnimationType.STAND)
            			{
                			sprites_manager.playAnimation(0,AnimationType.STAND);
            			}
	                }
                 }
	           	controller.update(J1.getCharBox(), J2.getCharBox());

            }
        }.start();
	}
}
