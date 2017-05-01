package GUI.animations;

import javafx.scene.image.Image;

public class Sprite {

	public static final double ratio = 65.0/100.0;
	
	public static final int ref_position_Y = (int) (ratio*430);
	public static final int ref_position_X_left = (int) (ratio*173);
	
	private double translate_X;
	private double translate_Y;
	private int duration;
	
	private Image image;
	
	public Sprite(int posX, int posY,int duration, Image img,boolean reversed)
	{
		this.translate_X = ratio*posX;
		this.translate_Y = ratio*posY;
		this.duration = duration;
		
		this.image = img;
		
		this.translate_Y = ref_position_Y - translate_Y;
		if(!reversed)
			this.translate_X = ref_position_X_left - translate_X;
		else
			this.translate_X = translate_X - ref_position_X_left;
	}
	
	public int getDuration()
	{
		return this.duration;
	}
	
	public double getTranslate_X()
	{
		return this.translate_X;
	}
	
	public double getTranslate_Y()
	{
		return this.translate_Y;
	}
	
	public Image getImage()
	{
		return this.image;
	}
}
