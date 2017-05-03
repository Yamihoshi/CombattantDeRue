package engine.components.character;

import java.util.concurrent.atomic.AtomicInteger;

import engine.components.hitbox.HitboxImpl;
import engine.contracts.HitboxContract;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.JumpService;
import engine.services.TechService;

public class Jump implements JumpService{

	private int startUp;
	private int moveUp;
	private int onAir;
	private int moveDown;
	private int landing;
	private int vitesseX;
	private int vitesseY;
	private int frameRestante;
	
	
	@Override
	public int getFrameStartUp() {
		return startUp;
	}

	@Override
	public int getFrameMoveUp() {
		return moveUp;
	}

	@Override
	public int getFrameOnAir() {
		return onAir;
	}

	@Override
	public int getFrameMoveDown() {
		return moveDown;
	}

	@Override
	public int getFrameLanding() {
		return landing;
	}

	@Override
	public int getFrame() {
		return frameRestante;
	}


	@Override
	public void launch() {
		frameRestante = 0;
		vitesseX = 0;
		
	}

	@Override
	public void step(HitboxService me, HitboxService other_hitbox) {
		HitboxService tmp = new HitboxImpl();
		tmp.init(new AtomicInteger(me.getPositionX().get()), me.getPositionY(), me.getHauteur(), me.getLargeur());
		System.out.println("je passe");
		if(isStartUp()){
			
		}else if(isMoveUp()){
			me.moveTo(tmp.getPositionX().get() + vitesseX, tmp.getPositionY() - vitesseY);
		}else if(isOnAir()){
			
		}else if(isMoveDown()){
			tmp.moveTo(tmp.getPositionX().get() + vitesseX, tmp.getPositionY() - vitesseY);
			if(!tmp.collidesWith(other_hitbox)){
				me.moveTo(tmp.getPositionX().get() + vitesseX, tmp.getPositionY() - vitesseY);
			}
		}else if(isLanding()){
			
		}else{
			CharacterImpl.jumping = false;
		}
		frameRestante++;
	}

	@Override
	public boolean isStartUp() {
		return frameRestante < getFrameStartUp();
	}

	@Override
	public boolean isMoveUp() {
		return frameRestante < getFrameStartUp() + getFrameMoveUp();
	}

	@Override
	public boolean isOnAir() {
		return frameRestante < getFrameStartUp() + getFrameMoveUp() + getFrameOnAir();
	}

	@Override
	public boolean isMoveDown() {
		return frameRestante < getFrameStartUp() + getFrameMoveUp() + getFrameOnAir() + getFrameMoveDown();
	}
	

	@Override
	public boolean isLanding() {
		return frameRestante < getFrameStartUp() + getFrameMoveUp() + getFrameOnAir() + getFrameMoveDown() + getFrameLanding();
	}



	@Override
	public void init(int startUp, int moveUp, int onAir, int moveDown, int landing, int vitesse_x, int vitesse_y) {
		this.startUp = startUp;
		this.moveUp = moveUp;
		this.onAir = onAir;
		this.moveDown = moveDown;
		this.landing = landing;
		this.vitesseX = vitesse_x;
		this.vitesseY = vitesse_y;
		this.frameRestante = frameRestante;
	}




}
