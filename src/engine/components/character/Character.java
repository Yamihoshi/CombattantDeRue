package engine.components.character;

import engine.components.player.Commande;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.HitboxService;

public class Character implements CharacterService{

	protected int vie = -1;
	protected int vitesse = -1;
	protected int positionX = -1;
	protected int positionY = -1;
	protected EngineService engine;
	protected HitboxService hitbox;
	protected boolean faceRight;

	//protected Hitbox hitbox;
	//protected Technique[] ou ArrayList techniques
	String sprite_src = null;
	@Override 
	public void init(int life, int speed, EngineService engine, boolean faceRight) {
		vie = life;
		vitesse = speed;
		this.engine = engine;
		this.faceRight = faceRight;
	}
	@Override
	public int getPositionX() {
		// TODO Auto-generated method stub
		return positionX;
	}
	@Override
	public int getPositionY() {
		// TODO Auto-generated method stub
		return positionY;
	}
	@Override
	public EngineService getEngine() {
		// TODO Auto-generated method stub
		return engine;
	}
	@Override
	public HitboxService getCharBox() {
		// TODO Auto-generated method stub
		return hitbox;
	}
	@Override
	public int getLife() {
		// TODO Auto-generated method stub
		return vie;
	}
	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return vitesse;
	}
	@Override
	public boolean isFaceRight() {
		// TODO Auto-generated method stub
		return faceRight;
	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void switchSide() {
		faceRight = !faceRight;
		
	}
	@Override
	public void step(Commande c) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isDead() {
		return vie > 0;
	}
}
