package engine.components.character;

import engine.services.CharacterService;
import engine.services.CommandeService;
import engine.services.EngineService;
import engine.services.HitboxService;

public class Character implements CharacterService{

	protected int vie = -1;
	protected int vitesse = -1;
	protected int positionX = -1;
	protected int positionY = -1;

	//protected Hitbox hitbox;
	//protected Technique[] ou ArrayList techniques
	String sprite_src = null;
	@Override
	public void init(int x, int y, EngineService engine) {
		// TODO Auto-generated method stub
		
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
		return null;
	}
	@Override
	public HitboxService getCharBox() {
		// TODO Auto-generated method stub
		return null;
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
		return false;
	}
	@Override
	public boolean isFaceLeft() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void step(CommandeService c) {
		// TODO Auto-generated method stub
		
	}
}
