package engine.contracts;

import engine.components.player.Commande;
import engine.components.player.Player;
import engine.contracts.error.PostconditionError;
import engine.contracts.error.PreconditionError;
import engine.decorators.EngineDecorator;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.components.character.CharacterImpl;

public class EngineContract extends EngineDecorator{

	public EngineContract(EngineService delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}
 
	@Override
	public FightCharService[] getCharacters() {
		// TODO Auto-generated method stub
		return super.getCharacters();
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
		if(!(getCharacter(0).getPositionX() == (int) w/2 - (int)s/2 && getCharacter(1).getPositionX() == (int) w/2 + (int)s/2)){
			new PostconditionError("Erreur init char width");
		}
		if(!(getCharacter(0).getPositionY() == 0 && getCharacter(1).getPositionY() == 0)){
			new PostconditionError("Erreur init char height");
		}
		if(!(getCharacter(0).isFaceRight() && !getCharacter(1).isFaceRight())){
			new PostconditionError("Erreur init char face right");
		}
	}
	
	public boolean checkInvariant(){
		return true;
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
	public FightCharService getCharacter(int i) {
		if(!(i == 0 || i == 1)){
			new PreconditionError("Entier i =/= 0 | 1 ");
			return null;
		}
		else{
			return super.getCharacter(i);
		}
	}

	@Override
	public Player getPlayer(int n) {
		if(!(n == 0 || n == 1)){
			new PreconditionError("Entier i =/= 0 | 1 ");
			return null;
		}
		else{
			return super.getPlayer(n);
		}
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return super.isGameOver();
	}

	@Override
	public void step(Commande c1, Commande c2) {
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
