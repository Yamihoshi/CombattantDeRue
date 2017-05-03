package engine.components.character;

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
	}

	@Override
	public void step(HitboxService me, HitboxService other) {
		if(isStartUp()){
			
		}else if(isMoveUp()){
			
		}else if(isOnAir()){
		
		}else if(isMoveDown()){
			
		}else if(isLanding()){
			
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
