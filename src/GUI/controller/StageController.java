package GUI.controller;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import GUI.animations.AnimationType;
import GUI.animations.SpritesManager;
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
import javafx.scene.shape.Rectangle;
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
	
	@FXML 
    private Rectangle hitbox_J1;
	
	@FXML 
    private Rectangle hitbox_J2;
	
	private boolean showHitbox;
  	
    @Override  
    public void initialize(URL location, ResourceBundle resources) {  
        
    	character_J1.setCache(true);
    	character_J1.setCacheHint(CacheHint.SPEED);
    	character_J2.setCache(true);
    	character_J2.setCacheHint(CacheHint.SPEED);	
    	
    	this.showHitbox = true;
    	this.toggleHitBox();
    }
    
    public ImageView getCharacterOfJ1()
    {
    	return this.character_J1;
    }
    
    public ImageView getCharacterOfJ2()
    {
    	return this.character_J2;
    }

    public void toggleHitBox()
    {
    	this.showHitbox = ! this.showHitbox;
    	
    	hitbox_J1.setVisible(showHitbox);
    	hitbox_J2.setVisible(showHitbox);
    }
    
    public void setHitbox(boolean hit)
    {
    	if(hit)
    	{
    		//set Color to RED
    	}
    	else
    	{
    		//set Color to GREEN
    	}
    }
    
    public void updatePosition_J1(int x)
    {
    	this.character_J1.setLayoutX(x);
    	this.hitbox_J1.setLayoutX(x);
    }
}
