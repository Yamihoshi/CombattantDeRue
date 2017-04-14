package GUI;

import engine.components.character.CharacterType;
import javafx.scene.image.Image;

public class SpritesManager {

	private SpritesLoader loader;
	private Image[] currentSprite;
	private String[] currentAnimation;
	private int frame;
	
	public SpritesManager()
	{
		this.loader = new SpritesLoader();
		this.frame = 0;
		this.currentAnimation = new String[2];
		this.currentAnimation[0] = AnimationType.IDLE;
		this.currentAnimation[1] = AnimationType.IDLE;
		this.currentSprite = new Image[2];
		this.currentSprite[0] = this.loader.getImageFromAnimation(CharacterType.IBUKI,AnimationType.IDLE,0);
		this.currentSprite[1] = this.loader.getImageFromAnimation(CharacterType.RYU,AnimationType.IDLE,0);
	}
	
	public void stepAnimation()
	{
		this.frame = ( this.frame + 1 ) % 5; // %Animation.length		
		this.currentSprite[0] = this.loader.getImageFromAnimation(CharacterType.IBUKI,AnimationType.IDLE,this.frame);
		this.currentSprite[1] = this.loader.getImageFromAnimation(CharacterType.RYU,AnimationType.IDLE,this.frame);
	}
	
	public void resetAnimation()
	{
		this.frame = 0;
	}
	
	public Image getCurrentSprite(int joueur)
	{
		return this.currentSprite[joueur];
	}
	
	
	
}
