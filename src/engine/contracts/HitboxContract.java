package engine.contracts;

import java.util.concurrent.atomic.AtomicInteger;

import engine.contracts.error.InvariantError;
import engine.contracts.error.PostconditionError;
import engine.contracts.error.PreconditionError;
import engine.decorators.HitboxDecorator;
import engine.services.HitboxService;

public class HitboxContract extends HitboxDecorator {

	public HitboxContract(HitboxService hitbox) {
		super(hitbox);
	}
	
	@Override
	public void resize(int w, int h) {
		if(!(w > 0 && h > 0))
			new PreconditionError("Mauvaise width/hauteur");
		checkInvariant();
		super.resize(w, h);
		checkInvariant();
		if(!(getHauteur() == h)){
			new PostconditionError("hauteur non set");
		}
		if(!(getLargeur() == w)){
			new PostconditionError("Largeur non set");
		}
	}

    public void checkInvariant() {
	// inv: getWidth() > 0
	if (!(getPositionX().get() > 0))
	    throw new InvariantError("X négative");
	// inv: getWidth() > 0
	if (!(getPositionY() > 0))
	    throw new InvariantError("Y négative");
	if(!(getLargeur() > 0))
	    throw new InvariantError("Largeur négative");
	if(!(getHauteur() > 0))
	    throw new InvariantError("Hauteur négative");
}
	@Override
	public void setPositionX(AtomicInteger x) {
		if(x.get() < 0){
			new PreconditionError("Position négative");
		}
		checkInvariant();
		super.setPositionX(x);
		checkInvariant();
		if(!(getPositionX().get() == x.get())){
			new PostconditionError("Set non effectué");
		}
	}
	@Override
	public void setPositionY(int y) {
		if(y < 0){
			new PreconditionError("Position négative");
		}
		checkInvariant();
		super.setPositionY(y);
		checkInvariant();
		if(!(getPositionY() == y)){
			new PostconditionError("Set non effectué");
		}
	}
	@Override
	public int getLargeur() {
		return super.getLargeur();
	}
	@Override
	public void setLargeur(int largeur) {
		if(getLargeur() < 0){
			new PreconditionError("largeur négative");
		}
		checkInvariant();
		super.setLargeur(largeur);
		checkInvariant();
		if(!(getLargeur() == largeur)){
			new PostconditionError("Set non effectué");
		}
	}
	@Override
	public void setHauteur(int hauteur) {
		if(hauteur < 0){
			new PreconditionError("hauteur négative");
		}
		checkInvariant();
		super.setHauteur(hauteur);
		checkInvariant();
		if(!(getHauteur() == hauteur)){
			new PostconditionError("Set non effectué");
		}
	}

	@Override
	public void init(AtomicInteger x, int y, int h, int l) {
		if(!(x.get() > 0 && y > 0 && h > 0 && l>0))
			new PreconditionError("Error precondition init hitbox");
		super.init(x, y, h, l);
		
		if(!(x.get() == getPositionX().get() && y == getPositionY() && h == getHauteur() && l == getLargeur())){
			new PostconditionError("Error postcondition init hitbox");
		}
	}

	@Override
	public AtomicInteger getPositionX() {
		return super.getPositionX();
	}

	@Override
	public int getPositionY() {
		return super.getPositionY();
	}

	@Override
	public int getHauteur() {
		return super.getHauteur();
	}


	@Override
	public boolean belongsTo(int x, int y) {
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
	public void moveTo(AtomicInteger x, int y) {
		// TODO Auto-generated method stub
		checkInvariant();
		super.moveTo(x, y);
		checkInvariant();

		if(!(getPositionX().get() == x.get() && getPositionY() == y)){
			new PostconditionError("Erreur MoveTo changement");
		}
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
