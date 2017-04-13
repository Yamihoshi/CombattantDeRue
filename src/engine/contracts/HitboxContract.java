package engine.contracts;

import engine.contracts.error.PostconditionError;
import engine.contracts.error.PreconditionError;
import engine.decorators.HitboxDecorator;
import engine.services.HitboxService;

public class HitboxContract extends HitboxDecorator {

	public void checkInvariant(){
		
	}
	@Override
	public void init(int x, int y, int h, int l) {
		if(!(x > 0 && y > 0 && h > 0 && l>0))
			new PreconditionError("Error precondition init hitbox");
		super.init(x, y, h, l);
		
		if(!(x == getPositionX() && y == getPositionY() && h == getHauteur() && l == getLongueur())){
			new PostconditionError("Error postcondition init hitbox");
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
	public int getHauteur() {
		// TODO Auto-generated method stub
		return super.getHauteur();
	}

	@Override
	public int getLongueur() {
		// TODO Auto-generated method stub
		return super.getLongueur();
	}

	@Override
	public boolean belongsTo(int x, int y) {
		// TODO Auto-generated method stub
		return super.belongsTo(x, y);
	}

	@Override
	public boolean collidesWith(HitboxService other_hitbox) {
		// TODO Auto-generated method stub
		return super.collidesWith(other_hitbox);
	}

	@Override
	public boolean equalsTo(HitboxService other_hitbox) {
		// TODO Auto-generated method stub
		return super.equalsTo(other_hitbox);
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		checkInvariant();
		super.moveTo(x, y);
		checkInvariant();

		if(!(getPositionX() == x && getPositionY() == y)){
			new PostconditionError("Erreur MoveTo changement");
		}
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
