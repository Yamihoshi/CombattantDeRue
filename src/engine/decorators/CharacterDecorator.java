package engine.decorators;

import engine.components.player.Commande;
import engine.contracts.error.InvariantError;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.HitboxService;

public class CharacterDecorator implements CharacterService{
	CharacterService character;
	

	public void init(int x, int y, EngineService engine, boolean faceRight) {
		character.init(x, y, engine, faceRight);
	}

	public int getPositionX() {
		return character.getPositionX();
	}

	public int getPositionY() {
		return character.getPositionY();
	}

	public EngineService getEngine() {
		return character.getEngine();
	}

	public HitboxService getCharBox() {
		return character.getCharBox();
	}

	public int getLife() {
		return character.getLife();
	}

	public int getSpeed() {
		return character.getSpeed();
	}

	public boolean isFaceRight() {
		return character.isFaceRight();
	}


	public boolean isDead() {
		return character.isDead();
	}

	public void moveLeft() {
		character.moveLeft();
	}

	public void moveRight() {
		character.moveRight();
	}

	public void switchSide() {
		character.switchSide();
	}

	public void step(Commande c) {
		character.step(c);
	}
}
