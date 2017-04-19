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

	private List<HashMap<AnimationType,Timeline>> loader;
	private List<ObjectProperty<Image>> characterImage;
	
	public SpritesLoader(List<ObjectProperty<Image>> characterImage, String chara[])
	{			
		this.characterImage = characterImage;
		this.loader = new ArrayList<HashMap<AnimationType,Timeline>>();
		this.initAnimations(chara);	
	}
	
	private String getPathOfSprite(String chara,AnimationType animation,int frame)
	{
		String path = Ressource.sprites+chara+"/";
		path += chara+"_"+animation+"_"+frame+".png";
		
		return path;
	}
	
	public void initAnimations(String chara[])
	{	
		for(int joueur=0;joueur<chara.length;joueur++)
		{
			HashMap<AnimationType,Timeline> sprites_list = new HashMap<AnimationType,Timeline>();
			
			for(AnimationType animation : AnimationType.values())
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
				
				sprites_list.put(animation,timeline);
			}
			
			this.loader.add(sprites_list);
		}
	}	
	
	public Timeline getAnimation(int joueur,AnimationType animation)
	{
		return this.loader.get(joueur).get(animation);
	}
}
