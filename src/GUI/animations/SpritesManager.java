package GUI.animations;

import java.util.List;

import engine.services.FightCharService;
import javafx.scene.image.ImageView;

public class SpritesManager {
	
	private SpritesLoader loader;
	private Animation[] currentAnimation;
	private List<ImageView> characterImages;
	
	public SpritesManager(List<ImageView> viewList, FightCharService[] chara)
	{	
		this.characterImages = viewList;
		
		this.loader = new SpritesLoader(viewList,chara);
		
		this.currentAnimation = new Animation[2];		
		this.currentAnimation[0] = this.loader.getAnimation(0, AnimationType.STAND);
		this.currentAnimation[1] = this.loader.getAnimation(1, AnimationType.STAND);
	}
	
	public void stopAnimation(int joueur)
	{
		this.currentAnimation[joueur].stop();
	}
	
	public void playAnimation(int joueur,AnimationType type)
	{
		this.stopAnimation(joueur);
		this.currentAnimation[joueur] = this.loader.getAnimation(joueur, type);
		this.currentAnimation[joueur].play();
	}
	
	public AnimationType getAnimationPlayed(int joueur)
	{
		return this.currentAnimation[joueur].getType();
	}
	
}
