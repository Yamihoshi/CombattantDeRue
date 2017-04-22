package GUI.animations;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Timeline;

public class Animation {
	
	private AnimationType type;
	private List<Sprite> sprites;
	private Timeline timeline;
	private boolean loop;
	
	public Animation(AnimationType type,boolean reversed)
	{
		this.type = type;
		this.sprites = new ArrayList<Sprite>();
	}
	
	public AnimationType getType()
	{
		return this.type;
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
	
	public void addSprite(Sprite sprt)
	{
		this.sprites.add(sprt);
	}
	
	public int length()
	{
		return this.sprites.size();
	}
	
	public List<Sprite> getSprites()
	{
		return this.sprites;
	}
	
	public Sprite getSprites(int i)
	{
		return this.sprites.get(i);
	}
}
