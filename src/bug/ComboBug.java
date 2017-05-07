package bug;

import engine.components.character.ComboIpml;
import engine.services.ComboService;

public class ComboBug extends  ComboIpml implements ComboService {

	@Override
	public void reset() {
		super.addCombo();
	}

	@Override
	public void addCombo() {
		super.reset();
	}
}
