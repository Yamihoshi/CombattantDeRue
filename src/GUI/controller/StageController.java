package GUI.controller;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import GUI.AnimationType;
import GUI.SpritesManager;
import engine.components.character.CharacterType;
import javafx.animation.AnimationTimer;
import javafx.beans.property.ObjectProperty;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class StageController implements Initializable {

	public static final double frameTime = 1.0/60.0;
	
	@FXML 
    private AnchorPane root; 
	
	@FXML 
    private ImageView character_J1;
	
	@FXML 
    private ImageView character_J2;
  	
    @Override  
    public void initialize(URL location, ResourceBundle resources) {  
        
    	character_J1.setCache(true);
    	character_J1.setCacheHint(CacheHint.SPEED);
    	character_J2.setCache(true);
    	character_J2.setCacheHint(CacheHint.SPEED);	
    }
    
    public ImageView getCharacterOfJ1()
    {
    	return this.character_J1;
    }
    
    public ImageView getCharacterOfJ2()
    {
    	return this.character_J2;
    }

}
