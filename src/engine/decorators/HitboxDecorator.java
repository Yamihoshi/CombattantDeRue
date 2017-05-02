package engine.decorators;

import engine.services.HitboxService;

public class HitboxDecorator implements HitboxService{
	HitboxService hitbox;
	
	public void setPositionX(int x) {
		hitbox.setPositionX(x);
	}

	public void setPositionY(int y) {
		hitbox.setPositionY(y);
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



	public void init(int x, int y, int h, int l) {
		hitbox.init(x, y, h, l);
	}

	public int getPositionX() {
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

	public void moveTo(int x, int y) {
		hitbox.moveTo(x, y);
	}

	@Override
	public void resize(int w, int h) {
		// TODO Auto-generated method stub
		
	}
}
