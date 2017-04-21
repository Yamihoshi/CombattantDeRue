package engine.components.character;

import java.util.ArrayList;

import engine.components.hitbox.HitboxImpl;
import engine.components.player.Commande;
import engine.contracts.HitboxContract;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.FightChar;
import engine.services.HitboxService;
import engine.services.TechService;

public class CharacterImpl implements FightChar{

	public static final int DEPLACEMENT = 10;
	protected Personnage personnage;
	protected int vie = -1;
	protected int vitesse = -1;
	protected EngineService engine;
	protected HitboxService hitbox;
	protected boolean faceRight;
	
	protected ArrayList<TechService> techniques;
 
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((engine == null) ? 0 : engine.hashCode());
		result = prime * result + (faceRight ? 1231 : 1237);
		result = prime * result + ((hitbox == null) ? 0 : hitbox.hashCode());
		result = prime * result + ((techniques == null) ? 0 : techniques.hashCode());
		result = prime * result + vie;
		result = prime * result + vitesse;
		return result;
	}
	
	@Override 
	public void init(Personnage personnage, int life, int speed, EngineService engine, boolean faceRight) {
		this.personnage = personnage;
		vie = life;
		vitesse = speed;
		this.engine = engine;
		this.faceRight = faceRight;
		this.hitbox = new HitboxContract(new HitboxImpl());
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
	public void setCharBox(HitboxService hit) {
		this.hitbox = hit;		
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
		return vie <= 0;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return personnage.name();
	}
	@Override
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isBlockstunned() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isHitstunned() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isTeching() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public TechService tech() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void moveLeft() {
		HitboxService tmp = new HitboxImpl();
		tmp.init(getPositionX() - this.vitesse, getPositionY(), getHauteur(), getLargeur());
		if(isOutside(tmp)){
			return;
		}
		else if(tmp.collidesWith(engine.getCharacter(getOtherIndice()).getCharBox()))
			return;
		System.out.println("ça passe");
		hitbox.moveTo(getPositionX() - /*CharacterImpl.DEPLACEMENT*/this.vitesse, getPositionY());

	}
	@Override
	public void moveRight() {
		HitboxService tmp = new HitboxImpl();
		int indice = getOtherIndice();
		tmp.init(getPositionX() + this.vitesse, getPositionY(), getHauteur(), getLargeur());
		if(isOutside(tmp)){
			return;
		}else if(tmp.collidesWith(engine.getCharacter(getOtherIndice()).getCharBox()))
			return;
		hitbox.moveTo(getPositionX() +/* CharacterImpl.DEPLACEMENT*/this.vitesse, getPositionY());
	}
	@Override
	public void moveUpRight() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void moveUpLeft() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void moveUp() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void moveDownLeft() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void moveDownRight() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "CharacterImpl [name=" + getName() + ", vie=" + vie + ", vitesse=" + vitesse + ", engine=" + engine
				+ ", hitbox=" + hitbox + ", faceRight=" + faceRight + ", techniques=" + techniques + "]";
	}
	
	protected int getMyIndice(){
		if(getEngine().getCharacter(0).getPersonnage() == this.personnage)
			return 0;
		return 1;
	} 
	
	protected int getOtherIndice(){
		if(getEngine().getCharacter(0).getPersonnage() == this.personnage)
			return 1;
		return 0;
	}
	@Override
	public int getHauteur() {
		// TODO Auto-generated method stub
		return hitbox.getHauteur();
	}

	//TODO ajouter gestion witdh/hauteur du perso
	public boolean isOutside(HitboxService tmp) {
		if(tmp.getPositionX() <= 0 || tmp.getPositionX() > engine.getWidth())
			return true;
		else if(tmp.getHauteur() <= 0 || tmp.getPositionY() > engine.getHeight())
			return true;
		else return false;
	}

	@Override
	public Personnage getPersonnage() {
		// TODO Auto-generated method stub
		return personnage;
	}

	@Override
	public int getLargeur() {
		// TODO Auto-generated method stub
		return hitbox.getLargeur();
	}

}
