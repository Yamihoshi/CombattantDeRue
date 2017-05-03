package engine.services;

import engine.components.player.*;

public interface EngineService {

	/* Constructors */
	public void init(int h, int w, int s, Player j1, Player j2); //ou PlayerService ??
	
	/* Observators */
	public int getHeight();
	public int getWidth();
	public int getSpace();
	public FightCharService getCharacter(int n);
	public FightCharService[] getCharacters();
	public Player getPlayer(int n);
	public boolean isGameOver();
	public int getOtherIndice(int myId);
	public int getMyIndice(int myId);

	/* Operators */
	
	public void step(Commande c1,Commande c2);

}
