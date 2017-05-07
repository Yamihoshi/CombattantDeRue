package engine.contracts;

import engine.components.player.Player;
import engine.decorators.IADecorator;
import engine.services.IAService;
import engine.services.PlayerService;

public class IAContract extends IADecorator{
	
	
	public IAContract(PlayerService player) {
		super((IAService) player);
	}

	public IAContract(IAService player) {
		super(player);
	}
}
