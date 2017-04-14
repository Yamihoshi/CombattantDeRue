package engine.components.character;

import java.util.ArrayList;

import engine.components.player.Commande;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.HitboxService;
import engine.services.TechService;

public class CharacterImpl implements CharacterService{

	@Override
	public String toString() {
		return "CharacterImpl [name=" + name + ", vie=" + vie + ", vitesse=" + vitesse + ", engine=" + engine
				+ ", hitbox=" + hitbox + ", faceRight=" + faceRight + ", techniques=" + techniques + "]";
	}
	protected String name;
	protected int vie = -1;
	protected int vitesse = -1;
	protected EngineService engine;
	protected HitboxService hitbox;
	protected boolean faceRight;
	
	protected ArrayList<TechService> techniques;

	@Override 
	public void init(String nom, int life, int speed, EngineService engine, boolean faceRight) {
		name = nom;
		vie = life;
		vitesse = speed;
		this.engine = engine;
		this.faceRight = faceRight;
	}
	@Override
	public int getPositionX() {
		// TODO Auto-generated method stub
		return hitbox.getPositionX();
	}
	@Override
	public int getPositionY() {
		// TODO Auto-generated method stub
		return hitbox.getPositionY();
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
		hitbox.moveTo(getPositionX()-1, getPositionY());
		
	}
	@Override
	public void moveRight() {
		hitbox.moveTo(getPositionX()+1, getPositionY());
	}
	@Override
	public void switchSide() {
		faceRight = !faceRight;
		
	}
	@Override
	public void step(Commande c) {
		switch(c){
			case ATTACK:
				break;
			case DOWN:
				break;
			case UP:
				break;
			case NEUTRAL:
				break;
			case LEFT:
				moveLeft();
				break;
			case RIGHT:
				moveRight();
				break;
			case DOWNLEFT:
				break;
		}
		
	}
	@Override
	public boolean isDead() {
		return vie > 0;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}
