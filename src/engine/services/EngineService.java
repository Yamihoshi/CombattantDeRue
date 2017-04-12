package engine.services;

import engine.components.player.*;

public interface EngineService {

	/* Constructors */
	public void init(int h, int w, int s, Player j1, Player j2); //ou PlayerService ??
	
	/* Observators */
	public int height();
	public int width();
	public Character chara(int n);
	public Player player(int n);
	public boolean gameOver();
	
	/* Operators */
	public void step(CommandeService c1,CommandeService c2);
}
