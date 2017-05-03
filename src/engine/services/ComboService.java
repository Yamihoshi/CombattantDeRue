package engine.services;

public interface ComboService {
	public int getCombo();
	public void addCombo();
	public int getFrameRestante();
	public void reset();
	public boolean comboPossible();
	public void removeFrame();
	public void init();
	public void step(boolean hit);
}


