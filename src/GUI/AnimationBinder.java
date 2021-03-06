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
		this.linker.put(Commande.PUNCH, AnimationType.PUNCH);
		this.linker.put(Commande.GUARD, AnimationType.GUARD);
		this.linker.put(Commande.DOWN, AnimationType.CROUCH);
		this.linker.put(Commande.DOWNLEFT, AnimationType.CROUCH);
		this.linker.put(Commande.DOWNRIGHT, AnimationType.CROUCH);
		this.linker.put(Commande.UP, AnimationType.START_JUMP);
		this.linker.put(Commande.KICK, AnimationType.KICK);
		this.linker.put(Commande.SUPER_PUNCH, AnimationType.PUNCH);
	}
	
	public AnimationType getAnimation(Commande cmd)
	{
		AnimationType anim = this.linker.get(cmd);
		
		if(anim==null)
			return AnimationType.STAND;
		
		return anim;
	}
}
