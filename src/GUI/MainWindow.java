package GUI;

import java.net.URISyntaxException;

import game.StreetFighterGame;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainWindow extends Application{

	boolean debug_mode = true;
	private Stage stage;
	private MediaPlayer mediaPlayer = null;
	private StreetFighterGame game;
	 
	 @Override
	 public void start(Stage stageParam) throws Exception {
		
		 this.stage = stageParam;
		 this.game = new StreetFighterGame(false);
		 
		 this.stage.setResizable(false);
		 
		 stage.setWidth(1280+5);
		 stage.setHeight(720+25);
		 
		 stage.setTitle("Street Fighter");
		 
		 StackPane root = new StackPane();
		 root.setId("title_screen");
		 Scene scene = new Scene(root);
		 
		 try
		 {
			 Media media = new Media(getClass().getResource(Ressource.music+"Guile_Theme.mp3").toURI().toString());
			 mediaPlayer = new MediaPlayer(media);
			 mediaPlayer.setVolume(0.3);
			 mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			 mediaPlayer.play();
		 }catch(Exception e){System.out.println("Can't load mediaPlayer");mediaPlayer=null;}
		 
		 stage.getIcons().add(new Image(getClass().getResource(Ressource.icon).toURI().toString()));
		 scene.getStylesheets().addAll(getClass().getResource("/style.css").toURI().toString());
		 
		 stage.setScene(scene);
		 
		 scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            public void handle(KeyEvent ke) {
	               //System.out.println("Key Pressed: " + ke.getCode());
	                try {
	                	switch_to_Character_Selection();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });
		 
		 stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
		        if (KeyCode.ESCAPE == event.getCode()) {
		            stage.close();
		        }
		    });
		 
		 stage.show();
	 }
	 
	 public void switch_to_Character_Selection() throws Exception
	 {
		 /*if(mediaPlayer!=null)
			 mediaPlayer.stop();*/
		 
		 CharacterSelection selection = new CharacterSelection(this.game);
		 
		 Scene scene = new Scene(selection.getPane());
		 
		 selection.addEventHandler();

		 stage.setScene(scene);
	 }
	
}
