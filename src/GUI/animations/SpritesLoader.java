package GUI.animations;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import engine.services.FightCharService;
import GUI.Ressource;
import GUI.controller.StageController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpritesLoader {

	private List<HashMap<AnimationType, Animation>> animations;
	
	public static HashMap<AnimationType,Animation> loadAnimations(String chara)
	{
		HashMap<AnimationType,Animation> animations = new HashMap<>();
		
		Properties prop = null;
		for(AnimationType type : AnimationType.values())
		{
			if(prop == null)
				prop = new Properties();
			
			prop.clear();
			
			Animation animation = new Animation(type,false);
			
			int read_Y = Sprite.ref_position_X_left;
			int read_X = Sprite.ref_position_Y;
			int read_duration = 4;
			int animation_length=0;
			boolean read_loop = true;
			
			try {			
				// load a properties file
				//System.out.println(Ressource.sprites+chara+"/"+type.toString()+"_config.txt");
				prop.load(SpritesLoader.class.getResourceAsStream(Ressource.sprites+chara+"/"+type.toString()+"_config.txt"));
			} catch (Exception ex) {System.out.println("Property reading error :" + ex.getMessage()+" ["+Ressource.sprites+chara+"/"+type.toString()+"_config.txt"+"]"); prop = null;}
			
			if(prop!=null)
			{
				read_loop = new Boolean(prop.getProperty("loop"));
				
				String s = prop.getProperty("animation_length");
				
				if(s!=null && !s.isEmpty())
				{
					animation_length = new Integer(s);
					
					for(int i=0;i<animation_length;i++)
					{
						String sprite_name = "sprite_"+i;
						
						//System.out.println(sprite_name);
						
						String[] values = prop.get(sprite_name).toString().split("#");
						
						read_X = new Integer(values[0]);
						read_Y = new Integer(values[1]);
						read_duration = new Integer(values[2]);
						
						String path = getPathOfSprite(chara,animation,i);
						Image img = null;
						try {
							img = new Image(SpritesLoader.class.getResource(path).toURI().toString());
						} catch (URISyntaxException e) {e.printStackTrace();}
						
						animation.addSprite(new Sprite(read_X, read_Y, read_duration, img,false));
					}	
					
					animation.setLoop(read_loop);
				}

			}
			
			animations.put(type,animation);	
		}
		
		return animations;
	}
	
	public SpritesLoader(FightCharService chara[])
	{			
		this.animations = new ArrayList<HashMap<AnimationType, Animation>>();
		this.initAnimations(chara);	
	}
	
	private static String getPathOfSprite(String chara,Animation animation,int frame)
	{
		String path = Ressource.sprites+chara+"/";
		path += chara+"_"+animation.toString()+"_"+frame+".png";
		
		return path;
	}
	
	public void initAnimations(FightCharService chara[])
	{	
		for(int joueur=0;joueur<chara.length;joueur++)
		{
			HashMap<AnimationType,Animation> hashmapAnimation = SpritesLoader.loadAnimations(chara[joueur].getName());			
			this.animations.add(hashmapAnimation);
		}
	}
	
	public Animation getAnimation(int joueur,AnimationType type)
	{
		return this.animations.get(joueur).get(type);
	}
}
