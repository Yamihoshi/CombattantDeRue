package engine.components.hitbox;

import java.util.concurrent.atomic.AtomicInteger;

import engine.services.HitboxService;

public class HitboxImpl implements HitboxService{

	private AtomicInteger positionX;
	private AtomicInteger positionY;
	private int hauteur;
	private int largeur;
	
	public HitboxImpl() {
		
	}
	@Override
	public void init(AtomicInteger x, AtomicInteger y, int h, int l) {
		positionX =x;
		positionY = y;
		hauteur = h;
		largeur = l;
	}


	@Override
	public boolean belongsTo(int x, int y) {
		return(getPositionX().get() <= x && getPositionX().get() + getLargeur() >= x &&
				getPositionY().get() <= y && getPositionY().get() + getHauteur() >= y);
	}

	@Override
	public boolean collidesWith(HitboxService other_hitbox) {
		return(this.getPositionX().get() < other_hitbox.getPositionX().get()  + other_hitbox.getLargeur() &&
				this.getPositionX().get()  + this.getLargeur() > other_hitbox.getPositionX().get()  &&
				this.getPositionY().get()  < other_hitbox.getPositionY().get()  + other_hitbox.getHauteur() &&
				this.getPositionY().get()  + this.getHauteur() > other_hitbox.getPositionY().get() );
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
	public void moveTo(AtomicInteger x, AtomicInteger y) {
		this.positionX.set(x.get());
		this.positionY.set(y.get());
	}


	public AtomicInteger getPositionX() {
		return positionX;
	}


	public void setPositionX(AtomicInteger positionX) {
		setPositionX(positionX.get());
	}


	public AtomicInteger getPositionY() {
		return positionY;
	}


	public void setPositionY(AtomicInteger positionY) {
		setPositionY(positionY.get());
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
		this.positionY.set(positionY);
	}
	@Override
	public void setPositionX(int x) {
		this.positionX.set(x);
	}
	@Override
	public void setPositionY(int y) {
		this.positionY.set(y);
	}


}
