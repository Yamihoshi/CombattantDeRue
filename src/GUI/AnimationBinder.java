package GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import GUI.animations.AnimationType;
import engine.components.player.Commande;
import javafx.scene.input.KeyCode;

public class AnimationBinder {

	private HashMap<Commande,AnimationType> linker;
	
	public AnimationBinder()
	{
		this.linker = new HashMap<Commande,AnimationType>();
		this.createLinks();
	}
	
	public void createLinks()
	{
		this.linker.put(Commande.NEUTRAL, AnimationType.STAND);
		this.linker.put(Commande.LEFT, AnimationType.WALK_FORWARD);
		this.linker.put(Commande.RIGHT, AnimationType.WALK_FORWARD);
		this.linker.put(Commande.PUNCH, AnimationType.STAND);
		this.linker.put(Commande.GUARD, AnimationType.GUARD);
		this.linker.put(Commande.CROUCH, AnimationType.GUARD);
	}
	
	public AnimationType getAnimation(Commande cmd)
	{
		return this.linker.get(cmd);
	}
}
