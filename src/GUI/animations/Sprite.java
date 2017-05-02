package GUI.animations;

import javafx.scene.image.Image;

public class Sprite {

	public static final double ratio = 65.0/100.0;
	
	private int duration;
	
	private Image image;
	
	public Sprite(int duration, Image img)
	{
		this.duration = duration;
		
		this.image = img;
	}
	
	public int getDuration()
	{
		return this.duration;
	}
	
	public Image getImage()
	{
		return this.image;
	}
}
