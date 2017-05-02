package engine.contracts;

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
		super.addCombo();
	}

	@Override
	public int getFrameRestante() {
		return super.getFrameRestante();
	}

	@Override
	public void reset() {
		super.reset();
		if(!(getCombo() == 0))
			new PostconditionError("Combo non réinitialisé");
	}

	@Override
	public boolean comboPossible() {
		checkInvariant();
		return super.comboPossible();
	}

	@Override
	public void removeFrame() {
		int frame = getFrameRestante();
		super.removeFrame();
		if(!(frame - 1 == getFrameRestante())){
			new PostconditionError("Erreur de frame");
		}
		
	}

	public void checkInvariant(){
		if(!(getCombo() > 0)){
			new InvariantError("Erreur Combo");
		}
	}
}
