package GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import GUI.animations.AnimationType;
import GUI.animations.SpritesManager;
import GUI.controller.StageController;
import engine.components.character.CharacterType;
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
	
	public FightScreen(CharacterType chara_J1, CharacterType chara_J2) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/stage.fxml"));
		
		this.pane = (AnchorPane) loader.load();
		
		this.controller = loader.<StageController>getController();
		
		if(chara_J1==CharacterType.RANDOM)
			chara_J1 = this.randomizeCharacter();
		
		if(chara_J2==CharacterType.RANDOM)
			chara_J2 = this.randomizeCharacter();
		
    	String[] charas = new String[2];
    	charas[0] = chara_J1.toString();
    	charas[1] = chara_J2.toString();
    	
    	List<ImageView> sprites = new ArrayList<ImageView>(); 
    	sprites.add(this.controller.getCharacterOfJ1());
    	sprites.add(this.controller.getCharacterOfJ2());
		
    	this.sprites_manager = new SpritesManager(sprites,charas);
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
            	
            }
        });
	}
}
