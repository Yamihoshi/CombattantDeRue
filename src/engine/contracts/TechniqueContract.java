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
		if(!(getFrame() <= getHit_frame() + getStart_up_frame() + getRecovery_Frame() )){
			throw new InvariantError("frame depassed...");
		}
		
		if(getNbHit() > 1){
			throw new InvariantError("Le joueur a été touché deux fois !");
		}
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
		return super.getHit_stun();
	}

	@Override
	public int getBlock_stun() {
		return super.getBlock_stun();
	}

	@Override
	public int getStart_up_frame() {
		return super.getStart_up_frame();
	}

	@Override
	public int getHit_frame() {
		return super.getHit_frame();
	}

	@Override
	public int getRecovery_Frame() {
		return super.getRecovery_Frame();
	}

	@Override
	public HitboxService getHitbox() {
		return super.getHitbox();
	}

	@Override
	public void step(FightCharService me, FightCharService other) {
		
		checkInvariant();
		
		int pre_frame = this.getFrame();
		int otherLife = other.getLife();
		
		if(!(this.getFrame()<this.getStart_up_frame()+this.getHit_frame()+this.getRecovery_Frame()+1))
			throw new PreconditionError("frame superior to all other" + getFrame());
		
		super.step(me, other);
		
		if(!(this.getFrame()==pre_frame+1))
			throw new PostconditionError("Frame Tech not incremented");
		boolean recovery = isInRecovery(), startUp = isInStartUp();
		if((recovery || startUp) && !(otherLife == other.getLife()) 
				){
			throw new PostconditionError("Life decreased in start up..");
		}
		
		checkInvariant();
	}

	@Override
	public void launchTechnique() {
		
		checkInvariant();
		
		super.launchTechnique();
		
		if(!(this.getFrame()==0))
			throw new PostconditionError("Launching Technique, frame no set on 0");
		
		checkInvariant();
	}

	@Override
	public boolean isInStartUp() {
		return super.isInStartUp();
	}

	@Override
	public boolean isInHit() {
		return super.isInHit();
	}

	@Override
	public boolean isInRecovery() {
		return super.isInRecovery();
	}

}
