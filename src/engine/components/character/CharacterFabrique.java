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

	   public static void init(Player player, Personnage personnage, EngineService engine, boolean faceRight){
		   FightCharService fc = player.getCharacter();
		   try {
			Properties p = CharacterFabrique.load("ressource//character//"+personnage.name());
			fc.init(personnage, new Integer(p.getProperty("life", "100")), new Integer(p.getProperty("vitesse", "1")), engine, faceRight);
			fc.getCharBox().init(1, 1, new Integer(p.getProperty("hauteur", "100")), new Integer(p.getProperty("largeur", "100")));
			
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
