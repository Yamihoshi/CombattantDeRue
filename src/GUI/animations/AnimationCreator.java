package GUI.animations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import GUI.Ressource;
import engine.components.character.Personnage;

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
			int read_X = 0;
			try {			
				// load a properties file
				System.out.println(Ressource.sprites+chara+"/"+type.toString()+"_axis.txt");
				prop.load(getClass().getResourceAsStream(Ressource.sprites+chara+"/"+type.toString()+"_axis.txt"));

				// get the property value and print it out
				
				read_Y = new Integer(prop.getProperty("Y"));
				read_X = new Integer(prop.getProperty("X"));

			} catch (Exception ex) {System.out.println("Property reading error :" + ex.getMessage()+" ["+Ressource.sprites+chara+"/"+type.toString()+"_axis.txt"+"]");}
			
			this.animations.put(type,new Animation(type,null,read_Y,read_X,false));
		}
	}
	
	public HashMap<AnimationType,Animation> getAnimations()
	{
		return this.animations;
	}
}
