package engine.services;

import java.util.ArrayList;
import java.util.HashMap;

import engine.components.player.Commande;

public interface FightCharService extends CharacterService{

	
	/* Observators */
	public boolean isBlocking();
	public boolean isBlockStunned();
	public boolean isHitStunned();
	public boolean isTeching();
	public HashMap<Commande, TechService> getTech();
	public boolean techFrame(); // What does it do ?
	public boolean techHasAlreadyHit();
	
	/* Operators */
	public void startTech(TechService tech);
	public void endTechnique();
	public void takeAttack(int damage, int hstun, int bstun);
}
