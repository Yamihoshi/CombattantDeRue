package GUI.animations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import GUI.Ressource;
import GUI.controller.StageController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class SpritesLoader {

	private List<ObjectProperty<Image>> characterImage;
	private List<HashMap<AnimationType, Animation>> animations;
	
	public SpritesLoader(List<ObjectProperty<Image>> characterImage, String chara[])
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
	
	public void initAnimations(String chara[])
	{	
		for(int joueur=0;joueur<chara.length;joueur++)
		{			
			HashMap<AnimationType,Animation> hashmapAnimation = new AnimationCreator(chara[joueur]).getAnimations();
			for(Animation animation : hashmapAnimation.values())
			{
				Timeline timeline = new Timeline();
				
				for(int i=0;i<=60;i++)
				{
					String path = getPathOfSprite(chara[joueur],animation,i);
					
					try {
						//System.out.println(getClass().getResource(path).toURI().toString());
						Image tmp = new Image(getClass().getResource(path).toURI().toString());
						double duree = i*4*StageController.frameTime;
						KeyFrame frame = new KeyFrame(Duration.seconds(duree), new KeyValue(this.characterImage.get(joueur), tmp));
						timeline.getKeyFrames().add(frame);
					} catch (Exception e) {
						//e.printStackTrace();
						break;
					}
				}
				
				KeyFrame frame = new KeyFrame(Duration.seconds(timeline.getKeyFrames().size()*4*StageController.frameTime), new KeyValue(this.characterImage.get(joueur), null));
				timeline.getKeyFrames().add(frame);
				
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
