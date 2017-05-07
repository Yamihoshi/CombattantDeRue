package engine.contracts;

import engine.decorators.PlayerDecorator;
import engine.services.PlayerService;

public class PlayerContract extends PlayerDecorator{

	public PlayerContract(PlayerService service) {
		super(service);
	}

}
