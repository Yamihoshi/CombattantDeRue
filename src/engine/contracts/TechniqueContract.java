package engine.contracts;

import engine.decorators.TechniqueDecorator;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.TechService;

public class TechniqueContract extends TechniqueDecorator {

	public TechniqueContract(TechService t) {
		super(t);
	}

	@Override
	public void init(int damage, int hstun, int bstun, int sframe, int hframe, int rframe, int debut_x, int debut_y,
			int width, int height) {
		// TODO Auto-generated method stub
		super.init(damage, hstun, bstun, sframe, hframe, rframe, debut_x, debut_y, width, height);
	}

	@Override
	public int getDamage() {
		return super.getDamage();
	}

	@Override
	public int getHit_stun() {
		// TODO Auto-generated method stub
		return super.getHit_stun();
	}

	@Override
	public int getBlock_stun() {
		// TODO Auto-generated method stub
		return super.getBlock_stun();
	}

	@Override
	public int getStart_up_frame() {
		// TODO Auto-generated method stub
		return super.getStart_up_frame();
	}

	@Override
	public int getHit_frame() {
		// TODO Auto-generated method stub
		return super.getHit_frame();
	}

	@Override
	public int getRecovery_Frame() {
		// TODO Auto-generated method stub
		return super.getRecovery_Frame();
	}

	@Override
	public HitboxService getHitbox() {
		return super.getHitbox();
	}

	@Override
	public void step(FightCharService me, FightCharService other) {
		// TODO Auto-generated method stub
		super.step(me, other);
	}

	@Override
	public void launchTechnique() {
		// TODO Auto-generated method stub
		super.launchTechnique();
	}

	@Override
	public boolean isInStartUp() {
		// TODO Auto-generated method stub
		return super.isInStartUp();
	}

	@Override
	public boolean isInHit() {
		// TODO Auto-generated method stub
		return super.isInHit();
	}

	@Override
	public boolean isInRecovery() {
		// TODO Auto-generated method stub
		return super.isInRecovery();
	}

}
