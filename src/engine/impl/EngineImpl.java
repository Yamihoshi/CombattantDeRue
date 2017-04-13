package engine.impl;

import engine.components.player.Commande;
import engine.components.player.Player;
import engine.services.CharacterService;
import engine.services.EngineService;

public class EngineImpl implements EngineService{

	public static final int NOMBRE_PERSO = 2;
	private Player player[];
	private CharacterService character[];
	private int width;
	private int height;
	private int space;
	
	public EngineImpl() {
		super();
		player = new Player[2];
		character = new CharacterService[2];
	}

	@Override
	public void init(int h, int w, int s, Player j1, Player j2) {
		width = w;
		height = h;
		space = s;
		player[0] = j1;
		player[1] = j2;
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
	public CharacterService getCharacter(int n) {
		// TODO Auto-generated method stub
		return character[n];
	}

	@Override
	public Player getPlayer(int n) {
		// TODO Auto-generated method stub
		return player[n];
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
		// TODO Auto-generated method stub
		
	}

}
