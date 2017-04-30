package engine.components.character;

import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.TechService;

public class Technique implements TechService {

	private int damage;
	private int hstun;
	private int bstun;
	private int sframe;
	private int rframe;
	private int hframe;
	private HitboxService hitbox;
	private int frame_actuel;
	private boolean already_touch;

	@Override
	public void init(int damage, int hstun, int bstun, int sframe, int hframe, int rframe,
			HitboxService hitbox) {
		this.damage = damage;
		this.hstun = hstun;
		this.bstun = bstun;
		this.sframe = sframe;
		this.hframe = hframe;
		this.rframe = rframe;
		this.hitbox = hitbox;
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public int getHit_stun() {
		return hstun;
	}

	@Override
	public int getBlock_stun() {
		return bstun;
	}

	@Override
	public int getStart_up_frame() {
		return sframe;
	}

	@Override
	public int getHit_frame() {
		return hframe;
	}

	@Override
	public int getRecovery_Frame() {
		// TODO Auto-generated method stub
		return rframe;
	}

	@Override
	public HitboxService getHitbox() {
		// TODO Auto-generated method stub
		return hitbox;
	}

	@Override
	public void step(FightCharService me, FightCharService other) {
		frame_actuel++;
		if(frame_actuel < getStart_up_frame()){
			
		}else if(frame_actuel < getStart_up_frame() + getHit_frame()){
			/*if(!already_touch && hitbox.collidesWith(other.getCharBox())){
				already_touch = true;
				other.takeAttack(damage, hstun, bstun);
			}*/
		}else if(frame_actuel < getStart_up_frame() + getHit_frame() + getRecovery_Frame()){
			//waiting
		}else{
			me.endTechnique();
		}
	}

	@Override
	public void launchTechnique() {
		this.frame_actuel = 0;
		this.already_touch = false;
	}

}
