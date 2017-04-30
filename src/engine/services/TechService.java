package engine.services;


public interface TechService {

	/* Constructors */
	public void init(int damage, int hstun, int bstun, int sframe, int hframe, int rframe, HitboxService hitbox);
	
	/* Observators */
	public int getDamage();
	public int getHit_stun();
	public int getBlock_stun();
	public int getStart_up_frame();
	public int getHit_frame();
	public int getRecovery_Frame();
	public HitboxService getHitbox();

	/* Operators */
	public void step(FightCharService me, FightCharService other);
	public void launchTechnique();
}
