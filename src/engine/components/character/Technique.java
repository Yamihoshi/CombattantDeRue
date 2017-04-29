package engine.components.character;

import engine.services.HitboxService;
import engine.services.TechService;

public class Technique implements TechService {

	private String name;
	private int damage;
	private int hstun;
	private int bstun;
	private int sframe;
	private int rframe;
	private int hframe;
	private HitboxService hitbox;

	@Override
	public void init(String name, int damage, int hstun, int bstun, int sframe, int hframe, int rframe,
			HitboxService hitbox) {
		this.name = name;
		this.damage = damage;
		this.hstun = hstun;
		this.bstun = bstun;
		this.sframe = sframe;
		this.hframe = hframe;
		this.rframe = rframe;
		this.hitbox = hitbox;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return damage;
	}

	@Override
	public int getHit_stun() {
		// TODO Auto-generated method stub
		return hstun;
	}

	@Override
	public int getBlock_stun() {
		// TODO Auto-generated method stub
		return bstun;
	}

	@Override
	public int getStart_up_frame() {
		// TODO Auto-generated method stub
		return sframe;
	}

	@Override
	public int getHit_frame() {
		// TODO Auto-generated method stub
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

}
