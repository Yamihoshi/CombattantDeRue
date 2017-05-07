package engine.components.player;

import engine.services.FightCharService;
import engine.services.PlayerService;

public class Player implements PlayerService{

	private FightCharService character;

	public Player(FightCharService character)
	{
		this.character = character;
	}
	
	public FightCharService getCharacter() {
		return character;
	}

	public void setCharacter(FightCharService character) {
		this.character = character;
	}

	@Override
	public void init(FightCharService character) {
		this.character = character;
	}
	
	public Commande getRandomCommande(){return null;}
}
