package engine.impl;

import engine.components.player.Commande;
import engine.components.player.Player;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.FightCharService;

public class EngineImpl implements EngineService{

	public static final int NOMBRE_PERSO = 2;
	private Player players[];
	private int width;
	private int height;
	private int space;
	
	public EngineImpl() {
		super();
		players = new Player[2];
	}

	@Override
	public void init(int h, int w, int s, Player j1, Player j2) {
		width = w;
		height = h;
		space = s;
		players[0] = j1;
		players[1] = j2;
		
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public FightCharService getCharacter(int n) {
		// TODO Auto-generated method stub
		return players[n].getCharacter();
	}

	@Override
	public Player getPlayer(int n) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		FightCharService[] charas = new FightCharService[2];
		charas[0] = this.getCharacter(0);
		charas[1] = this.getCharacter(1);
		
		return charas;
	}

}
