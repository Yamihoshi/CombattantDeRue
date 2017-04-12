package engine.contracts;

import engine.decorators.EngineDecorator;
import engine.services.EngineService;

public class EngineContract extends EngineDecorator{

	public EngineContract(EngineService delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

}
