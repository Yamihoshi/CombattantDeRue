package engine.components.character;

import java.util.ArrayList;

import engine.components.hitbox.HitboxImpl;
import engine.components.player.Commande;
import engine.contracts.HitboxContract;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.TechService; 

public class CharacterImpl implements FightCharService{

	public static final int DEPLACEMENT = 10;
	protected Personnage personnage;
	protected int vie = -1;
	protected int vitesse = -1;
	protected EngineService engine;
	protected HitboxService hitbox;
	protected boolean faceRight;
	protected State state_actuel;
	
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
		return hitbox.getPositionX();
	}
	@Override
	public int getPositionY() {
		return hitbox.getPositionY();
	}
	@Override
	public EngineService getEngine() {
		return engine;
	}
	@Override
	public HitboxService getCharBox() {
		return hitbox;
	}
	@Override
	public void setCharBox(HitboxService hit) {
		this.hitbox = hit;		
	}
	@Override
	public int getLife() {
		return vie;
	}
	@Override
	public int getSpeed() {
		return vitesse;
	}
	@Override
	public boolean isFaceRight() {
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
			case DOWNRIGHT:
				break;
			case GUARD:
				break;
			case UPLEFT:
				break;
			case UPRIGHT:
				break;
			default:
				break;
			}
		
	}
	@Override
	public boolean isDead() {
		return vie <= 0;
	}
	@Override
	public String getName() {
		return personnage.name();
	}
	@Override
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTeching() {
		// TODO Auto-generated method stub
		return false;
	}

	
	private void gestionStand(){
		state_actuel = State.STAND;
	}
	
	private void gestionJump(){
		state_actuel = State.JUMP;
	}
	private void gestionDown(){
		state_actuel = State.CROUCH;
	}
	@Override
	public void moveLeft() {
		gestionStand();
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
		gestionStand();
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
	public void neutral() {
		gestionStand();
		
	}


	@Override
	public void moveUpRight() {
		gestionJump();
		
	}
	@Override
	public void moveUpLeft() {
		// TODO Auto-generated method stub
		gestionJump();

		
	}
	@Override
	public void moveUp() {
		gestionJump();
	}
	@Override
	public void moveDown() {
		gestionDown();
		/*ADD CHANGEMENT HITBOX*/
	}

	@Override
	public void moveDownLeft() {
		gestionDown();
	}
	@Override
	public void moveDownRight() {
		gestionDown();
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
		if(tmp.getPositionX() <= 0 || tmp.getPositionX() + tmp.getLargeur() > engine.getWidth())
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

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return state_actuel;
	}



	@Override
	public boolean isBlockStunned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHitStunned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TechService getTech() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean techFrame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean techHasAlreadyHit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startTech(TechService tech) {
		// TODO Auto-generated method stub
		
	}



}
