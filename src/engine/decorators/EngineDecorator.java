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

	@Override
	public int height() {
		return this.delegate.height();
	}

	@Override
	public int width() {
		return this.delegate.width();
	}

	@Override
	public Character chara(int n) {
		return this.delegate.chara(n);
	}

	@Override
	public Player player(int n) {
		return this.delegate.player(n);
	}

	@Override
	public boolean gameOver() {
		return this.delegate.gameOver();
	}

	@Override
	public void step(CommandeService c1, CommandeService c2) {
		this.delegate.step(c1, c2);
	}

}
