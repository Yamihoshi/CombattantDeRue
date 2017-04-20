package game;

import engine.components.character.CharacterImpl;
import engine.components.player.Player;
import engine.contracts.CharacterContract;
import engine.contracts.EngineContract;
import engine.impl.EngineImpl;
import engine.services.EngineService;

public class StreetFighterGame {

	private EngineService engine;
	private Player[] players;
	
	public StreetFighterGame()
	{
		this.engine = new EngineContract(new EngineImpl());
		this.players= new Player[2];
		this.players[0] = new Player(new CharacterContract(new CharacterImpl()));
		this.players[1] = new Player(new CharacterContract(new CharacterImpl()));
	}
	
	public EngineService getEngine() {
		return engine;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}
	
}
