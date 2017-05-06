package engine.components.player;

import java.util.Random;

import engine.services.FightCharService;

public class IA extends Player{

	public IA(FightCharService character) {
		super(character);
	}
	
	public static Commande getRandomCommande()
	{
		Commande[] cmd = Commande.values();
		int rng = new Random().nextInt(cmd.length);
		
		return cmd[rng];
	}

}
