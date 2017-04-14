package GUI;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import engine.components.character.CharacterType;
import javafx.scene.image.Image;

public class SpritesLoader {

	private HashMap<String,HashMap<String,ArrayList<Image>>> loader;
	
	public SpritesLoader()
	{		
		this.loader = new HashMap<String,HashMap<String,ArrayList<Image>>>();
		this.init(CharacterType.IBUKI);
		this.init(CharacterType.RYU);
	}
	
	public void init(String chara)
	{
		HashMap<String,ArrayList<Image>> sprites_list = new HashMap<String,ArrayList<Image>>();
		
		initIdle(chara,sprites_list);
		
		this.loader.put(chara,sprites_list);
	}
	
	private String getPathOfSprite(String chara,String animation,int frame)
	{
		String path = Ressource.sprites+chara+"/";
		path += chara+"_"+animation+"_"+frame+".png";
		
		return path;
	}
	
	public void initIdle(String chara,HashMap<String,ArrayList<Image>> sprites_list)
	{
		ArrayList<Image> sprites = new ArrayList<Image>();
		
		/* i< nb_frame idle */
		for(int i=1;i<=5;i++)
		{
			String path = getPathOfSprite(chara,AnimationType.IDLE,i);
			try {
				System.out.println(getClass().getResource(path).toURI().toString());
				sprites.add(new Image(getClass().getResource(path).toURI().toString()));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		sprites_list.put(AnimationType.IDLE,sprites);
	}
	
	public Image getImageFromAnimation(String chara,String animation, int i)
	{
		ArrayList<Image> images = getSpritesListOfAnimation(chara,animation);
		
		if(i>=0 && i<images.size())
		{
			return images.get(i);
		}
		else
			return null;
	}
	
	
	public ArrayList<Image> getSpritesListOfAnimation(String chara,String animation)
	{
		//System.out.println("loading " +animation + " of "+chara);
		return this.loader.get(chara).get(animation);
	}
}
