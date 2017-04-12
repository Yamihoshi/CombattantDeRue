package engine.contracts;

import engine.components.player.Player;
import engine.contracts.error.PostconditionError;
import engine.contracts.error.PreconditionError;
import engine.decorators.EngineDecorator;
import engine.services.CommandeService;
import engine.services.EngineService;

public class EngineContract extends EngineDecorator{

	public EngineContract(EngineService delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(int h, int w, int s, Player j1, Player j2) {
		// TODO Auto-generated method stub
		if(!(h > 0 && w > 0 && s > 0 && w > s && j1.equals(j2)))
			new PreconditionError("Erreur init engine");
		super.init(h, w, s, j1, j2);
		if(!(h== getHeight() && w == getWidth())){
			new PostconditionError("Erreur init engine dimension");
		}
		if(!(j1.equals(this.getPlayer(0)) && j2.equals(getPlayer(1))))
			new PostconditionError("Erreur init player");
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return super.getHeight();
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return super.getWidth();
	}

	@Override
	public Character getCharacter(int n) {
		// TODO Auto-generated method stub
		return super.getCharacter(n);
	}

	@Override
	public Player getPlayer(int n) {
		// TODO Auto-generated method stub
		return super.getPlayer(n);
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return super.isGameOver();
	}

	@Override
	public void step(CommandeService c1, CommandeService c2) {
		// TODO Auto-generated method stub
		super.step(c1, c2);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}



}
