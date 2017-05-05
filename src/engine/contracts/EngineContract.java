package engine.contracts;

import engine.components.player.Commande;
import engine.components.player.Player;
import engine.contracts.error.ContractError;
import engine.contracts.error.InvariantError;
import engine.contracts.error.PostconditionError;
import engine.contracts.error.PreconditionError;
import engine.decorators.EngineDecorator;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.components.character.FighterImpl;

public class EngineContract extends EngineDecorator{

	@Override
	public int getOtherIndice(int myId) {
		return super.getOtherIndice(myId);
	}

	@Override
	public int getMyIndice(int myId) {
		return super.getMyIndice(myId);
	}

	public EngineContract(EngineService delegate) {
		super(delegate);
	}

	@Override
	public void init(int h, int w, int s, Player j1, Player j2) {
		// TODO Auto-generated method stub
		if(!(h > 0 && w > 0 && s > 0 && w > s && !j1.equals(j2)))
			throw new PreconditionError("Erreur init engine");
		super.init(h, w, s, j1, j2);
		if(!(h== getHeight() && w == getWidth())){
			throw new PostconditionError("Erreur init engine dimension");
		}
		if(!(j1.equals(this.getPlayer(0)) && j2.equals(getPlayer(1))))
			throw new PostconditionError("Erreur init player");
		if(!(getCharacter(0).getPositionX() == (int) w/2 - (int)s/2 && getCharacter(1).getPositionX() == (int) w/2 + (int)s/2)){
			throw new PostconditionError("Erreur init char width");
		}
		if(!(getCharacter(0).getPositionY() ==  h-getCharacter(0).getHauteur() && getCharacter(1).getPositionY() == h-getCharacter(1).getHauteur())){
			throw new PostconditionError("Erreur init char height");
		}
		if(!(getCharacter(0).isFaceRight() && !getCharacter(1).isFaceRight())){
			throw new PostconditionError("Erreur init char face right");
		}
	}
	
	public void checkInvariant(){
		if(isGameOver()){
			if(!(getCharacter(0).isDead() || getCharacter(1).isDead())){
				throw new InvariantError("Personne morte");
			}
		}
		
		if(getCharacter(0).isFaceRight() == getCharacter(1).isFaceRight()){
			throw new ContractError("2 char in the same direction");
		}
		
		if(getCharacter(0).getPositionX() > getCharacter(1).getPositionX()){
			if(getCharacter(0).isFaceRight()){
				throw new ContractError("Erreur invariant faceRight");
			}
		}
		if(getCharacter(0).getPositionX() < getCharacter(1).getPositionX()){
			if(!getCharacter(0).isFaceRight()){
				throw new ContractError("Erreur invariant faceRight");
			}
		}
	}
	@Override
	public int getHeight() {
		return super.getHeight();
	}

	@Override
	public int getWidth() {
		return super.getWidth();
	}

	@Override
	public FightCharService getCharacter(int i) {
		if(!(i == 0 || i == 1)){
			throw new PreconditionError("Entier i =/= 0 | 1 ");
		}
		else{
			return super.getCharacter(i);
		}
	}

	@Override
	public Player getPlayer(int n) {
		if(!(n == 0 || n == 1)){
			throw new PreconditionError("Entier i =/= 0 | 1 ");
		}
		else{
			return super.getPlayer(n);
		}
	}

	@Override
	public boolean isGameOver() {
		return super.isGameOver();
	}

	@Override
	public void step(Commande c1, Commande c2) {
		checkInvariant();
		super.step(c1, c2);
		checkInvariant();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	 
	@Override
	public FightCharService[] getCharacters() {
		return super.getCharacters();
	}

	@Override
	public int getSpace() {
		return super.getSpace();
	}

	@Override
	public String toString() {
		return super.toString();
	}



}
