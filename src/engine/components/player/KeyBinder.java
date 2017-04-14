package engine.components.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.input.KeyCode;

public class KeyBinder {

	private List<HashMap<List<KeyCode>,Commande>> binder;
	
	public KeyBinder()
	{
		this.binder = new ArrayList<HashMap<List<KeyCode>,Commande>>();
	}
	
	public KeyBinder(int nb_joueur)
	{
		this();
		this.addPlayers(nb_joueur);
	}
	
	public void addPlayer()
	{
		this.binder.add(new HashMap<List<KeyCode>,Commande>());
	}
	
	public void addPlayers(int n)
	{
		for(int i=0;i<n;i++)
			this.binder.add(new HashMap<List<KeyCode>,Commande>());
	}
	
	public void bind(int joueur,List<KeyCode> keys,Commande commande)
	{
		if(joueur>this.binder.size())
			return;
		
		this.binder.get(joueur).put(keys, commande);
	}
	
	public void default_bind()
	{
		HashMap<List<KeyCode>,Commande> joueur_1 = this.binder.get(0);
		
		List<KeyCode> left = new ArrayList<KeyCode>();
		left.add(KeyCode.LEFT);
	}
	
	
}
