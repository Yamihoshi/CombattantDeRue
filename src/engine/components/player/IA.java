package engine.components.player;

import java.util.Random;

import engine.services.FightCharService;

public class IA extends Player{
	
	private Commande previousCmd;
	private int frame;

	public IA(FightCharService character) {
		super(character);
		this.previousCmd=Commande.NEUTRAL;
	}
	
	public Commande getRandomCommande()
	{
		if(frame>0 && (previousCmd==Commande.LEFT || previousCmd==Commande.RIGHT))
		{
			frame--;
			return previousCmd;
		}
		
		frame = 60;
		
		Commande[] cmd = Commande.values();
		int rng = new Random().nextInt(cmd.length);
		
		this.previousCmd =  cmd[rng];
		
		return cmd[rng];
	}

}
