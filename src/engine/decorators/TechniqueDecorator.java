package engine.decorators;

import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.TechService;

public class TechniqueDecorator implements TechService{
	private TechService technique;

	public boolean hasTouched() {
		return technique.hasTouched();
	}

	public TechniqueDecorator(TechService t) {
		technique = t;
	}

	public void init(int damage, int hstun, int bstun, int sframe, int hframe, int rframe, int debut_x, int debut_y,
			int width, int height) {
		technique.init(damage, hstun, bstun, sframe, hframe, rframe, debut_x, debut_y, width, height);
	}

	public int getDamage() {
		return technique.getDamage();
	}

	public int getHit_stun() {
		return technique.getHit_stun();
	}

	public int getBlock_stun() {
		return technique.getBlock_stun();
	}

	public int getStart_up_frame() {
		return technique.getStart_up_frame();
	}

	public int getHit_frame() {
		return technique.getHit_frame();
	}

	public int getRecovery_Frame() {
		return technique.getRecovery_Frame();
	}

	public HitboxService getHitbox() {
		return technique.getHitbox();
	}

	public void step(FightCharService me, FightCharService other) {
		technique.step(me, other);
	}

	public void launchTechnique() {
		technique.launchTechnique();
	}

	public boolean isInStartUp() {
		return technique.isInStartUp();
	}

	public boolean isInHit() {
		return technique.isInHit();
	}

	public boolean isInRecovery() {
		return technique.isInRecovery();
	}

	@Override
	public int getFrame() {
		return technique.getFrame();
	}
}
