package engine.contracts;

import engine.components.character.Personnage;
import engine.components.character.State;
import engine.components.player.Commande;
import engine.contracts.error.InvariantError;
import engine.contracts.error.PostconditionError;
import engine.contracts.error.PreconditionError;
import engine.decorators.CharacterDecorator;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.TechService;

public class CharacterContract extends CharacterDecorator {

	public CharacterContract(FightCharService character) {
		super(character);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(Personnage personnage, int l, int s, EngineService engine, boolean faceRight, int ecart) {
		if(!(l > 0 && s>0)){
			throw new PreconditionError("Erreur init precondition character");
		}
		
		super.init(personnage, l, s, engine, faceRight, ecart);
	
		if(!(getLife() == l && getSpeed() == s && isFaceRight() == faceRight && getEngine().equals(engine)) && getCharBox() != null ){
			throw new PostconditionError("Erreur post condition error");
		}
	}
 


	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		
		int other = getEngine().getOtherIndice(getId());
		int pre_positionX = getPositionX();
		 
		checkInvariant();
		super.moveLeft();
		checkInvariant();
		
		if(!(!(getCharBox().collidesWith(getEngine().getCharacter(other).getCharBox()))
				|| (getPositionX() == pre_positionX))
				){
			throw new PostconditionError("Collision with a changement of posX" + getPositionX() + "==" + pre_positionX);
		}
	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		int other = getEngine().getOtherIndice(getId());
		int pre_positionX = getPositionX();
		
		checkInvariant();
		super.moveRight();
		
		if((!(getCharBox().collidesWith(getEngine().getCharacter(other).getCharBox()))
				|| (getPositionX() == pre_positionX))
				){
			new PostconditionError("Collision with a deplacement of posX on a right deplacement");
		}
		
		checkInvariant();
	}

	@Override
	public void switchSide() {
		checkInvariant();
		// TODO Auto-generated method stub
		boolean pre_faceRight = isFaceRight();
		int pre_positionX = getPositionX();
		super.switchSide();
		if(!(pre_positionX == getPositionX() && pre_faceRight != isFaceRight())){
			throw new PostconditionError("Erreur post condition error switchSide");
		}
		checkInvariant();
	}

	@Override
	public void step(Commande c) {
		checkInvariant();
		// TODO Auto-generated method stub
		super.step(c);
		checkInvariant();
	}
	
	public void checkInvariant(){
		if(!(getPositionX() > 0 && getPositionX() + getLargeur() < getEngine().getWidth())){
			//throw new InvariantError("Erreur Character X " + getPositionX());
		}
		if(!(getPositionY() > 0 && getPositionY() < getEngine().getHeight())){
			throw new InvariantError("Erreur Character Y " + getPositionY());
		}
		if(!(isDead() == !(getLife()>0))){
			throw new InvariantError("Erreur Character not really dead kek");
		}
	}
	@Override
	public void moveUpRight() {
		super.moveUpRight();
	}

	@Override
	public void moveUpLeft() {
		super.moveUpLeft();
	}

	@Override
	public void moveUp() {
		super.moveUp();
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		super.moveDown();
	}

	@Override
	public void moveDownLeft() {
		// TODO Auto-generated method stub
		super.moveDownLeft();
	}

	@Override
	public void moveDownRight() {
		// TODO Auto-generated method stub
		super.moveDownRight();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}



	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return super.getState();
	}

	@Override
	public void neutral() {
		// TODO Auto-generated method stub
		super.neutral();
	}

	@Override
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return super.isBlocking();
	}

	@Override
	public boolean isBlockStunned() {
		// TODO Auto-generated method stub
		return super.isBlockStunned();
	}

	@Override
	public boolean isHitStunned() {
		return super.isHitStunned();
	}

	@Override
	public boolean isTeching() {
		return super.isTeching();
	}



	@Override
	public void startTech(TechService tech) {
		super.startTech(tech);
	}


	@Override
	public int getHauteur() {
		// TODO Auto-generated method stub
		return super.getHauteur();
	}

	@Override
	public int getLargeur() {
		// TODO Auto-generated method stub
		return super.getLargeur();
	}

	@Override
	public Personnage getPersonnage() {
		// TODO Auto-generated method stub
		return super.getPersonnage();
	}
	@Override
	public int getPositionX() {
		// TODO Auto-generated method stub
		return super.getPositionX();
	}

	@Override
	public int getPositionY() {
		// TODO Auto-generated method stub
		return super.getPositionY();
	}

	@Override
	public EngineService getEngine() {
		// TODO Auto-generated method stub
		return super.getEngine();
	}

	@Override
	public HitboxService getCharBox() {
		// TODO Auto-generated method stub
		return super.getCharBox();
	}

	@Override
	public int getLife() {
		// TODO Auto-generated method stub
		return super.getLife();
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return super.getSpeed();
	}

	@Override
	public boolean isFaceRight() {
		// TODO Auto-generated method stub
		return super.isFaceRight();
	}


	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return super.isDead();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
