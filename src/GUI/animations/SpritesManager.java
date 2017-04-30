package GUI.animations;

import java.util.List;

import engine.services.FightCharService;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpritesManager {
	
	private SpritesLoader loader;
	private Animation[] currentAnimation;
	
	public SpritesManager(FightCharService[] chara)
	{	
		this.loader = new SpritesLoader(chara);
		
		this.currentAnimation = new Animation[2];		
		this.currentAnimation[0] = this.loader.getAnimation(0, AnimationType.STAND);
		this.currentAnimation[1] = this.loader.getAnimation(1, AnimationType.STAND);
	}
	
	public void playAnimation(int joueur,AnimationType type)
	{
		this.currentAnimation[joueur] = this.loader.getAnimation(joueur, type);
	}
	
	public AnimationType getAnimationPlayed(int joueur)
	{
		return this.currentAnimation[joueur].getType();
	}
	
	public void stepAnimation(int joueur)
	{
		this.currentAnimation[joueur].step();
	}
	
	public Sprite getCurrentSprite(int joueur)
	{
		return this.currentAnimation[joueur].getCurrentSprite();
	}
	
	public Image getCurrentImage(int joueur)
	{
		return this.currentAnimation[joueur].getCurrentImage();
	}
	
}
