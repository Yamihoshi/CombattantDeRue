package GUI.animations;

import java.util.ArrayList;
import java.util.List;

import engine.services.FightCharService;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpritesManager {
	
	private SpritesLoader loader;
	private Animation[] currentAnimation;
	private List<ImageView> characterImages;
	
	public SpritesManager(List<ImageView> viewList, FightCharService[] chara)
	{	
		this.characterImages = viewList;
		
		List<ObjectProperty<Image>> characterImage = new ArrayList<ObjectProperty<Image>>();
		for(ImageView view : viewList)
			characterImage.add(view.imageProperty());
		
		this.loader = new SpritesLoader(characterImage,chara);
		
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
		this.aligne(joueur);
	}
	
	public void aligne(int joueur)
	{			
		this.characterImages.get(joueur).setTranslateY(this.currentAnimation[joueur].getPosition_Y());
		this.characterImages.get(joueur).setTranslateX(this.currentAnimation[joueur].getPosition_X());
	}
	
	public AnimationType getAnimationPlayed(int joueur)
	{
		return this.currentAnimation[joueur].getType();
	}
	
}
