package engine.components.player;

import engine.services.FightCharService;

public class Player {

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

}
