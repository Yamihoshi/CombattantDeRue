package GUI.animations;

import java.util.ArrayList;
import java.util.List;

import engine.components.character.CharacterType;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpritesManager {
	
	private SpritesLoader loader;
	private Animation[] currentAnimation;
	private List<ImageView> characterImages;
	
	public SpritesManager(List<ImageView> viewList, String[] chara)
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
	
	public void beginAnimation(int joueur)
	{
		this.currentAnimation[joueur].play();
		this.aligne(joueur);
	}
	
	public void setAnimation(int joueur,AnimationType type)
	{
		this.currentAnimation[joueur] = this.loader.getAnimation(joueur, type);
	}
	
	public void aligne(int joueur)
	{			
		System.out.println(this.currentAnimation[joueur].getPosition_Y());
		this.characterImages.get(joueur).setTranslateY(this.currentAnimation[joueur].getPosition_Y());
	}
	
}
