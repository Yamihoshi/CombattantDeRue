package engine.contracts;

import engine.components.player.Commande;
import engine.contracts.error.InvariantError;
import engine.contracts.error.PostconditionError;
import engine.contracts.error.PreconditionError;
import engine.decorators.CharacterDecorator;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.HitboxService;

public class CharacterContract extends CharacterDecorator {



	public CharacterContract(CharacterService character) {
		super(character);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(String nom, int l, int s, EngineService engine, boolean faceRight) {
		if(!(l > 0 && s>0)){
			throw new PreconditionError("Erreur init precondition character");
		}
		
		super.init(nom, l, s, engine, faceRight);
	
		if(!(getLife() == l && getSpeed() == s && isFaceRight() == faceRight && getEngine().equals(engine)) && getCharBox() != null ){
			throw new PostconditionError("Erreur post condition error");
		}
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

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		
		int other = getOtherIndice();
		int pre_positionX = getPositionX();
		
		checkInvariant();
		super.moveLeft();
		checkInvariant();
		
		if((!(getCharBox().collidesWith(getEngine().getCharacter(other).getCharBox()))
				|| (getPositionX() == pre_positionX))
				){
			throw new PostconditionError("Collision with a changement of posX" + getPositionX() + "==" + pre_positionX);
		}
	}
	int getMyIndice(){
		if(getEngine().getCharacter(0) == this)
			return 0;
		return 1;
	}
	
	int getOtherIndice(){
		if(getEngine().getCharacter(0) == this)
			return 1;
		return 0;
	}
	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		int other = getOtherIndice();
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
		if(!(getPositionX() > 0 && getPositionX() < getEngine().getWidth())){
			throw new InvariantError("Erreur Character X " + getPositionX());
		}
		if(!(getPositionY() > 0 && getPositionY() < getEngine().getHeight())){
			throw new InvariantError("Erreur Character Y " + getPositionY());
		}
		if(!(isDead() == !(getLife()>0))){
			throw new InvariantError("Erreur Character not really dead kek");
		}
	}
}
