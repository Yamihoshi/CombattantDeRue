package engine.components.character;

import java.util.HashMap;

import engine.components.hitbox.HitboxImpl;
import engine.components.player.Commande;
import engine.contracts.ComboContract;
import engine.contracts.HitboxContract;
import engine.services.ComboService;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.TechService; 

public class FighterImpl extends CharacterImpl implements FightCharService{

	protected TechService current_technique;
	protected HashMap<Commande, TechService> techniques;
	protected ComboService compteurCombo;
	protected int frame_stun = 0;
	protected int block_frame_stun = 0;

	
	@Override 
	public void init(Personnage personnage, int life, int speed, EngineService engine, boolean faceRight) {
		super.init(personnage, life, speed, engine, faceRight);
		this.techniques = new HashMap<>();
		this.compteurCombo = (ComboService) new ComboContract(new ComboIpml());
		this.compteurCombo.init();
	}
	
	@Override
	public void step(Commande c) {
	//	state_actuel = State.WAITING;
		if(isTeching()){
			current_technique.step(this, getOtherPlayer());
		}else if(isBlockStunned()){
			this.block_frame_stun--;
			switch (c) {
			case NEUTRAL:
				
				break;
			case DOWN:
				break;
			default:
				break;
			}
		}else if(isHitStunned()){
			this.frame_stun--;
		}else if(c == Commande.GUARD){
			state_actuel = State.BLOCKING;
		}
		else{
			state_actuel = State.WAITING;
			switch(c){
				case PUNCH:
					startTech(techniques.get(Commande.PUNCH));
					break;
				case KICK:
					startTech(techniques.get(Commande.KICK));
					break;
				case DOWN:
					break;
				case UP:
					break;
				case NEUTRAL:
					break;
				case LEFT:
					moveLeft();
					break;
				case RIGHT:
					moveRight();
					break;
				case DOWNLEFT:
					break;
				case DOWNRIGHT:
					break;
				case UPLEFT:
					break;
				case UPRIGHT:
					break;
				default:
					break;
				}
		}
		this.compteurCombo.step(false);
	}
	


	/*
	 * FightChar
	 * @see engine.services.FightCharService#startTech(engine.services.TechService)
	 */
	
	
	@Override
	public void startTech(TechService tech) {
		if(isTeching()){
			tech.step(this, getOtherPlayer());
		}else{
			state_actuel = State.TEACHING;
			tech.launchTechnique();
			tech.step(this, getOtherPlayer());
			current_technique = tech;
		}
	}
	
	@Override
	public void endTechnique() {
		state_actuel = State.WAITING;
	}
	@Override
	public void takeAttack(int damage, int hstun, int bstun) {
		state_actuel = State.WAITING;
		if(isBlocking()){
			block_frame_stun = bstun;
		}else{
			frame_stun = hstun;
		}
		this.vie -= damage;
		this.compteurCombo.reset();
	}	

	@Override
	public boolean isBlockStunned() {
		return block_frame_stun > 0;
	}

	@Override
	public boolean isHitStunned() {
		return frame_stun > 0;
	}

	@Override
	public HashMap<Commande, TechService> getTech() {
		return this.techniques;
	}

	
	@Override
	public TechService getCurrentTechnique() {
		return current_technique;
	}

	@Override
	public boolean isBlocking() {
		return state_actuel == State.BLOCKING;
	}

	@Override
	public boolean isTeching() {
		return state_actuel == State.TEACHING;
	}

	@Override
	public ComboService getComboService() {
		return compteurCombo;
	}

	@Override
	public boolean isCombo() {
		return getComboService().getCombo() > 0;
	}

	@Override
	public int getCombo() {
		return getComboService().getCombo();
	}

	@Override
	public void stepCombo(boolean hit) {
		getComboService().step(hit);
	}

}
