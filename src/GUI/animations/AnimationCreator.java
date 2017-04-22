package GUI.animations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import GUI.Ressource;
import engine.components.character.Personnage;

public class AnimationCreator {
	
	public AnimationCreator()
	{
		
	}
	
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
			
			try {			
				// load a properties file
				System.out.println(Ressource.sprites+chara+"/"+type.toString()+"_config.txt");
				prop.load(AnimationCreator.class.getResourceAsStream(Ressource.sprites+chara+"/"+type.toString()+"_config.txt"));
			} catch (Exception ex) {System.out.println("Property reading error :" + ex.getMessage()+" ["+Ressource.sprites+chara+"/"+type.toString()+"_config.txt"+"]"); prop = null;}
			
			if(prop!=null)
			{
				String s = prop.getProperty("animation_length");
				
				if(s!=null && !s.isEmpty())
				{
					animation_length = new Integer(s);
					
					for(int i=0;i<animation_length;i++)
					{
						String sprite_name = "sprite_"+i;
						
						System.out.println(sprite_name);
						
						String[] values = prop.get(sprite_name).toString().split("#");
						
						read_X = new Integer(values[0]);
						read_Y = new Integer(values[1]);
						read_duration = new Integer(values[2]);
						
						animation.addSprite(new Sprite(read_X, read_Y, read_duration, false));
					}
				}

			}
			
			animations.put(type,animation);	
		}
		
		return animations;
	}
	
	public void createAnimation()
	{
		/* Code pour générer les .config*/
	}
}
