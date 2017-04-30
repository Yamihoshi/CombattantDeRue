package engine.decorators;

import java.util.HashMap;

import engine.components.character.Personnage;
import engine.components.character.State;
import engine.components.player.Commande;
import engine.contracts.error.InvariantError;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.TechService;

public class CharacterDecorator implements FightCharService{


	FightCharService character;


	public void endTechnique() {
		character.endTechnique();
	}

	public void takeAttack(int damage, int hstun, int bstun) {
		character.takeAttack(damage, hstun, bstun);
	}

	public boolean isBlocking() {
		return character.isBlocking();
	}

	public boolean isBlockStunned() {
		return character.isBlockStunned();
	}

	public boolean isHitStunned() {
		return character.isHitStunned();
	}

	public boolean isTeching() {
		return character.isTeching();
	}

	public HashMap<Commande, TechService> getTech() {
		return character.getTech();
	}

	public boolean techFrame() {
		return character.techFrame();
	}

	public boolean techHasAlreadyHit() {
		return character.techHasAlreadyHit();
	}

	public void startTech(TechService tech) {
		character.startTech(tech);
	}

	public CharacterDecorator(FightCharService character2) {
		super();
		this.character = character2;
	}
	
	@Override
	public void init(Personnage nom, int life, int speed, EngineService engine, boolean faceRight) {
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


	public State getState() {
		return character.getState();
	}

	public void neutral() {
		character.neutral();
	}
	
	protected int getMyIndice(){
		if(getEngine().getCharacter(0) == this)
			return 0;
		return 1;
	}
	
	protected int getOtherIndice(){
		if(getEngine().getCharacter(0) == this)
			return 1;
		return 0;
	}

	public CharacterService getCharacter() {
		return character;
	}

	public void setCharacter(FightCharService character) {
		this.character = character;
	}
	
	public Personnage getPersonnage() {
		return character.getPersonnage();
	}

	@Override
	public int getHauteur() {
		// TODO Auto-generated method stub
		return getCharBox().getHauteur();
	}

	@Override
	public int getLargeur() {
		// TODO Auto-generated method stub
		return getCharBox().getLargeur();
	}





}
