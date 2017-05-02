package engine.decorators;

import engine.services.ComboService;

public class ComboDecorator  implements ComboService{
	
	private ComboService combo;

	@Override
	public void step(boolean hit) {
		combo.step(hit);
	}

	public ComboDecorator(ComboService combo) {
		super();
		this.combo = combo;
	}

	public int getCombo() {
		return combo.getCombo();
	}

	public void addCombo() {
		combo.addCombo();
	}

	public int getFrameRestante() {
		return combo.getFrameRestante();
	}

	public void reset() {
		combo.reset();
	}

	public boolean comboPossible() {
		return combo.comboPossible();
	}

	public void removeFrame() {
		combo.removeFrame();
	}

	@Override
	public void init() {
		combo.init();
	}
}
