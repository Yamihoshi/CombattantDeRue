package GUI.controller;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import GUI.SpritesLoader;
import GUI.SpritesManager;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class StageController implements Initializable {

	@FXML 
    private AnchorPane root; 
	
	@FXML 
    private ImageView character_J1;
  
	private List<KeyEvent> key_event;
	private SpritesManager sprites_manager;
	
    @Override  
    public void initialize(URL location, ResourceBundle resources) {  

    	character_J1.setFocusTraversable(true);
    	
    	character_J1.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
            	keyPressed(event.getCode());
            }
        });
    	
    	this.sprites_manager = new SpritesManager();
    	
		Task<Void> task = new Task<Void>() {
		    @Override 
		    protected Void call() throws Exception{
		        while(true){
		        	if (isCancelled()) {
		                break;
		             }
		        	
		        	refreshFighter();
		        	
		        	try {
		                Thread.sleep(110);
		            } catch (InterruptedException interrupted) {
		                if (isCancelled()) {
		                    updateMessage("Cancelled");
		                    break;
		                }
		            }
		        }
		        return null;
		    }
		};
		
		Thread t = new Thread(task);
		t.setDaemon(true);
		t.start();
    }  
    
	public void refreshFighter()
	{
		this.sprites_manager.stepAnimation();
		character_J1.setImage(this.sprites_manager.getCurrentSprite());
	}
	
	public void keyPressed(KeyCode key)
	{
		System.out.println(key);
	}
	
	public void keyReleased()
	{
		
	}


}
