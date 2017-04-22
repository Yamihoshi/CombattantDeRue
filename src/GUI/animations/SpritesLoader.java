package GUI.animations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import engine.services.FightCharService;
import GUI.Ressource;
import GUI.controller.StageController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpritesLoader {

	private List<ImageView> characterImage;
	private List<HashMap<AnimationType, Animation>> animations;
	
	public SpritesLoader(List<ImageView> characterImage, FightCharService chara[])
	{			
		this.characterImage = characterImage;
		this.animations = new ArrayList<HashMap<AnimationType, Animation>>();
		this.initAnimations(chara);	
	}
	
	private String getPathOfSprite(String chara,Animation animation,int frame)
	{
		String path = Ressource.sprites+chara+"/";
		path += chara+"_"+animation.toString()+"_"+frame+".png";
		
		return path;
	}
	
	public void initAnimations(FightCharService chara[])
	{	
		for(int joueur=0;joueur<chara.length;joueur++)
		{			
			HashMap<AnimationType,Animation> hashmapAnimation = AnimationCreator.loadAnimations(chara[joueur].getName());
			for(Animation animation : hashmapAnimation.values())
			{
				Timeline timeline = new Timeline();
				
				for(int i=0;i<=animation.length();i++)
				{
					String path = getPathOfSprite(chara[joueur].getName(),animation,i);
					
					try {
						
						Sprite sprite = animation.getSprites(i);
						
						Image tmp = new Image(getClass().getResource(path).toURI().toString());
						double duree = i*sprite.getDuration()*StageController.frameTime;
						KeyFrame frame = new KeyFrame(Duration.seconds(duree), new KeyValue(this.characterImage.get(joueur).imageProperty(), tmp));
						timeline.getKeyFrames().add(frame);
						
						frame =  new KeyFrame(Duration.seconds(duree), new KeyValue(this.characterImage.get(joueur).translateYProperty(), this.characterImage.get(joueur).getTranslateY()+sprite.getTranslate_Y()));
						timeline.getKeyFrames().add(frame);
						frame =  new KeyFrame(Duration.seconds(duree), new KeyValue(this.characterImage.get(joueur).translateXProperty(), this.characterImage.get(joueur).getTranslateX()+sprite.getTranslate_X()));
						timeline.getKeyFrames().add(frame);
					} catch (Exception e) {
						//e.printStackTrace();
						break;
					}
				}
				if(animation.length()>0)
				{
					Sprite lastSprite = animation.getSprites(animation.length()-1);
					
					KeyFrame frame = new KeyFrame(Duration.seconds((timeline.getKeyFrames().size()/3)*lastSprite.getDuration()*StageController.frameTime), new KeyValue(this.characterImage.get(joueur).imageProperty(), null));
					timeline.getKeyFrames().add(frame);
				}
				
				timeline.setCycleCount(Timeline.INDEFINITE);
					
				animation.setTimeLine(timeline);
					
			}
			
			this.animations.add(hashmapAnimation);
		}
	}
	
	public Animation getAnimation(int joueur,AnimationType type)
	{
		return this.animations.get(joueur).get(type);
	}
}
