package engine.services;

import engine.components.character.Personnage;
import engine.components.player.Commande;

public interface CharacterService {

	/* Constructors */
	public void init(Personnage personnage, int life, int speed, EngineService engine, boolean faceRight ); // What does they mean by this ?
	
	/* Observators */
	public int getPositionX();
	public int getPositionY();
	public int getHauteur();
	public int getLargeur();
	public EngineService getEngine();
	public HitboxService getCharBox();
	public void setCharBox(HitboxService hit);
	public int getLife();
	public int getSpeed();
	public String getName();
	public Personnage getPersonnage();
	public boolean isFaceRight();
	public boolean isDead();
	
	
	/* Operators */
	public void moveLeft();
	public void moveRight();
	public void moveUpRight();
	public void moveUpLeft();
	public void moveUp();
	public void moveDown();
	public void moveDownLeft();
	public void moveDownRight();
	public void switchSide();
	public void step(Commande c);
}
