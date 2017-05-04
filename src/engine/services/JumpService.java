package engine.services;

public interface JumpService {
	public int getFrameStartUp();
	public int getFrameMoveUp();
	public int getFrameOnAir();
	public int getFrameMoveDown();
	public int getFrameLanding();
	public int getFrame();
	
	public boolean isStartUp();
	public boolean isMoveUp();
	public boolean isOnAir();
	public boolean isMoveDown();
	public boolean isLanding();
	public void step(CharacterService other);
	public void launch();
	
	public void init(int startUp, int moveUp, int onAir, int moveDown, int landing, int vitesse_x, int vitesse_y, CharacterService joueur);
}
