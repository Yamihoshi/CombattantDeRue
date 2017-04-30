package GUI.animations;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Timeline;
import javafx.scene.image.Image;

public class Animation {
	
	private AnimationType type;
	private List<Sprite> sprites;
	private boolean loop;
	private int currentSprite;
	
	public Animation(AnimationType type,boolean reversed)
	{
		this.type = type;
		this.sprites = new ArrayList<Sprite>();
		this.currentSprite = 0;
		this.loop=true;
	}
	
	public AnimationType getType()
	{
		return this.type;
	}
	
	public String toString()
	{
		return this.type.toString();
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
	
	public Sprite getSprite(int i)
	{
		return this.sprites.get(i);
	}
	
	public Image getImage(int i)
	{
		return this.sprites.get(i).getImage();
	}
	
	public Image getCurrentImage()
	{
		return getImage(this.currentSprite);
	}
	
	
	public Sprite getCurrentSprite()
	{
		return getSprite(this.currentSprite);
	}
	
	public void step()
	{
		this.currentSprite = (this.currentSprite+1)%this.sprites.size();
	}
}
