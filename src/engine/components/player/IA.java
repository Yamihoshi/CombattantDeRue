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
		if(frame>0 && (previousCmd==Commande.LEFT || previousCmd==Commande.RIGHT || previousCmd==Commande.DOWN))
		{
			frame--;
			return previousCmd;
		}
		
		frame = 60;
		
		do
		{
			Commande[] cmd = Commande.values();
			int rng = new Random().nextInt(cmd.length);
			this.previousCmd =  cmd[rng];
		}while(this.previousCmd==Commande.NEUTRAL);
		
		return this.previousCmd;
	}

}
