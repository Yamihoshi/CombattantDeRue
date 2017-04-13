package GUI.controller;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    	try {
    		InputStream fontStream = getClass().getResourceAsStream("/fonts/SSF4.ttf");
    		Font bgFont = Font.loadFont(fontStream, 36);
    		font.setFont(bgFont);
			character_display_J1.setImage(new Image(getClass().getResource("/Character_Display/Ryu_J2.png").toURI().toString()));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  


}
