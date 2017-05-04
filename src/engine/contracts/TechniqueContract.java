package engine.contracts;

import engine.contracts.error.ContractError;
import engine.contracts.error.InvariantError;
import engine.contracts.error.PostconditionError;
import engine.contracts.error.PreconditionError;
import engine.decorators.TechniqueDecorator;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.TechService;

public class TechniqueContract extends TechniqueDecorator {

	public TechniqueContract(TechService t) {
		super(t);
	}
	
	public void checkInvariant(){
		
	}

	@Override
	public void init(int damage, int hstun, int bstun, int sframe, int hframe, int rframe, int debut_x, int debut_y,
			int width, int height) {
		
		if(!(damage > 0 && hstun > 0 && bstun >0 && sframe >0 && hframe > 0 && rframe>0 && width>0 && height>0))
			 throw new PreconditionError("Erreur init technique");
		
		super.init(damage, hstun, bstun, sframe, hframe, rframe, debut_x, debut_y, width, height);
		
		if(!(damage == getDamage() && hstun == getHit_stun() && sframe == getStart_up_frame() && hframe == getHit_frame()
				&& rframe == getRecovery_Frame())){
			 throw new PostconditionError("Erreur init technique");
		}
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
		
		checkInvariant();
		
		//int pre_frame = this.getFrame();
		
		/*if(!(this.getFrame()<this.getStart_up_frame()+this.getHit_frame()+this.getRecovery_Frame()))
			throw new PreconditionError("");*/
		
		super.step(me, other);
		
		/*if(!(this.getFrame()==pre_frame+1))
			throw new PostconditionError("Frame Tech not incremented");*/
		
		checkInvariant();
	}

	@Override
	public void launchTechnique() {
		// TODO Auto-generated method stub
		
		checkInvariant();
		
		super.launchTechnique();
		
		/*if(!(this.getFrame()==0))
			throw new PostconditionError("Launching Technique, frame no set on 0");*/
		
		checkInvariant();
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
