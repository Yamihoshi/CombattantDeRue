package engine.services;

public interface TechService {

	/* Constructors */
	public void init(String name, int damage, int hstun, int bstun, int sframe, int hframe, int rframe, HitboxService hitbox);
	
	/* Observators */
	public String getName();
	public int getDamage();
	public int getHit_stun();
	public int getBlock_stun();
	public int getStart_up_frame();
	public int getHit_frame();
	public int getRecovery_Frame();
	public HitboxService getHitbox();
	
	/* Operators */
}
