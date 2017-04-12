package engine.decorators;

import engine.components.player.Commande;
import engine.components.player.Player;
import engine.services.CommandeService;
import engine.services.EngineService;

public class EngineDecorator implements EngineService{

	private final EngineService delegate;
	
	public EngineDecorator(EngineService delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public void init(int h, int w, int s, Player j1, Player j2) {
		this.delegate.init(h, w, s, j1, j2);
	}

	public int getHeight() {
		return delegate.getHeight();
	}

	public int getWidth() {
		return delegate.getWidth();
	}

	public Character getCharacter(int n) {
		return delegate.getCharacter(n);
	}

	public Player getPlayer(int n) {
		return delegate.getPlayer(n);
	}

	public boolean isGameOver() {
		return delegate.isGameOver();
	}

	@Override
	public void step(CommandeService c1, CommandeService c2) {
		this.delegate.step(c1, c2);
	}

}
