package engine.decorators;

import engine.services.CharacterService;
import engine.services.JumpService;

public class JumpDecorator implements JumpService{
	private JumpService jump;

	public JumpDecorator(JumpService jump2) {
		jump = jump2;
	}

	public int getFrameStartUp() {
		return jump.getFrameStartUp();
	}

	public int getFrameMoveUp() {
		return jump.getFrameMoveUp();
	}

	public int getFrameOnAir() {
		return jump.getFrameOnAir();
	}

	public int getFrameMoveDown() {
		return jump.getFrameMoveDown();
	}

	public int getFrameLanding() {
		return jump.getFrameLanding();
	}

	public int getFrame() {
		return jump.getFrame();
	}

	public boolean isStartUp() {
		return jump.isStartUp();
	}

	public boolean isMoveUp() {
		return jump.isMoveUp();
	}

	public boolean isOnAir() {
		return jump.isOnAir();
	}

	public boolean isMoveDown() {
		return jump.isMoveDown();
	}

	public boolean isLanding() {
		return jump.isLanding();
	}

	public void step(CharacterService other) {
		jump.step(other);
	}

	public void launch() {
		jump.launch();
	}

	public void init(int startUp, int moveUp, int onAir, int moveDown, int landing, int vitesse_x, int vitesse_y,
			CharacterService joueur) {
		jump.init(startUp, moveUp, onAir, moveDown, landing, vitesse_x, vitesse_y, joueur);
	}
}
