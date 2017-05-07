package engine.decorators;

import engine.components.player.Commande;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.PlayerService;

public class EngineDecorator implements EngineService{



	private final EngineService delegate;
	
	public int getOtherIndice(int myId) {
		return delegate.getOtherIndice(myId);
	}

	public int getMyIndice(int myId) {
		return delegate.getMyIndice(myId);
	}

	public FightCharService[] getCharacters() {
		return delegate.getCharacters();
	}

	public EngineDecorator(EngineService delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public void init(int h, int w, int s, PlayerService j1, PlayerService j2) {
		this.delegate.init(h, w, s, j1, j2);
	}

	public int getHeight() {
		return delegate.getHeight();
	}

	public int getWidth() {
		return delegate.getWidth();
	}

	public FightCharService getCharacter(int n) {
		return delegate.getCharacter(n);
	}

	public PlayerService getPlayer(int n) {
		return delegate.getPlayer(n);
	}

	public boolean isGameOver() {
		return delegate.isGameOver();
	}

	@Override
	public void step(Commande c1, Commande c2) {
		this.delegate.step(c1, c2);
	}

	@Override
	public int getSpace() {
		return delegate.getSpace();
	}

}
