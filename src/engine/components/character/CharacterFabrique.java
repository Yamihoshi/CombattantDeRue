package engine.components.character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import GUI.MainWindow;
import engine.components.hitbox.HitboxImpl;
import engine.components.player.Commande;
import engine.components.player.Player;
import engine.contracts.CharacterContract;
import engine.contracts.EngineContract;
import engine.impl.EngineImpl;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.TechService;
import javafx.application.Application;

public class CharacterFabrique {

	   public static void init(Player player, Personnage personnage, EngineService engine, boolean faceRight){
		   FightCharService fc = player.getCharacter();
		   try {
			Properties p = CharacterFabrique.load("ressource//character//"+personnage.name());
			fc.init(personnage, new Integer(p.getProperty("life", "100")), new Integer(p.getProperty("vitesse", "1")), engine, faceRight);
			fc.getCharBox().init(1, 1, new Integer(p.getProperty("hauteur", "100")), new Integer(p.getProperty("largeur", "100")));
			TechService punch = new Technique();
			HitboxService punch_hitbox = new HitboxImpl();
			punch.init(new Integer(p.getProperty("punch_damage")), new Integer(p.getProperty("punch_hit_stun")),
					new Integer(p.getProperty("punch_block_stun")), new Integer(p.getProperty("punch_start_up_frame")), 
					new Integer(p.getProperty("punch_hit_frame")), new Integer(p.getProperty("punch_recovery_frame")), 
					new Integer(p.getProperty("punch_debut_x")), new Integer(p.getProperty("punch_debut_y")), 
					new Integer(p.getProperty("punch_width")), new Integer(p.getProperty("punch_height")));
			fc.getTech().put(Commande.PUNCH, punch);
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
