package GUI;

import java.io.IOException;
import java.net.URISyntaxException;

import GUI.controller.CharacterSelectionController;
import engine.components.character.CharacterFabrique;
import engine.components.character.Personnage;
import game.StreetFighterGame;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CharacterSelection{

	private AnchorPane pane;
	
	private CharacterSelectionController controller;
	
    private int index_chara_J1 = 1;
    private int index_chara_J2 = 2;
    
    private StreetFighterGame game;
	
	public CharacterSelection(StreetFighterGame game) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/character_selection.fxml"));
		
		this.pane = (AnchorPane) loader.load();
		
		this.controller = loader.<CharacterSelectionController>getController();
		
		this.game = game;
	}
	
	public void addEventHandler()
	{
		this.pane.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
            	
            	if(event.getCode()==KeyCode.ENTER)
            		switch_to_fight_screen();
            	else if(event.getCode()==KeyCode.LEFT)
            		previousCharacter(1);
            	else if (event.getCode()==KeyCode.RIGHT)
            		nextCharacter(1);
            	
            }
        });
	}
	
	public void switch_to_fight_screen()
	{

		
		CharacterFabrique.init(this.game.getPlayers()[0], Personnage.values()[this.index_chara_J1], this.game.getEngine(), true);
		CharacterFabrique.init(this.game.getPlayers()[1], Personnage.values()[this.index_chara_J2], this.game.getEngine(), false);

		this.game.getEngine().init(720, 1280, 1000 ,this.game.getPlayers()[0], this.game.getPlayers()[1]);
		
		
    	FightScreen fight;
		try {
			fight = new FightScreen(this.game);
			Scene scene = new Scene(fight.getPane());
        	
        	Stage stage = (Stage) pane.getScene().getWindow();
        	
        	fight.addEventHandler();
        	stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public AnchorPane getPane()
	{
		return this.pane;
	}
	
    public void nextCharacter(int joueur)
    {    	
    	this.index_chara_J1 = (this.index_chara_J1+1)%Personnage.values().length;
    	
    	this.controller.updateImageCharacterDisplay(1,this.index_chara_J1);
    }
    
    public void previousCharacter(int joueur)
    {
    	this.index_chara_J1--;
    	
    	if(this.index_chara_J1<0)
    		this.index_chara_J1=Personnage.values().length-1;
    	
    	this.controller.updateImageCharacterDisplay(1,this.index_chara_J1);
    }
	
	
}
