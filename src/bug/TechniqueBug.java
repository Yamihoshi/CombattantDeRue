package bug;

import engine.components.character.Technique;
import engine.services.FightCharService;
import engine.services.TechService;

public class TechniqueBug extends Technique implements TechService{

	@Override
	public void step(FightCharService me, FightCharService other) {
		// TODO Auto-generated method stub
		super.step(me, other);
	}

	@Override
	public void launchTechnique() {
		frame_actuel = 20;
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
		return false;
	}

}
