package engine.impl;

import engine.components.player.Commande;
import engine.services.PlayerService;

public class EngineBugImpl extends EngineImpl{

	@Override
	public boolean isGameOver() {
		for(int i = 0; i < NOMBRE_PERSO; i++){
			if(getCharacter(i).isBlocking())
				return true;
		}
		return false;
	}
	
	//Inversion des commandes
	@Override
	public void step(Commande c1, Commande c2) {
		this.getCharacter(0).step(c2);
		this.getCharacter(1).step(c1);
	}
}
