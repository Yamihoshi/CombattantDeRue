package GUI;

import java.util.ArrayList;
import java.util.List;

import engine.components.character.CharacterType;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpritesManager {
	
	private SpritesLoader loader;
	private String[] currentAnimation;
	private Timeline[] animation;
	
	public SpritesManager(List<ObjectProperty<Image>> characterImage, String[] chara)
	{		
		this.loader = new SpritesLoader(characterImage,chara);
		this.currentAnimation = new String[2];
		this.currentAnimation[0] = AnimationType.STAND.toString();
		this.currentAnimation[1] = AnimationType.STAND.toString();
		this.animation = new Timeline[2];
	}
	
	public void beginAnimation(String animation, int joueur)
	{
		this.animation[joueur] = this.loader.getAnimation(joueur,animation);
		this.animation[joueur].play();
		
	}	
	
}
