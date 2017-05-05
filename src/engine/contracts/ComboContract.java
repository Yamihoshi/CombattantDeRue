package engine.contracts;

import engine.contracts.error.ContractError;
import engine.contracts.error.InvariantError;
import engine.contracts.error.PostconditionError;
import engine.decorators.ComboDecorator;
import engine.services.ComboService;

public class ComboContract extends ComboDecorator {

	public ComboContract(ComboService combo) {
		super(combo);
	}

	@Override
	public int getCombo() {
		return super.getCombo();
	}

	@Override
	public void addCombo() {
		checkInvariant();

		int combo = getCombo();
		super.addCombo();
		if(!(getCombo() == combo +1)){
			new PostconditionError("Erreur combo");
		}
		checkInvariant();

	}

	@Override
	public int getFrameRestante() {
		return super.getFrameRestante();
	}

	@Override
	public void reset() {
		checkInvariant();
		super.reset();
		if(!(getCombo() == 0))
			new PostconditionError("Combo non réinitialisé");
		checkInvariant();

	}

	@Override
	public boolean comboPossible() {
		checkInvariant();
		return super.comboPossible();
	}

	@Override
	public void removeFrame() {
		checkInvariant();
		int frame = getFrameRestante();
		super.removeFrame();
		if(!(frame - 1 == getFrameRestante())){
			new PostconditionError("Erreur de frame");
		}
		checkInvariant();
		
	}

	public void checkInvariant(){
		if(!(getCombo() >= 0)){
			new InvariantError("Erreur Combo");
		}
	}
}
