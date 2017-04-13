package GUI.controller;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CharacterSelectionController implements Initializable {

	@FXML 
    private AnchorPane root; 
	
	@FXML 
    private ImageView character_display_J1; 
	
    @FXML 
    private ImageView character_display_J2; 
    
  
    @Override  
    public void initialize(URL location, ResourceBundle resources) {  
    	try {
			character_display_J1.setImage(new Image(getClass().getResource("/Character_Display/Ryu_J2.png").toURI().toString()));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  


}
