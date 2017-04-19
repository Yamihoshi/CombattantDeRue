package GUI.animations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import GUI.Ressource;
import engine.components.character.CharacterType;

public class AnimationCreator {

	HashMap<AnimationType,Animation> animations;
	
	public AnimationCreator(String chara)
	{
		this.animations = new HashMap<AnimationType,Animation>();
		this.createAnimations(chara);
	}
	
	public void createAnimations(String chara)
	{
		Properties prop = new Properties();
		
		for(AnimationType type : AnimationType.values())
		{
			int read_Y = 0;
			try {			
				// load a properties file
				System.out.println(Ressource.sprites+chara+"/"+type.toString()+"_axis.txt");
				prop.load(getClass().getResourceAsStream(Ressource.sprites+chara+"/"+type.toString()+"_axis.txt"));

				// get the property value and print it out
				
				read_Y = new Integer(prop.getProperty("Y"));

			} catch (Exception ex) {ex.printStackTrace();}
			
			this.animations.put(type,new Animation(type,null,read_Y));
		}
	}
	
	public HashMap<AnimationType,Animation> getAnimations()
	{
		return this.animations;
	}
}
