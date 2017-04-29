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
        	int frameCount = 0;
        	long lasttimeFPS = System.nanoTime();
        	long lasttimeFPS_keys = System.nanoTime();
        	FightCharService J1 = game.getEngine().getCharacter(0);
        	FightCharService J2 = game.getEngine().getCharacter(1);
        	
        	double timePerFrame = 1000000000 * StageController.frameTime;
        	
            @Override
            public void handle(long arg0)
            {
            	frameCount++;
            	
            	long currenttimeNano = System.nanoTime();
            	
            	if(framePerFrame==true)
            		timePerFrame = 1000000000;
            	else
            		timePerFrame = 1000000000 * StageController.frameTime;
            	
            	if (currenttimeNano > lasttimeFPS + timePerFrame)
            	{
            		frameCount = 0;
                    lasttimeFPS = currenttimeNano;
                    
                    Commande[] commandes = new Commande[2];
                    commandes[0] = keyBinder.getAction(0, currentKey[0]);
                    commandes[1] = keyBinder.getAction(1, currentKey[1]);
                    
                    for(int i=0;i<commandes.length;i++)
                    {
	    	           	if(sprites_manager.getAnimationPlayed(i)!=animationBinder.getAnimation(commandes[i]))
	    	           		sprites_manager.playAnimation(i,animationBinder.getAnimation(commandes[i]));
                    }
                    
                    game.getEngine().step(commandes[0], commandes[1]);
                    controller.update(J1.getCharBox(), J2.getCharBox());
                 }
            }
        }.start();
	}
}
