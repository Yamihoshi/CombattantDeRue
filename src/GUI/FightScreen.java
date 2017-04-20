package GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import GUI.animations.AnimationType;
import GUI.animations.SpritesManager;
import GUI.controller.StageController;
import engine.components.character.CharacterType;
import engine.components.player.Commande;
import game.StreetFighterGame;
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
    	this.sprites_manager.beginAnimation(0);
    	this.sprites_manager.beginAnimation(1);
	}
	
	public AnchorPane getPane()
	{
		return this.pane;
	}
	
	public CharacterType randomizeCharacter()
	{
		Random rand = new Random();
		int index = rand.nextInt(CharacterType.values().length);
		
		return CharacterType.values()[index];
	}
	
	public void addEventHandler()
	{
		this.pane.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
            	if(event.getCode()==KeyCode.F2)
            	{
            		controller.toggleHitBox();
            	}
            	else if(event.getCode()==KeyCode.LEFT)
            	{
            		game.getEngine().getCharacter(0).moveLeft();
            		controller.updatePosition_J1(game.getEngine().getCharacter(0).getPositionX());
            	}
            	else if(event.getCode()==KeyCode.RIGHT)
            	{
            		game.getEngine().step(Commande.RIGHT, Commande.NEUTRAL);
            		controller.updatePosition_J1(game.getEngine().getCharacter(0).getPositionX());
            	}
            	
            }
        });
	}
}
