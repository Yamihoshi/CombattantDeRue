package engine.decorators;

import java.util.concurrent.atomic.AtomicInteger;

import engine.services.HitboxService;

public class HitboxDecorator implements HitboxService{
	HitboxService hitbox;
	
	public void setPositionX(int x) {
		hitbox.setPositionX(x);
	}

	public void setPositionY(int y) {
		hitbox.setPositionY(y);
	}

	public void moveTo(int new_x, int positionY) {
		hitbox.moveTo(new_x, positionY);
	}

	public void resize(int w, int h) {
		hitbox.resize(w, h);
	}

	public void setPositionX(AtomicInteger x) {
		hitbox.setPositionX(x);
	}


	public int getLargeur() {
		return hitbox.getLargeur();
	}

	public void setLargeur(int longueur) {
		hitbox.setLargeur(longueur);
	}

	public HitboxDecorator(HitboxService hitbox) {
		super();
		this.hitbox = hitbox;
	}

	public void setHauteur(int hauteur) {
		hitbox.setHauteur(hauteur);
	}

	public void init(AtomicInteger x, int y, int h, int l) {
		hitbox.init(x, y, h, l);
	}

	public AtomicInteger getPositionX() {
		return hitbox.getPositionX();
	}

	public int getPositionY() {
		return hitbox.getPositionY();
	}

	public int getHauteur() {
		return hitbox.getHauteur();
	}

	public boolean belongsTo(int x, int y) {
		return hitbox.belongsTo(x, y);
	}

	public boolean collidesWith(HitboxService other_hitbox) {
		return hitbox.collidesWith(other_hitbox);
	}

	public boolean equalsTo(HitboxService other_hitbox) {
		return hitbox.equalsTo(other_hitbox);
	}

	public void moveTo(AtomicInteger x, int y) {
		hitbox.moveTo(x, y);
	}


}
