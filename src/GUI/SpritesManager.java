package GUI;

import engine.components.character.CharacterType;
import javafx.scene.image.Image;

public class SpritesManager {

	private SpritesLoader loader;
	private Image currentSprite;
	private String currentAnimation;
	private int frame;
	
	public SpritesManager()
	{
		this.loader = new SpritesLoader();
		this.frame = 0;
		this.currentAnimation = AnimationType.IDLE;
		this.currentSprite = this.loader.getImageFromAnimation(CharacterType.IBUKI,AnimationType.IDLE,0);
	}
	
	public void stepAnimation()
	{
		this.frame = ( this.frame + 1 ) % 5; // %Animation.length		
		this.currentSprite = this.loader.getImageFromAnimation(CharacterType.IBUKI,AnimationType.IDLE,this.frame);
	}
	
	public void resetAnimation()
	{
		this.frame = 0;
	}
	
	public Image getCurrentSprite()
	{
		return this.currentSprite;
	}
	
	
	
}
