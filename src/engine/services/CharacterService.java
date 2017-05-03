package engine.services;

import engine.components.character.Personnage;
import engine.components.character.State;
import engine.components.hitbox.HitboxState;
import engine.components.player.Commande;

public interface CharacterService {

	/* Constructors */
	public void init(Personnage personnage, int life, int speed, EngineService engine, boolean faceRight, int ecart ); // What does they mean by this ?
	
	/* Observators */
	public int getPositionX();
	public int getPositionY();
	public int getHauteur();
	public int getLargeur();
	public int getEcart();
	public State getState();
	public EngineService getEngine();
	public HitboxService getCharBox();
	public int getLife();
	public int getMaxLife();
	public int getSpeed();
	public String getName();
	public Personnage getPersonnage();
	public boolean isFaceRight();
	public boolean isDead();
	public int getId();
	public void bindHitbox(HitboxService hitbox, HitboxState state);
	
	
	/* Operators */
	public void neutral();
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
