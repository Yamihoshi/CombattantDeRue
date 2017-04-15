package GUI;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import GUI.controller.StageController;
import engine.components.character.CharacterType;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpritesLoader {

	private HashMap<String,HashMap<String,Timeline>> loader;
	private ObjectProperty<Image> characterImage;
	
	public SpritesLoader(ObjectProperty<Image> characterImage, String chara)
	{			
		this.characterImage = characterImage;
		this.loader = new HashMap<String,HashMap<String,Timeline>>();
		this.init(CharacterType.CHUN_LI);
		
	}
	
	public void init(String chara)
	{
		HashMap<String,Timeline> sprites_list = new HashMap<String,Timeline>();
		
		initSTAND(chara,sprites_list);
		
		this.loader.put(chara,sprites_list);
	}
	
	private String getPathOfSprite(String chara,String animation,int frame)
	{
		String path = Ressource.sprites+chara+"/";
		path += chara+"_"+animation+"_"+frame+".png";
		
		return path;
	}
	
	public void initSTAND(String chara,HashMap<String,Timeline> sprites_list)
	{
		Timeline timeline = new Timeline();
		
		
		/* i< nb_frame STAND */
		for(int i=0;i<=60;i++)
		{
			String path = getPathOfSprite(chara,AnimationType.STAND,i);
			
			try {
				System.out.println(getClass().getResource(path).toURI().toString());
				Image tmp = new Image(getClass().getResource(path).toURI().toString());
				double duree = i*3*StageController.frameTime;
				KeyFrame frame = new KeyFrame(Duration.seconds(duree), new KeyValue(this.characterImage, tmp));
				timeline.getKeyFrames().add(frame);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				continue;
			}
		}
		
		timeline.setCycleCount(Timeline.INDEFINITE);
		
		sprites_list.put(AnimationType.STAND,timeline);
	}
	
	/*public Image getImageFromAnimation(String chara,String animation, int i)
	{
		ArrayList<Image> images = getSpritesListOfAnimation(chara,animation);
		
		if(i>=0 && i<images.size())
		{
			return images.get(i);
		}
		else
			return null;
	}*/
	
	
	public Timeline getAnimation(String chara,String animation)
	{
		//System.out.println("loading " +animation + " of "+chara);
		return this.loader.get(chara).get(animation);
	}
}
