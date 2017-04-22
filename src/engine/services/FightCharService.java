package engine.services;

public interface FightCharService extends CharacterService{

	
	/* Observators */
	public boolean isBlocking();
	public boolean isBlockStunned();
	public boolean isHitStunned();
	public boolean isTeching();
	public TechService getTech();
	public boolean techFrame(); // What does it do ?
	public boolean techHasAlreadyHit();
	
	/* Operators */
	public void startTech(TechService tech);
}
