package game;

import engine.components.character.FighterImpl;
import engine.components.player.IA;
import engine.components.player.Player;
import engine.contracts.CharacterContract;
import engine.contracts.EngineContract;
import engine.impl.EngineImpl;
import engine.services.EngineService;

public class StreetFighterGame {

	public static final int HEIGHT = 720;
	public static final int WIDTH = 1280;
	private EngineService engine;
	private Player[] players;
	
	public boolean versus_IA;
	
	public StreetFighterGame(boolean IA)
	{
		this.engine = new EngineContract(new EngineImpl());
		this.players= new Player[2];
		this.players[0] = new Player(new CharacterContract(new FighterImpl()));
		this.versus_IA=IA;
		if(!this.versus_IA)
			this.players[1] = new Player(new CharacterContract(new FighterImpl()));
		else
			this.players[1] = new IA(new CharacterContract(new FighterImpl()));
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
	
	public boolean isVersusIA()
	{
		return this.versus_IA;
	}
	
}
