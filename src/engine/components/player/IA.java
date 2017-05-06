package engine.components.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import engine.services.FightCharService;

public class IA extends Player{
	
	private Commande previousCmd;
	private List<Commande> cmd;
	private int frame;

	public IA(FightCharService character) {
		super(character);
		this.previousCmd=Commande.NEUTRAL;
		this.setCommande();
	}
	
	public void setCommande()
	{
		this.cmd = new ArrayList<Commande>();
		cmd.add(Commande.LEFT);
		cmd.add(Commande.RIGHT);
		cmd.add(Commande.PUNCH);
		cmd.add(Commande.KICK);
		cmd.add(Commande.SUPER_PUNCH);
	}
	
	public Commande getRandomCommande()
	{
		if(frame>0 && (previousCmd==Commande.LEFT || previousCmd==Commande.RIGHT || previousCmd==Commande.DOWN))
		{
			frame--;
			return previousCmd;
		}
		
		frame = 60;
			
		int rng = new Random().nextInt(cmd.size());
		this.previousCmd =  cmd.get(rng);
		
		return this.previousCmd;
	}

}
