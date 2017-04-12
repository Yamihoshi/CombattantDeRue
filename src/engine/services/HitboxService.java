package engine.services;

public interface HitboxService {

	/* Constructors */
	public void init(int x, int y);
	
	/* Observators */
	public int getPositionX();
	public int getPositionY();
	public boolean belongsTo(int x, int y);
	public boolean collidesWith(HitboxService other_hitbox);
	public boolean equalsTo(HitboxService other_hitbox);
	
	/* Operators */
	public void moveTo(int x, int y);

}
