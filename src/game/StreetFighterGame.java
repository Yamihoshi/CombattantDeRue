package game;

import engine.components.character.FighterImpl;
import engine.components.player.IA;
import engine.components.player.Player;
import engine.contracts.CharacterContract;
import engine.contracts.EngineContract;
import engine.contracts.IAContract;
import engine.contracts.PlayerContract;
import engine.impl.EngineImpl;
import engine.services.EngineService;
import engine.services.IAService;
import engine.services.PlayerService;

public class StreetFighterGame {

	public static final int HEIGHT = 720;
	public static final int WIDTH = 1280;
	private EngineService engine;
	private PlayerService[] players;
	
	public boolean versus_IA;
	
	public StreetFighterGame(boolean IA)
	{
		this.engine = new EngineContract(new EngineImpl());
		this.players= new PlayerService[2];
		this.players[0] =  new PlayerContract(new Player
							(new CharacterContract(new FighterImpl())));
		this.versus_IA=IA;
		this.setPlayer2();
	}
	
	public EngineService getEngine() {
		return engine;
	}

	public PlayerService[] getPlayers() {
		return players;
	}

	public void setPlayers(PlayerService[] players) {
		this.players =  players;
	}
	
	public boolean isVersusIA()
	{
		return this.versus_IA;
	}

	public void toggleIA() {
		this.versus_IA=!this.versus_IA;
		this.setPlayer2();
	}
	
	public void setPlayer2()
	{
		if(!this.versus_IA)
			this.players[1] = new PlayerContract(new Player
					(new CharacterContract(new FighterImpl())));
		else
			this.players[1] = new IA
					(new CharacterContract(new FighterImpl()));
	}
	
}
