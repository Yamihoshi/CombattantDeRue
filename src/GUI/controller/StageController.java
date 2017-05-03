package GUI.controller;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import GUI.animations.AnimationType;
import GUI.animations.Sprite;
import GUI.animations.SpritesManager;
import engine.components.character.Personnage;
import engine.services.FightCharService;
import engine.services.HitboxService;
import javafx.animation.AnimationTimer;
import javafx.beans.property.ObjectProperty;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class StageController implements Initializable {

	public static final double frameTime = 1.0/60.0;
	
	@FXML 
    private AnchorPane root; 
	
	@FXML 
    private ImageView character_J1;
	
	@FXML 
    private ImageView character_J2;
	
	@FXML 
    private Rectangle hitbox_J1;
	
	@FXML 
    private Rectangle hitbox_J2;
	
	@FXML 
    private Rectangle hitbox_attack_J1;
	
	@FXML 
    private Rectangle hitbox_attack_J2;
	
	@FXML 
    private Text frame_count;
	
	@FXML 
    private ProgressBar life_bar_J1;
	
	@FXML 
    private ProgressBar life_bar_J2;
	
	private boolean showHitbox;
  	public static int SPACE = 70;
    @Override  
    public void initialize(URL location, ResourceBundle resources) {  
        
    	character_J1.setCache(true);
    	character_J1.setCacheHint(CacheHint.SPEED);
    	character_J2.setCache(true);
    	character_J2.setCacheHint(CacheHint.SPEED);	
    	
    	character_J1.setRotationAxis(Rotate.Y_AXIS);
    	character_J2.setRotationAxis(Rotate.Y_AXIS);
    	
    	this.showHitbox = true;
    	this.toggleHitBox();
    }
    
    public ImageView getCharacterOfJ1()
    {
    	return this.character_J1;
    }
    
    public ImageView getCharacterOfJ2()
    {
    	return this.character_J2;
    }

    public void toggleHitBox()
    {
    	this.showHitbox = ! this.showHitbox;
    	
    	hitbox_J1.setVisible(showHitbox);
    	hitbox_J2.setVisible(showHitbox);
    	hitbox_attack_J1.setVisible(showHitbox);
    }
    
    public void setHitbox(boolean hit)
    {
    	if(hit)
    	{
    		//set Color to RED
    	}
    	else
    	{
    		//set Color to GREEN
    	}
    }
    
    public void updateSprite(int joueur,Sprite sprite)
    {
    	if(joueur==0)
    		this.updateSprite_J1(sprite);
    	else
    		this.updateSprite_J2(sprite);
    }
    
    public void updateSpriteAlignement(int joueur,int posX,int posY)
    {
    	if(joueur==0)
    	{
    		this.character_J1.setTranslateX(posX);
    		this.character_J1.setTranslateY(posY);
    	}
    	else
    	{
    		this.character_J2.setTranslateX(posX);
    		this.character_J2.setTranslateY(posY);
    	}
    }
    
    public void updateSprite_J1(Sprite sprite)
    {
    	this.character_J1.setImage(sprite.getImage());
    }
    
    public void updateSprite_J2(Sprite sprite)
    {
    	this.character_J2.setImage(sprite.getImage());
    }
    
    public void updatePosition(HitboxService hitboxJ1, HitboxService hitboxJ2)
    {
    	this.character_J1.setLayoutX(hitboxJ1.getPositionX().get());
    	this.character_J1.setLayoutY(hitboxJ1.getPositionY());
    	
    	this.character_J2.setLayoutY(hitboxJ2.getPositionY() );
    	this.character_J2.setLayoutX(hitboxJ2.getPositionX().get());

    }
    
    public void updateHitbox(FightCharService fc1, FightCharService fc2){
    	HitboxService hitboxJ1 = fc1.getCharBox(), hitboxJ2 = fc2.getCharBox();
    	int J1x = hitboxJ1.getPositionX().get(), J2x = hitboxJ2.getPositionX().get();
    	int J1width = hitboxJ1.getLargeur();
    	int J2width = hitboxJ2.getLargeur();
    	if(!fc1.isFaceRight()){
    		J1x=fc1.getEcart();
    	}else{
    		J2x+= fc2.getEcart();
    	}
    	this.hitbox_J1.setLayoutX(J1x);
    	this.hitbox_J1.setLayoutY(hitboxJ1.getPositionY());
    	this.hitbox_J1.setHeight(hitboxJ1.getHauteur());
    	this.hitbox_J1.setWidth(hitboxJ1.getLargeur());
    	
    	if(fc1.isTeching() && fc1.getCurrentTechnique().isInHit()){
    		HitboxService attack = fc1.getCurrentTechnique().getHitbox();
    		this.hitbox_attack_J1.setLayoutX(attack.getPositionX().get());
    		this.hitbox_attack_J1.setLayoutY(attack.getPositionY());
        	this.hitbox_attack_J1.setHeight(attack.getHauteur());
        	this.hitbox_attack_J1.setWidth(attack.getLargeur());
    		hitbox_attack_J1.setVisible(true);

    	}else{
    		hitbox_attack_J1.setVisible(false);
    	}
    	
    	this.hitbox_J2.setLayoutX(J2x);
    	this.hitbox_J2.setLayoutY(hitboxJ2.getPositionY());
    	this.hitbox_J2.setHeight(hitboxJ2.getHauteur());
    	this.hitbox_J2.setWidth(hitboxJ2.getLargeur());
    	
    	if(fc2.isTeching() && fc2.getCurrentTechnique().isInHit()){
    		HitboxService attack = fc2.getCurrentTechnique().getHitbox();
    		this.hitbox_attack_J2.setLayoutX(attack.getPositionX().get());
    		this.hitbox_attack_J2.setLayoutY(attack.getPositionY());
        	this.hitbox_attack_J2.setHeight(attack.getHauteur());
        	this.hitbox_attack_J2.setWidth(attack.getLargeur());
    		hitbox_attack_J2.setVisible(true);

    	}else{
    		hitbox_attack_J2.setVisible(false);
    	}
    }
    public void setFrame(int frame)
    {
    	this.frame_count.setText(frame+"");
    }

	public void flip(int joueur) {
		
    	if(joueur==0)
    	{
    		this.character_J1.setRotate(180);
    	}
    	else
    	{
    		this.character_J2.setRotate(180);
    	}
	}
	
	public void setLifeBarValue(int joueur,double val)
	{
		if(val<0.0)
			val=0.0;
		
		if(joueur==0)
		{
			if(val!=life_bar_J1.getProgress())
				life_bar_J1.setProgress(val);
		}
		else
			if(val!=life_bar_J2.getProgress())
				life_bar_J2.setProgress(val);
	}
}
