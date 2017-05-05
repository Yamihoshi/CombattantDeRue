package engine.components.character;

import java.util.concurrent.atomic.AtomicInteger;

import engine.components.hitbox.HitboxImpl;
import engine.contracts.HitboxContract;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.TechService;
import game.StreetFighterGame;

public class Technique implements TechService {

	private int damage;
	private int hstun;
	private int bstun;
	private int sframe;
	private int rframe;
	private int hframe;
	private int saveWidth;
	private int saveHeight;
	private int debut_x;
	private int debut_y;
	protected HitboxService hitbox;
	protected int frame_actuel;
	private boolean already_touch;



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
		return rframe;
	}

	@Override
	public HitboxService getHitbox() {
		return hitbox;
	}

	@Override
	public void step(FightCharService me, FightCharService other) {
		if(isInStartUp()){
		}else if(isInHit()){
			if(me.isFaceRight())
				hitbox.setPositionX(this.debut_x + me.getPositionX());
			else{
				hitbox.setPositionX(me.getPositionX() - this.saveWidth);
			}
			hitbox.setPositionY(this.debut_y + me.getPositionY());
			
			if(!already_touch && hitbox.collidesWith(other.getCharBox())){
				already_touch = true;
				other.takeAttack(damage, hstun, bstun);
				me.stepCombo(true);
				//System.out.println("Touché !!!!!! Combo en cours " + me.getCombo());
			}
		}else if(isInRecovery()){
			//waiting
		}else{
			me.endTechnique();
		}
		frame_actuel++;

	}

	@Override
	public void launchTechnique() {
		this.frame_actuel = 0;
		this.already_touch = false;
	}

	@Override
	public void init(int damage, int hstun, int bstun, int sframe, int hframe, int rframe, int debut_x, int debut_y,
			int width, int height) {
		this.damage = damage;
		this.hstun = hstun;
		this.bstun = bstun;
		this.sframe = sframe;
		this.hframe = hframe;
		this.rframe = rframe;
		this.saveHeight = height;
		this.saveWidth = width;
		this.debut_x = debut_x;
		this.debut_y = debut_y;
		this.hitbox = new HitboxContract(new HitboxImpl());
		hitbox.init(new AtomicInteger(debut_x), debut_y, saveHeight, saveWidth);
		
	}

	@Override
	public boolean isInStartUp() {
		return frame_actuel < getStart_up_frame();
	}

	@Override
	public boolean isInHit() {
		return frame_actuel < getStart_up_frame() + getHit_frame();
	}

	@Override
	public boolean isInRecovery() {
		return frame_actuel < getStart_up_frame() + getHit_frame() + getRecovery_Frame() &&!isInHit();
	}

	@Override
	public int getFrame() {
		return frame_actuel;
	}

	@Override
	public boolean hasTouched() {
		// TODO Auto-generated method stub
		return already_touch;
	}

}
