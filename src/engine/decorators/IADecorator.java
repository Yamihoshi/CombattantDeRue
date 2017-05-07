package engine.decorators;

import engine.components.player.Commande;
import engine.services.FightCharService;
import engine.services.IAService;
import engine.services.PlayerService;

public class IADecorator  implements IAService {
	
	public IADecorator(IAService player) {
		this.ia = (IAService) player;
	}

	private IAService ia;

	public FightCharService getCharacter() {
		return ia.getCharacter();
	}

	public void setCharacter(FightCharService character) {
		ia.setCharacter(character);
	}

	public Commande getRandomCommande() {
		return ia.getRandomCommande();
	}

	@Override
	public void init(FightCharService character) {
		ia.init(character);
	}
}
