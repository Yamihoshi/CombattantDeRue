package engine.decorators;

import engine.services.FightCharService;
import engine.services.PlayerService;

public class PlayerDecorator implements PlayerService{

	private PlayerService service;

	public PlayerDecorator(PlayerService service) {
		super();
		this.service = service;
	}

	public FightCharService getCharacter() {
		return service.getCharacter();
	}

	public void setCharacter(FightCharService character) {
		service.setCharacter(character);
	}

	@Override
	public void init(FightCharService character) {
		service.init(character);
		
	}
}
