package engine.components.character;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import engine.components.hitbox.HitboxImpl;
import engine.components.hitbox.HitboxState;
import engine.components.player.Commande;
import engine.contracts.HitboxContract;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.HitboxService;

public class CharacterImpl implements CharacterService{
	public  static int id = 0;
	
	
	protected Personnage personnage;
	protected int vie = -1;
	protected int vitesse = -1;
	protected int x;
	protected int y;
	protected EngineService engine;
	protected HitboxService hitbox;
	protected boolean faceRight;
	protected State state_actuel;
	protected HashMap<HitboxState, HitboxService> liste_hitbox;
	protected int myId;
	protected HitboxState hitboxState = HitboxState.STANDING;
	
	private void gestionStand(){
		//state_actuel = State.STAND;
	}
	
	private void gestionJump(){
		//state_actuel = State.JUMP;
	}
	private void gestionDown(){
		//state_actuel = State.CROUCH;
	}
	@Override
	public void moveLeft() {
		gestionStand();
		AtomicInteger new_x = new AtomicInteger(getPositionX() - this.vitesse);
		HitboxService tmp = new HitboxImpl();
		tmp.init(new_x, getReferencePositionY(), getHauteur(), getLargeur());
		
		if(isOutsideLeft(tmp)){
			new_x.set(1);
			//tmp.init(new_x, getPositionY(), getHauteur(), getLargeur());
		}else if(isOutsideRight(tmp)){
			new_x.set(engine.getWidth());
			//tmp.init(new_x, getPositionY(), getHauteur(), getLargeur());
		}
		if(tmp.collidesWith(engine.getCharacter(getOtherIndice()).getCharBox()))
			return;
		hitbox.moveTo(new_x.get(), getPositionY());
	}
	
	@Override
	public void moveRight() {
		gestionStand();
		HitboxService tmp = new HitboxImpl();
		int indice = getOtherIndice();
		AtomicInteger new_x = new AtomicInteger(getPositionX() + this.vitesse);
		tmp.init(new_x, getReferencePositionY(), getHauteur(), getLargeur());
		if(isOutsideRight(tmp)){
			new_x.set(engine.getWidth() - getLargeur());
		//	tmp.init(new_x, getPositionY(), getHauteur(), getLargeur());
			System.out.println("Try to go out forward..");
		}
		if(tmp.collidesWith(getOtherPlayer().getCharBox())){
			return;
		}
			
		hitbox.moveTo(new_x, getReferencePositionY());
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
				+ ", hitbox=" + hitbox + ", faceRight=" + faceRight + "]";
	}
	
	protected int getMyIndice(){
		return getEngine().getMyIndice(myId);
	} 
	
	protected int getOtherIndice(){
		return getEngine().getOtherIndice(myId);
	}
	protected FightCharService getOtherPlayer(){
		return engine.getCharacter(getOtherIndice());
	}
	@Override
	public int getHauteur() {
		return hitbox.getHauteur();
	}

	//TODO ajouter gestion witdh/hauteur du perso
	public boolean isOutsideLeft(HitboxService tmp) {
		return tmp.getPositionX().get() <= 0;
	}
	
	public boolean isOutsideRight(HitboxService tmp){
		return tmp.getPositionX().get() + tmp.getLargeur() > engine.getWidth();
	}

	@Override 
	public void init(Personnage personnage, int life, int speed, EngineService engine, boolean faceRight) {
		this.myId = ++id;
		this.personnage = personnage;
		vie = life;
		vitesse = speed;
		this.engine = engine;
		this.faceRight = faceRight;
		this.hitbox = new HitboxContract(new HitboxImpl());
		this.liste_hitbox = new HashMap<>();
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
	public int getPositionX() {
		return getCharBox().getPositionX().get();
	}
	@Override
	public int getPositionY() {
		return getCharBox().getPositionY().get();
	}
	public AtomicInteger getReferencePositionX(){
		return getCharBox().getPositionX();
	}
	public AtomicInteger getReferencePositionY(){
		return getCharBox().getPositionY();
	}
	@Override
	public EngineService getEngine() {
		return engine;
	}
	@Override
	public HitboxService getCharBox() {
		return liste_hitbox.get(hitboxState);
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
	public Personnage getPersonnage() {
		return personnage;
	}

	@Override
	public int getLargeur() {
		return hitbox.getLargeur();
	}

	@Override
	public State getState() {
		return state_actuel;
	}


	@Override
	public void switchSide() {
		faceRight = !faceRight;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((engine == null) ? 0 : engine.hashCode());
		result = prime * result + (faceRight ? 1231 : 1237);
		result = prime * result + ((hitbox == null) ? 0 : hitbox.hashCode());
		result = prime * result + vie;
		result = prime * result + vitesse;
		return result;
	}

	@Override
	public void step(Commande c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getId() {
		return myId;
	}

	public int getMaxLife() {
		return 100;
	}
	
	public void bindHitbox(HitboxService hitbox, HitboxState state) {
		this.liste_hitbox.put(state, hitbox);
	}

}
