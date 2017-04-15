package GUI;

import java.util.ArrayList;

import engine.components.character.CharacterType;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpritesManager {
	
	private SpritesLoader loader;
	private String[] currentAnimation;
	private Timeline animation;
	
	public SpritesManager(ObjectProperty<Image> characterImage, String chara)
	{		
		this.loader = new SpritesLoader(characterImage,chara);
		this.currentAnimation = new String[2];
		this.currentAnimation[0] = AnimationType.STAND;
		this.currentAnimation[1] = AnimationType.STAND;
	}
	
	public void beginAnimation(String animation)
	{
		this.animation = this.loader.getAnimation(CharacterType.CHUN_LI,animation);
		this.animation.play();
		
	}	
	
}
