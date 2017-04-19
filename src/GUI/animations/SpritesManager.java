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
	private AnimationType[] currentAnimation;
	private Timeline[] animation;
	
	public SpritesManager(List<ObjectProperty<Image>> characterImage, String[] chara)
	{		
		this.loader = new SpritesLoader(characterImage,chara);
		this.currentAnimation = new AnimationType[2];
		this.currentAnimation[0] = AnimationType.STAND;
		this.currentAnimation[1] = AnimationType.STAND;
		this.animation = new Timeline[2];
	}
	
	public void beginAnimation(AnimationType animation, int joueur)
	{
		this.animation[joueur] = this.loader.getAnimation(joueur,animation);
		this.animation[joueur].play();
		
	}	
	
}
