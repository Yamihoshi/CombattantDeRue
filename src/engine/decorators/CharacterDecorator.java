package engine.decorators;

import engine.components.player.Commande;
import engine.contracts.error.InvariantError;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.TechService;

public class CharacterDecorator implements FightCharService{


	CharacterService character;
	
	public CharacterDecorator(CharacterService character) {
		super();
		this.character = character;
	}

	public void init(String nom, int life, int speed, EngineService engine, boolean faceRight) {
		character.init(nom, life, speed, engine, faceRight);
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
	
	public void moveUpRight() {
		character.moveUpRight();
	}

	public void moveUpLeft() {
		character.moveUpLeft();
	}

	public void moveUp() {
		character.moveUp();
	}

	public void moveDown() {
		character.moveDown();
	}

	public void moveDownLeft() {
		character.moveDownLeft();
	}

	public void moveDownRight() {
		character.moveDownRight();
	}

	public void switchSide() {
		character.switchSide();
	}

	public void step(Commande c) {
		character.step(c);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return character.getName();
	}
	
	@Override
	public String toString() {
		return "CharacterDecorator [character=" + character + "]";
	}

	@Override
	public void setCharBox(HitboxService hit) {
		character.setCharBox(hit);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBlockStunned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHitStunned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTeching() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TechService getTech() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean techFrame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean techHasAlreadyHit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startTech(TechService tech) {
		// TODO Auto-generated method stub
		
	}
}
