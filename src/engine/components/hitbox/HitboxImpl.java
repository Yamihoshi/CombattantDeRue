package engine.components.hitbox;

import engine.services.HitboxService;

public class HitboxImpl implements HitboxService{

	private int positionX;
	private int positionY;
	private int hauteur;
	private int largeur;
	
	public HitboxImpl() {
		
	}
	@Override
	public void init(int x, int y, int h, int l) {
		positionX =x;
		positionY = y;
		hauteur = h;
		largeur = l;
	}


	@Override
	public boolean belongsTo(int x, int y) {
		return(getPositionX() <= x && getPositionX() + getLargeur() >= x &&
				getPositionY() <= y && getPositionY() + getHauteur() >= y);
	}

	@Override
	public boolean collidesWith(HitboxService other_hitbox) {
		return(this.getPositionX() < other_hitbox.getPositionX() + other_hitbox.getLargeur() &&
				this.getPositionX() + this.getLargeur() > other_hitbox.getPositionX() &&
				this.getPositionY() < other_hitbox.getPositionY() + other_hitbox.getHauteur() &&
				this.getPositionY() + this.getHauteur() > other_hitbox.getPositionY());
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
		result = prime * result + positionX;
		result = prime * result + positionY;
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
	public void moveTo(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}


	public int getPositionX() {
		return positionX;
	}


	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}


	public int getPositionY() {
		return positionY;
	}


	public void setPositionY(int positionY) {
		this.positionY = positionY;
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


}
