package GUI.animations;

import javafx.animation.Timeline;

public class Animation {
	
	public static final int ref_position_Y = 430;
	public static final int ref_position_X_left = 186;
	
	private AnimationType type;
	private int position_Y;
	private int position_X;
	private int sprites_duration[];
	private Timeline timeline;
	
	public Animation(AnimationType type, int[] sprites_duration, int position_Y, int position_X,boolean reversed)
	{
		this.type = type;
		this.position_Y = ref_position_Y - position_Y;
		if(!reversed)
			this.position_X = ref_position_X_left - position_X;
		else
			this.position_X = position_X - ref_position_X_left;
		this.sprites_duration = sprites_duration;
		
		System.out.println(type.toString() +" : "+this.position_X);
	}
	
	public AnimationType getType()
	{
		return this.type;
	}
	
	public int getPosition_Y()
	{
		return this.position_Y;
	}
	
	public int getPosition_X()
	{
		return this.position_X;
	}
	
	public int[] sprites_duration()
	{
		return this.sprites_duration;
	}
	
	public int sprite_duration(int frame)
	{
		return this.sprites_duration[frame];
	}
	
	public String toString()
	{
		return this.type.toString();
	}
	
	public Timeline getTimeLine()
	{
		return this.timeline;
	}
	
	public void setTimeLine(Timeline timeline)
	{
		this.timeline = timeline;
	}
	
	public void play()
	{
		this.timeline.play();
	}
	
	public void stop()
	{
		this.timeline.stop();
	}
}
