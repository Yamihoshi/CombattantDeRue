package GUI.animations;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Timeline;
import javafx.scene.image.Image;

public class Animation {
	
	public static final int ref_position_Y = (int) (Sprite.ratio*430);
	public static final int ref_position_X_left = (int) (Sprite.ratio*186);
	
	private AnimationType type;
	private List<Sprite> sprites;
	private boolean loop;
	private int currentSprite;
	private int translate_X;
	private int translate_Y;
	private int translate_X_reversed;
	
	public Animation(AnimationType type)
	{
		this.type = type;
		this.sprites = new ArrayList<Sprite>();
		this.currentSprite = 0;
		this.loop=true;
	}
	
	public void setPosition(int posX, int posX_reversed, int posY)
	{
		this.translate_X = (int) (Sprite.ratio*posX);
		this.translate_X_reversed = (int) (Sprite.ratio*posX_reversed);
		this.translate_Y = (int) (Sprite.ratio*posY);
		
		this.translate_Y = ref_position_Y - translate_Y;
		
		this.translate_X = ref_position_X_left - translate_X;
		this.translate_X_reversed = ref_position_X_left - translate_X_reversed;
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
	
	public void setLoop(boolean loop)
	{
		this.loop=loop;
	}
	
	public boolean isLooped()
	{
		return this.loop;
	}
	public void restart()
	{
		this.currentSprite = 0;
	}

	public int getTranslate_X() {
		return translate_X;
	}
	
	public int getTranslate_X_reversed() {
		
		int val = (int) (this.getImage(0).getWidth() - 361*Sprite.ratio) + this.translate_X;
		
		System.out.println(type + " "+val);
		
		return - val;
	}

	public void setTranslate_X(int translateX) {
		this.translate_X = translateX;
	}

	public int getTranslate_Y() {
		return translate_Y;
	}

	public void setTranslate_Y(int translateY) {
		this.translate_Y = translateY;
	}
}
