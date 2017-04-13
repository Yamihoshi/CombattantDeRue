package GUI;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class CharacterSelection{

	private AnchorPane pane;
	
	public CharacterSelection() throws IOException
	{
		this.pane = FXMLLoader.load(getClass().getResource("/character_selection.fxml"));
		this.pane.setId("character_selection");
	}
	
	public AnchorPane getPane()
	{
		return this.pane;
	}
	
	
}
