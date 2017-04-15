package GUI;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

	private List<HashMap<String,Timeline>> loader;
	private List<ObjectProperty<Image>> characterImage;
	
	public SpritesLoader(List<ObjectProperty<Image>> characterImage, String chara[])
	{			
		this.characterImage = characterImage;
		this.loader = new ArrayList<HashMap<String,Timeline>>();
		for(int i=0;i<chara.length;i++)
			this.init(chara[i],i);
		
	}
	
	public void init(String chara, int joueur)
	{
		HashMap<String,Timeline> sprites_list = new HashMap<String,Timeline>();
		
		initSTAND(chara,sprites_list, joueur);
		
		this.loader.add(sprites_list);
	}
	
	private String getPathOfSprite(String chara,String animation,int frame)
	{
		String path = Ressource.sprites+chara+"/";
		path += chara+"_"+animation+"_"+frame+".png";
		
		return path;
	}
	
	public void initSTAND(String chara,HashMap<String,Timeline> sprites_list, int joueur)
	{
		Timeline timeline = new Timeline();
		
		
		/* i< nb_frame STAND */
		for(int i=0;i<=60;i++)
		{
			String path = getPathOfSprite(chara,AnimationType.STAND,i);
			
			try {
				//System.out.println(getClass().getResource(path).toURI().toString());
				Image tmp = new Image(getClass().getResource(path).toURI().toString());
				double duree = i*4*StageController.frameTime;
				KeyFrame frame = new KeyFrame(Duration.seconds(duree), new KeyValue(this.characterImage.get(joueur), tmp));
				timeline.getKeyFrames().add(frame);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				break;
			}
		}
		
		KeyFrame frame = new KeyFrame(Duration.seconds(timeline.getKeyFrames().size()*4*StageController.frameTime), new KeyValue(this.characterImage.get(joueur), null));
		timeline.getKeyFrames().add(frame);
		
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
	
	
	public Timeline getAnimation(int joueur,String animation)
	{
		//System.out.println("loading " +animation + " of "+chara);
		return this.loader.get(joueur).get(animation);
	}
}
