package engine.services;

public interface HitboxService {

	/* Constructors */
	public void init(int x, int y, int h, int l);
	
	/* Observators */
	public int getPositionX();
	public int getPositionY();
	public int getHauteur();
	public int getLargeur();
	public void setHauteur(int hauteur);
	public void setLargeur(int longueur);
	public void setPositionX(int x);
	public void setPositionY(int y);
	public boolean belongsTo(int x, int y);
	public boolean collidesWith(HitboxService other_hitbox);
	public boolean equalsTo(HitboxService other_hitbox);
	
	/* Operators */
	public void moveTo(int x, int y);
	public void resize(int w,int h);

	//public boolean isOutside();

}
