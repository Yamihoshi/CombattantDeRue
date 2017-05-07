package engine.impl;

import engine.components.player.Commande;
import engine.components.player.Player;
import engine.contracts.IAContract;
import engine.contracts.PlayerContract;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.PlayerService;

public class EngineImpl implements EngineService{

	public static final int NOMBRE_PERSO = 2;
	private PlayerService players[];
	private int width;
	private int height;
	private int space;
	
	public EngineImpl() {
		super();
		players = new PlayerService[2];
	}

	@Override
	public void init(int h, int w, int s, PlayerService j1, PlayerService j2) {
		width = w;
		height = h;
		space = s;
		players[0] = j1;
		players[1] = j2;
		getCharacter(0).getCharBox().moveTo((int)width/2 - (int)s/2, getCharacter(0).getPositionY());
		getCharacter(1).getCharBox().moveTo((int)width/2  + (int)s/2, getCharacter(1).getPositionY());
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public FightCharService getCharacter(int n) {
		return players[n].getCharacter();
	}

	@Override
	public PlayerService getPlayer(int n) {
		return players[n];
	}

	@Override
	public boolean isGameOver() {
		for(int i = 0; i < NOMBRE_PERSO; i++){
			if(getCharacter(i).isDead())
				return true;
		}
		return false;
	}

	@Override
	public void step(Commande c1, Commande c2) {
		this.getCharacter(0).step(c1);
		this.getCharacter(1).step(c2);
	}

	@Override
	public FightCharService[] getCharacters() {
		FightCharService[] charas = new FightCharService[2];
		charas[0] = this.getCharacter(0);
		charas[1] = this.getCharacter(1);
		return charas;
	}

	@Override
	public int getSpace() {
		return space;
	}

	@Override
	public int getOtherIndice(int myId){
		if(this.getCharacter(0).getId() == myId)
			return 1;
		return 0;
	}
	 
	@Override
	public int getMyIndice(int myId){
		if(this.getCharacter(0).getId() == myId)
			return 0;
		return 1;
	}

}
