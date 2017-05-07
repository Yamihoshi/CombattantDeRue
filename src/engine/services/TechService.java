package engine.services;


public interface TechService {

	/* Constructors */
	public void init(int damage, int hstun, int bstun, int sframe, int hframe, int rframe, int debut_x, int debut_y,
			int width, int height);	
	/* Observators */
	public int getDamage();
	public int getHit_stun();
	public int getBlock_stun();
	public int getStart_up_frame();
	public int getHit_frame();
	public int getRecovery_Frame();
	public HitboxService getHitbox();
	public int getFrame();
	public int getNbHit();
	/* Operators */
	public void step(FightCharService me, FightCharService other);
	public void launchTechnique();
	public boolean isInStartUp();
	public boolean isInHit();
	public boolean isInRecovery();
	public boolean hasTouched();
}
