package engine.services;

import engine.components.player.Commande;

public interface CharacterService {

	/* Constructors */
	public void init(String nom, int life, int speed, EngineService engine, boolean faceRight ); // What does they mean by this ?
	
	/* Observators */
	public int getPositionX();
	public int getPositionY();
	public EngineService getEngine();
	public HitboxService getCharBox();
	public int getLife();
	public int getSpeed();
	public String getName();
	public boolean isFaceRight();
	
	public boolean isDead(); 
	/* Operators */
	public void moveLeft();
	public void moveRight();
	public void switchSide();
	public void step(Commande c);
}
