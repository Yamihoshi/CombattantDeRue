package engine.services;

public interface CharacterService {

	/* Constructors */
	public void init(int x, int y, EngineService engine ); // What does they mean by this ?
	
	/* Observators */
	public int positionX();
	public int positionY();
	public EngineService engine();
	public HitboxService charBox();
	public int life();
	public int speed();
	public boolean faceRight();
	public boolean faceLeft();
	
	
	/* Operators */
	public void moveLeft();
	public void moveRight();
	public void switchSide();
	public void step(CommandeService c);
}
