package engine.components.hitbox;

import java.util.concurrent.atomic.AtomicInteger;

import engine.services.HitboxService;

public class HitboxImpl implements HitboxService{

	private AtomicInteger positionX;
	private int positionY;
	private int hauteur;
	private int largeur;
	
	public HitboxImpl() {
		
	}
	@Override
	public void init(AtomicInteger x, int y, int h, int l) {
		positionX = x;
		positionY = y;
		hauteur = h;
		largeur = l;
	}


	@Override
	public boolean belongsTo(int x, int y) {
		return(getPositionX().get() <= x && getPositionX().get() + getLargeur() >= x &&
				getPositionY() <= y && getPositionY() + getHauteur() >= y);
	}

	@Override
	public boolean collidesWith(HitboxService other_hitbox) {
		return(this.getPositionX().get() < other_hitbox.getPositionX().get()  + other_hitbox.getLargeur() &&
				this.getPositionX().get()  + this.getLargeur() > other_hitbox.getPositionX().get()  &&
				this.getPositionY() < other_hitbox.getPositionY()  + other_hitbox.getHauteur() &&
				this.getPositionY()  + this.getHauteur() > other_hitbox.getPositionY());
	}

	@Override
	public boolean equalsTo(HitboxService other_hitbox) {
		return this.equals(other_hitbox);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hauteur;
		result = prime * result + largeur;

		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HitboxImpl other = (HitboxImpl) obj;
		if (hauteur != other.hauteur)
			return false;
		if (largeur != other.largeur)
			return false;
		if (positionX != other.positionX)
			return false;
		if (positionY != other.positionY)
			return false;
		return true;
	}


	@Override
	public void moveTo(AtomicInteger x, int y) {
		this.positionX.set(x.get());
		this.positionY = y;
	}


	public AtomicInteger getPositionX() {
		return positionX;
	}


	public void setPositionX(AtomicInteger positionX) {
		setPositionX(positionX.get());
	}


	public int getPositionY() {
		return positionY;
	}

	public int getHauteur() {
		return hauteur;
	}


	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	@Override
	public int getLargeur() {
		return largeur;
	}
	@Override
	public void setLargeur(int largeur) {
		this.largeur = largeur;	
	}
	@Override
	public void resize(int w, int h) {
		this.largeur = w;
		this.hauteur = h;
	}
	@Override
	public void moveTo(int new_x, int positionY) {
		this.positionX.set(new_x);
		this.positionY = positionY;
	}
	@Override
	public void setPositionX(int x) {
		this.positionX.set(x);
	}
	@Override
	public void setPositionY(int y) {
		this.positionY = y;
	}


}
