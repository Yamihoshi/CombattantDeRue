package engine.components.character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import GUI.Ressource;
import engine.components.hitbox.HitboxImpl;
import engine.components.hitbox.HitboxState;
import engine.components.player.Commande;
import engine.components.player.Player;
import engine.contracts.TechniqueContract;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.TechService;
import game.StreetFighterGame;


public class CharacterFabrique {

	   public static void init(Player player, Personnage personnage, EngineService engine, boolean faceRight){
		   FightCharService fc = player.getCharacter();
		   try {
			Properties p = CharacterFabrique.load("ressource"+Ressource.character+personnage.toString());
			fc.init(personnage, new Integer(p.getProperty("life", "100")), new Integer(p.getProperty("vitesse", "1")), engine, faceRight);
			initHitbox(fc, p);
			initTechnique(fc, p);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   }
	   
	   public static void initTechnique(FightCharService fc, Properties p){
			TechService punch = new TechniqueContract(new Technique());
			TechService kick = new TechniqueContract(new Technique());

			punch.init(new Integer(p.getProperty("punch_damage")), new Integer(p.getProperty("punch_hit_stun")),
					new Integer(p.getProperty("punch_block_stun")), new Integer(p.getProperty("punch_start_up_frame")), 
					new Integer(p.getProperty("punch_hit_frame")), new Integer(p.getProperty("punch_recovery_frame")), 
					new Integer(p.getProperty("punch_debut_x")), new Integer(p.getProperty("punch_debut_y")), 
					new Integer(p.getProperty("punch_width")), new Integer(p.getProperty("punch_height")));
			fc.getTech().put(Commande.PUNCH, punch);

			
			kick.init(new Integer(p.getProperty("light_kick_damage")),
					new Integer(p.getProperty("light_kick_hit_stun")),
					new Integer(p.getProperty("light_kick_block_stun")), new Integer(p.getProperty("light_kick_start_up_frame")), 
					new Integer(p.getProperty("light_kick_hit_frame")), new Integer(p.getProperty("light_kick_recovery_frame")), 
					new Integer(p.getProperty("light_kick_debut_x")), new Integer(p.getProperty("light_kick_debut_y")), 
					new Integer(p.getProperty("light_kick_width")), new Integer(p.getProperty("light_kick_height")));
			fc.getTech().put(Commande.KICK, kick);
	   }
	   
	   public static void initHitbox(FightCharService fc, Properties p){
			int hauteur_standing = new Integer(p.getProperty("standing_height"));
			int largeur_standing =  new Integer(p.getProperty("standing_width"));
			AtomicInteger x = new AtomicInteger(1);
			int y = StreetFighterGame.HEIGHT - hauteur_standing;
			fc.bindHitbox(createHitbox(x, y, hauteur_standing, largeur_standing), HitboxState.STANDING);
			
			
			
			int hauteur_crouching = new Integer(p.getProperty("crouching_height"));
			int largeur_crouching =  new Integer(p.getProperty("crouching_width"));
			y = StreetFighterGame.HEIGHT - hauteur_crouching;
			System.out.println(y);
			fc.bindHitbox(createHitbox(x, y, hauteur_crouching, largeur_crouching), HitboxState.CROUCHING);

			
			
			
	   }
	   
	   public static HitboxService createHitbox(AtomicInteger x, int y, int hauteur, int largeur){
			HitboxService hitbox = new HitboxImpl();
			hitbox.init(x, y, hauteur, largeur);
			return hitbox;
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
