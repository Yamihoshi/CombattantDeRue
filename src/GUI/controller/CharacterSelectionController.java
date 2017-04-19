package GUI.controller;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import engine.components.character.CharacterType;
import game.StreetFighterGame;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class CharacterSelectionController implements Initializable {

	@FXML 
    private AnchorPane root;
	
	@FXML 
    private ImageView character_display_J1; 
	
    @FXML 
    private ImageView character_display_J2; 
    
    @FXML
    private Label font;
    
    @Override  
    public void initialize(URL location, ResourceBundle resources) {  
    	
    		/*InputStream fontStream = getClass().getResourceAsStream("/fonts/SSF4.ttf");
    		Font bgFont = Font.loadFont(fontStream, 36);
    		font.setFont(bgFont);*/
    		
    	updateImageCharacterDisplay(1,1);
    	updateImageCharacterDisplay(2,2);    	
    }
    
    public void updateImageCharacterDisplay(int joueur,int index_chara)
    {
    	try{	
	    	if(joueur == 1)
	    		character_display_J1.setImage(new Image(getClass().getResource("/Character_Display/"+CharacterType.values()[index_chara]+"_J1.png").toURI().toString()));
	    	else
	    		character_display_J2.setImage(new Image(getClass().getResource("/Character_Display/"+CharacterType.values()[index_chara]+"_J2.png").toURI().toString()));
    	}catch(Exception e){};
    }

}
