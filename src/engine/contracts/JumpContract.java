package engine.contracts;

import engine.components.character.Jump;
import engine.contracts.error.InvariantError;
import engine.contracts.error.PostconditionError;
import engine.contracts.error.PreconditionError;
import engine.decorators.JumpDecorator;
import engine.services.CharacterService;
import engine.services.JumpService;

public class JumpContract extends JumpDecorator{

	public JumpContract(JumpService jump) {
		super(jump);
	}

	@Override
	public void launch() {
		super.launch();
		if(getFrame() != 0)
			throw new PostconditionError("Frame dif de 0");
		invariantError();
	}
	public void invariantError(){
		if(getFrame() > getFrameLanding() + getFrameMoveDown() + getFrameMoveUp() + getFrameStartUp() + getFrameOnAir())
			throw new InvariantError("Frame up");
	}
	@Override
	public void init(int startUp, int moveUp, int onAir, int moveDown, int landing, int vitesse_x, int vitesse_y,
			CharacterService joueur) {
		if(!(startUp > 0 && moveDown > 0 && onAir > 0 && moveDown > 0 && landing > 0 && vitesse_x > 0 && vitesse_y > 0 && joueur != null)){
			throw new PreconditionError("Erreur init");
		}
		
		if(moveDown != moveUp){
			throw new PreconditionError("Not the same");
		}
		invariantError();

		super.init(startUp, moveUp, onAir, moveDown, landing, vitesse_x, vitesse_y, joueur);
		invariantError();

		if(startUp != getFrameStartUp() && moveDown != getFrameMoveDown() && onAir != getFrameOnAir() && landing != getFrameLanding()){
			throw new PostconditionError("Erreur init");
		}
		
	}
}