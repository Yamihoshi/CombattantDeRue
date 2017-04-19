package GUI.animations;

import javafx.animation.Timeline;

public class Animation {
	
	public static final int ref_position_Y = 430;
	
	private AnimationType type;
	private int position_Y;
	private int sprites_duration[];
	private Timeline timeline;
	
	public Animation(AnimationType type, int[] sprites_duration, int position_Y)
	{
		this.type = type;
		this.position_Y = ref_position_Y - position_Y;
		this.sprites_duration = sprites_duration;
	}
	
	public AnimationType getType()
	{
		return this.type;
	}
	
	public int getPosition_Y()
	{
		return this.position_Y;
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
