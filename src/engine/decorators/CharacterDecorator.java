package engine.decorators;

import java.util.HashMap;

import engine.components.character.Personnage;
import engine.components.character.State;
import engine.components.hitbox.HitboxState;
import engine.components.player.Commande;
import engine.contracts.error.InvariantError;
import engine.services.CharacterService;
import engine.services.ComboService;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.JumpService;
import engine.services.TechService;

public class CharacterDecorator implements FightCharService{


	private FightCharService character;

	public int getEcart() {
		return character.getEcart();
	}

	public void bindHitbox(HitboxService hitbox, HitboxState state) {
		character.bindHitbox(hitbox, state);
	}

	public boolean isBlocking() {
		return character.isBlocking();
	}

	public void init(Personnage personnage, int life, int speed, EngineService engine, boolean faceRight, int ecart) {
		character.init(personnage, life, speed, engine, faceRight, ecart);
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

	public int getPositionX() {
		return character.getPositionX();
	}

	public TechService getCurrentTechnique() {
		return character.getCurrentTechnique();
	}

	public int getPositionY() {
		return character.getPositionY();
	}

	public int getHauteur() {
		return character.getHauteur();
	}

	public ComboService getComboService() {
		return character.getComboService();
	}

	public int getLargeur() {
		return character.getLargeur();
	}

	public State getState() {
		return character.getState();
	}

	public boolean isCombo() {
		return character.isCombo();
	}

	public EngineService getEngine() {
		return character.getEngine();
	}

	public int getCombo() {
		return character.getCombo();
	}

	public HitboxService getCharBox() {
		return character.getCharBox();
	}

	public void startTech(TechService tech) {
		character.startTech(tech);
	}



	public void endTechnique() {
		character.endTechnique();
	}

	public int getLife() {
		return character.getLife();
	}

	public void takeAttack(int damage, int hstun, int bstun) {
		character.takeAttack(damage, hstun, bstun);
	}

	public int getSpeed() {
		return character.getSpeed();
	}

	public String getName() {
		return character.getName();
	}

	public Personnage getPersonnage() {
		return character.getPersonnage();
	}

	public void stepCombo(boolean hit) {
		character.stepCombo(hit);
	}

	public boolean isFaceRight() {
		return character.isFaceRight();
	}

	public boolean isDead() {
		return character.isDead();
	}

	public int getId() {
		return character.getId();
	}

	public void neutral() {
		character.neutral();
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

	public CharacterDecorator(FightCharService character2) {
		super();
		this.character = character2;
	}

	public int getMaxLife() {
		return character.getMaxLife();
	}

	@Override
	public void bindJump(JumpService jumpService) {
		character.bindJump(jumpService);
	}
	
}
