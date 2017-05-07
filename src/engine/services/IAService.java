package engine.services;

import engine.components.player.Commande;

public interface IAService extends PlayerService {
	public Commande getRandomCommande();
}
