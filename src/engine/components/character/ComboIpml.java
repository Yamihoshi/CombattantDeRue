package engine.components.character;

import engine.services.ComboService;

public class ComboIpml implements ComboService{
	
	private static final int FRAME_RESTANTE = 96;
	private int combo;
	private int frameRestante;
	
	
	public ComboIpml() {
		this.combo = 0;
		this.frameRestante = FRAME_RESTANTE;
	}
	@Override
	public int getCombo() {
		if(comboPossible()){
		}
		else{
			reset();
		}
		return combo;
	}

	@Override
	public int getFrameRestante() {
		return frameRestante;
	}

	@Override
	public void reset() {
		this.combo = 0;
	}
	
	public void step(boolean hit){
		if(!comboPossible()){
			reset();
		}
		if(hit){
			addCombo();
		}else{
			removeFrame();
		}
	}

	@Override
	public boolean comboPossible() {
		return frameRestante > 0;
	}

	@Override
	public void addCombo() {
		combo++;
		this.frameRestante = FRAME_RESTANTE;
	}

	@Override
	public void removeFrame() {
		frameRestante--;
	}
	@Override
	public void init() {
		this.combo = 0;
		this.frameRestante = FRAME_RESTANTE;		
	}


}
