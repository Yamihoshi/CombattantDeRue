package engine.contracts;

import engine.components.player.Commande;
import engine.contracts.error.InvariantError;
import engine.contracts.error.PostconditionError;
import engine.contracts.error.PreconditionError;
import engine.decorators.CharacterDecorator;
import engine.services.EngineService;
import engine.services.HitboxService;

public class CharacterContract extends CharacterDecorator {

	@Override
	public void init(int l, int s, EngineService engine, boolean faceRight) {
		if(!(l > 0 && s>0)){
			new PreconditionError("Erreur init precondition character");
		}
		super.init(l, s, engine, faceRight);
		if(!(getLife() == l && getSpeed() == s && isFaceRight() == faceRight && getEngine().equals(engine))){
			new PostconditionError("Erreur post condition error");
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
		
		checkInvariant();
		super.moveLeft();
		checkInvariant();
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
		checkInvariant();
		super.moveRight();
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
			new PostconditionError("Erreur post condition error switchSide");
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
			new InvariantError("Erreur Characteur X");
		}
		if(!(getPositionY() > 0 && getPositionY() < getEngine().getHeight())){
			new InvariantError("Erreur Characteur Y");
		}
		if(!(isDead() == !(getLife()>0))){
			new InvariantError("Erreur Characteur Y");
		}
	}
}
