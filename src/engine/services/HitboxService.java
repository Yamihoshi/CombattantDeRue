package engine.services;

import java.util.concurrent.atomic.AtomicInteger;

public interface HitboxService {

	/* Constructors */
	public void init(AtomicInteger x, int y, int h, int l);
	
	/* Observators */
	public AtomicInteger getPositionX();
	public int getPositionY();
	public int getHauteur();
	public int getLargeur();
	public void setHauteur(int hauteur);
	public void setLargeur(int longueur);
	public void setPositionX(AtomicInteger x);
	public void setPositionX(int x);
	public void setPositionY(int y);
	public boolean belongsTo(int x, int y);
	public boolean collidesWith(HitboxService other_hitbox);
	public boolean equalsTo(HitboxService other_hitbox);
	
	/* Operators */
	public void moveTo(AtomicInteger x, int y);
	public void resize(int w,int h);

	public void moveTo(int new_x, int positionY);

	//public boolean isOutside();

}
