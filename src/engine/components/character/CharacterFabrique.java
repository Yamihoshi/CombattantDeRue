package engine.components.character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import GUI.MainWindow;
import engine.contracts.CharacterContract;
import engine.contracts.EngineContract;
import engine.impl.EngineImpl;
import engine.services.CharacterService;
import engine.services.EngineService;
import javafx.application.Application;

public class CharacterFabrique {

	   public static CharacterService initPerso(String name, EngineService engine, boolean faceRight){
		   CharacterService chihuahua = new CharacterContract(new CharacterImpl());
		   try {
			Properties p = CharacterFabrique.load("ressource//character//"+name);
			System.out.println(p.keySet());
			chihuahua.init(p.getProperty("nom", "milou"), new Integer(p.getProperty("life", "100")), new Integer(p.getProperty("vitesse", "1")), engine, faceRight);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chihuahua;
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
	   
		public static void main(String[] args)
		{
			System.out.println(CharacterFabrique.initPerso("ryu", new EngineContract(new EngineImpl()), true));
		}
}
