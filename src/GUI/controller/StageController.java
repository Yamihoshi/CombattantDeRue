package GUI.controller;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import GUI.AnimationType;
import GUI.SpritesManager;
import engine.components.character.CharacterType;
import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
  
	private List<KeyEvent> key_event;
	private SpritesManager sprites_manager;
	
    @Override  
    public void initialize(URL location, ResourceBundle resources) {  

    	character_J1.setFocusTraversable(true);
    	
    	character_J1.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
            	keyPressed(event.getCode());
            }
        });
    	
       /* new AnimationTimer()
        {
        	int frameCount = 0;
        	long lasttimeFPS = System.nanoTime();
        	int i = 0;
        	
            @Override
            public void handle(long arg0)
            {
            	frameCount++;
            	
            	long currenttimeNano = System.nanoTime();
            	
            	int tick = 3;
            	
            	if(i>=7 && i<=15)
            		tick=4;
            	
            	if (currenttimeNano > lasttimeFPS + 1000000000 * frameTime * tick)
            	{
            		//System.out.println("1 FRAME");
            		frameCount = 0;
                    lasttimeFPS = currenttimeNano;
                    if(i==21)
                    	i=0;
                    
                    try {
						character_J1.setImage(new Image(getClass().getResource("/sprites/Chun_Li/Chun_Li_stand_"+i+".png").toURI().toString()));
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                    System.out.println(i);
                    
                    i++;
            	}
            	
            }
        }.start();*/
    	
        
    	character_J1.setCache(true);
    	character_J1.setCacheHint(CacheHint.SPEED);
    	
    	this.sprites_manager = new SpritesManager(character_J1.imageProperty(),CharacterType.CHUN_LI);
    	this.sprites_manager.beginAnimation(AnimationType.STAND);
    	
		/*Task<Void> task = new Task<Void>() {
			int desiredRate = 20;
			long timeNeeded = 1_000_000 / desiredRate;

		    @Override 
		    protected Void call() throws Exception{
		        while(true){
			        long time = System.nanoTime(); 

		        	if (isCancelled()) {
		                break;
		             }
		        	refreshFighter();
		            long executionTime = System.nanoTime() - time;
		            long remainingTime = timeNeeded - executionTime;
		            
		        	try {
			            if (remainingTime > 0){ 
			            	Thread.sleep(remainingTime/1000);
			            	System.out.println(remainingTime);
			            }
			            else
			            	System.out.println(remainingTime);
		            } catch (InterruptedException interrupted) {
		                if (isCancelled()) {
		                    updateMessage("Cancelled");
		                    break;
		                }
		            }
		        } 
		        return null;
		    }
		};
		
		Thread t = new Thread(task);
		t.setDaemon(true);
		t.start();*/
    }  
    
	public void refreshFighter()
	{		
		
	}
	
	public void keyPressed(KeyCode key)
	{
		System.out.println(key);
	}
	
	public void keyReleased()
	{
		
	}


}
