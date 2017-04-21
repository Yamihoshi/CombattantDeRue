package engine.components.character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import GUI.MainWindow;
import engine.components.player.Player;
import engine.contracts.CharacterContract;
import engine.contracts.EngineContract;
import engine.impl.EngineImpl;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.FightCharService;
import javafx.application.Application;

public class CharacterFabrique {

	   public static void initPerso(Personnage personnage, EngineService engine, int player){
		boolean faceRight = (player == 0)? true : false;
		   try {
			Properties p = CharacterFabrique.load("ressource//character//"+personnage.name());
			engine.getPlayer(player).getCharacter().init(personnage, new Integer(p.getProperty("life", "100")), new Integer(p.getProperty("vitesse", "1")), engine, faceRight);
			int x = 5 + ((faceRight) ? 0 : engine.getSpace());
			engine.getPlayer(player).getCharacter().getCharBox().init(x, 1, new Integer(p.getProperty("hauteur", "100")), new Integer(p.getProperty("largeur", "100")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
	   
	   /**
	    * Charge la liste des propriétés contenu dans le fichier spécifié
	    *
	    * @param filename le fichier contenant les propriétés
	    * @return un objet Properties contenant les propriétés du fichier
	    */
	   public static Properties load(String filename) throws IOException, FileNotFoundException{
	      Properties properties = new Properties();
	      FileInputStream input = new FileInputStream(filename);
	      try{
	         properties.load(input);
	         return properties;
	      }
	      finally{
	         input.close();
	      }
	   }
}
