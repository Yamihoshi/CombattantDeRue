package GUI.animations;

public class Sprite {

	public static final int ref_position_Y = 430;
	public static final int ref_position_X_left = 173;
	
	private int translate_X;
	private int translate_Y;
	private int duration;
	
	public Sprite(int posX, int posY,int duration, boolean reversed)
	{
		this.translate_X = posX;
		this.translate_Y = posY;
		this.duration = duration;
		
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
	
	public int getTranslate_X()
	{
		return this.translate_X;
	}
	
	public int getTranslate_Y()
	{
		return this.translate_Y;
	}
}
