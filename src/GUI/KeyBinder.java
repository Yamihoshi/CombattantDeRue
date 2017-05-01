package GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import engine.components.player.Commande;
import javafx.scene.input.KeyCode;

public class KeyBinder {

	private List<HashMap<KeyCode,Commande>> keys;
	
	public KeyBinder()
	{
		this.keys = new ArrayList<HashMap<KeyCode,Commande>>();
		this.keys.add(this.createKeys_J1());
		this.keys.add(this.createKeys_J2());
	}
	
	public HashMap<KeyCode,Commande> createKeys_J1()
	{
		HashMap<KeyCode,Commande> bind = new HashMap<KeyCode,Commande>();
		
		bind.put(KeyCode.Q, Commande.LEFT);
		bind.put(KeyCode.D, Commande.RIGHT);
		bind.put(KeyCode.X, Commande.PUNCH);
		bind.put(KeyCode.E, Commande.GUARD);
		bind.put(KeyCode.S, Commande.CROUCH);
		bind.put(KeyCode.W, Commande.KICK);
		
		return bind;
	}
	
	public HashMap<KeyCode,Commande> createKeys_J2()
	{
		HashMap<KeyCode,Commande> bind = new HashMap<KeyCode,Commande>();
		
		bind.put(KeyCode.LEFT, Commande.LEFT);
		bind.put(KeyCode.RIGHT, Commande.RIGHT);
		bind.put(KeyCode.NUMPAD0, Commande.GUARD);
		bind.put(KeyCode.NUMPAD1, Commande.PUNCH);
		bind.put(KeyCode.NUMPAD2, Commande.KICK);
		bind.put(KeyCode.DOWN, Commande.CROUCH);
		
		return bind;
	}
	
	public boolean isKeyOfPlayer(int joueur,KeyCode key)
	{
		return this.keys.get(joueur).containsKey(key);
	}
	
	public Commande getAction(int joueur,KeyCode key)
	{
		Commande cmd = this.keys.get(joueur).get(key);
		
		if(cmd==null)
			return Commande.NEUTRAL;
		
		return cmd;
	}
	
	
}
