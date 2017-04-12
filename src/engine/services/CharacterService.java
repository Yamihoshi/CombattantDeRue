package engine.services;

public interface CharacterService {

	/* Constructors */
	public void init(int x, int y, EngineService engine ); // What does they mean by this ?
	
	/* Observators */
	public int getPositionX();
	public int getPositionY();
	public EngineService getEngine();
	public HitboxService getCharBox();
	public int getLife();
	public int getSpeed();
	public boolean faceRight();
	
	
	/* Operators */
	public void moveLeft();
	public void moveRight();
	public void switchSide();
	public void step(CommandeService c);
}
