package GUI;

import java.io.IOException;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class FightScreen{

	private AnchorPane pane;
	
	public FightScreen() throws IOException
	{
		this.pane = FXMLLoader.load(getClass().getResource("/stage.fxml"));
	}
	
	public AnchorPane getPane()
	{
		return this.pane;
	}
	
}
