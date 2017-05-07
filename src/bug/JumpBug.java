package bug;

import engine.components.character.Jump;
import engine.services.JumpService;

public class JumpBug  extends Jump implements JumpService{

	@Override
	public void launch() {
		super.launch();
	}

	@Override
	public boolean isLanding() {
		return false;
	}

}
