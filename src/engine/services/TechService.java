package engine.services;

public interface TechService {

	/* Constructors */
	public void init();
	
	/* Observators */
	public int damage();
	public int hstun();
	public int bstun();
	public int sframe();
	public int hframe();
	public int rframe();
	public HitboxService hitbox(int x, int y); //x et y en param WHAT ?
	
	/* Operators */
}
