package engine.components.character;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import engine.components.hitbox.HitboxImpl;
import engine.components.hitbox.HitboxState;
import engine.components.player.Commande;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.JumpService;

public class CharacterImpl implements CharacterService{
	public  static int id = 0;
	protected Personnage personnage;
	protected int vie = -1;
	protected int vitesse = -1;
	protected int x;
	protected int y;
	protected EngineService engine;
	protected boolean faceRight;
	protected State state_actuel;
	protected HashMap<HitboxState, HitboxService> liste_hitbox;
	protected int myId;
	protected HitboxState hitboxState = HitboxState.STANDING;
	private int maxLife;
	private int ecart;
	protected JumpService jump;
	public  boolean jumping = false;
	

	private void gestionDown(){
		changeHitbox(HitboxState.CROUCHING);
	}
	@Override
	public void moveLeft() {
		gestionStand();
		AtomicInteger new_x = new AtomicInteger(getPositionX() - this.vitesse);
		int y = getPositionY();
		HitboxService tmp = new HitboxImpl();
		tmp.init(new_x, y, getHauteur(), getLargeur());
		
		if(isOutsideLeft(tmp)){
			new_x.set(1);
		}
		if(tmp.collidesWith(engine.getCharacter(getOtherIndice()).getCharBox()))
			return;
		getCharBox().moveTo(new_x.get(), getPositionY());
	}
	
	@Override
	public void moveRight() {
		gestionStand();
		HitboxService tmp = new HitboxImpl();
		int indice = getOtherIndice();
		AtomicInteger new_x = new AtomicInteger(getPositionX() + this.vitesse);
		int y = getPositionY();
		tmp.init(new_x, y, getHauteur(), getLargeur());
		if(isOutsideRight(tmp)){
			new_x.set(engine.getWidth() - getLargeur());
		}
		if(tmp.collidesWith(getOtherPlayer().getCharBox())){
			return;
		}
		
		getCharBox().moveTo(new_x, getPositionY());
	}

	@Override
	public void neutral() {
		gestionStand();
	}
	@Override
	public void moveUpRight() {
		
	}
	@Override
	public void moveUpLeft() {
	}
	@Override
	public void moveUp() {
		jump.launch();
	}
	@Override
	public void moveDown() {
		gestionDown();
	}
	@Override
	public void moveDownLeft() {
		gestionDown();
		AtomicInteger new_x = new AtomicInteger(getPositionX() - this.vitesse/2);
		int y = getPositionY();
		HitboxService tmp = new HitboxImpl();
		
		tmp.init(new_x, y, getHauteur(), getLargeur());
		
		if(isOutsideLeft(tmp)){
			new_x.set(1);
		}
		if(tmp.collidesWith(engine.getCharacter(getOtherIndice()).getCharBox()))
			return;
		getCharBox().moveTo(new_x.get(), getPositionY());
	}
	@Override
	public void moveDownRight() {
		gestionDown();
		HitboxService tmp = new HitboxImpl();
		int indice = getOtherIndice();
		AtomicInteger new_x = new AtomicInteger(getPositionX() + this.vitesse/2);
		int y = getPositionY();
		tmp.init(new_x, y, getHauteur(), getLargeur());
		if(isOutsideRight(tmp)){
			new_x.set(engine.getWidth() - getLargeur());
			System.out.println("Try to go out forward..");
		}
		if(tmp.collidesWith(getOtherPlayer().getCharBox())){
			return;
		}	
		getCharBox().moveTo(new_x, getPositionY());
	}
	
	@Override
	public String toString() {
		return "CharacterImpl [name=" + getName() + ", vie=" + vie + ", vitesse=" + vitesse + ", engine=" + engine
				+ ", hitbox=" + getCharBox() + ", faceRight=" + faceRight + "]";
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
		return getCharBox().getHauteur();
	}

	//TODO ajouter gestion witdh/hauteur du perso
	public boolean isOutsideLeft(HitboxService tmp) {
		return tmp.getPositionX().get() <= 0;
	}
	
	public boolean isOutsideRight(HitboxService tmp){
		return tmp.getPositionX().get() + tmp.getLargeur() > engine.getWidth();
	}

	@Override 
	public void init(Personnage personnage, int life, int speed, EngineService engine, boolean faceRight, int ecart) {
		this.myId = ++id;
		this.personnage = personnage;
		vie = life;
		vitesse = speed;
		this.engine = engine;
		this.faceRight = faceRight;
		this.liste_hitbox = new HashMap<>();
		this.maxLife = life;
		this.ecart = ecart;
	}
	

	
	@Override
	public boolean isDead() {
		return vie <= 0;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CharacterImpl other = (CharacterImpl) obj;
		if (ecart != other.ecart)
			return false;
		if (engine == null) {
			if (other.engine != null)
				return false;
		} else if (!engine.equals(other.engine))
			return false;
		if (faceRight != other.faceRight)
			return false;
		if (hitboxState != other.hitboxState)
			return false;
		if (jump == null) {
			if (other.jump != null)
				return false;
		} else if (!jump.equals(other.jump))
			return false;
		if (jumping != other.jumping)
			return false;
		if (liste_hitbox == null) {
			if (other.liste_hitbox != null)
				return false;
		} else if (!liste_hitbox.equals(other.liste_hitbox))
			return false;
		if (maxLife != other.maxLife)
			return false;
		if (myId != other.myId)
			return false;
		if (personnage != other.personnage)
			return false;
		if (state_actuel != other.state_actuel)
			return false;
		if (vie != other.vie)
			return false;
		if (vitesse != other.vitesse)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	@Override
	public String getName() {
		return personnage.toString();
	}


	@Override
	public int getPositionX() {
		return getCharBox().getPositionX().get();
	}
	@Override
	public int getPositionY() {
		return getCharBox().getPositionY();
	}
	public AtomicInteger getReferencePositionX(){
		return getCharBox().getPositionX();
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
		return getCharBox().getLargeur();
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
		result = prime * result + ecart;
		result = prime * result + ((engine == null) ? 0 : engine.hashCode());
		result = prime * result + (faceRight ? 1231 : 1237);
		result = prime * result + ((hitboxState == null) ? 0 : hitboxState.hashCode());
		result = prime * result + ((jump == null) ? 0 : jump.hashCode());
		result = prime * result + (jumping ? 1231 : 1237);
		result = prime * result + ((liste_hitbox == null) ? 0 : liste_hitbox.hashCode());
		result = prime * result + maxLife;
		result = prime * result + myId;
		result = prime * result + ((personnage == null) ? 0 : personnage.hashCode());
		result = prime * result + ((state_actuel == null) ? 0 : state_actuel.hashCode());
		result = prime * result + vie;
		result = prime * result + vitesse;
		result = prime * result + x;
		result = prime * result + y;
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
	
	@Override
	public int getMaxLife() {
		return maxLife;
	}
	
	public void bindHitbox(HitboxService hitbox, HitboxState state) {
		this.liste_hitbox.put(state, hitbox);
	}
	public void changeHitbox(HitboxState state){
		hitboxState = state;
	}

	@Override
	public int getEcart() {
		return ecart;
	}

	@Override
	public void bindJump(JumpService jumpService) {
		jump = jumpService;
	}
	@Override
	public void switchJump(boolean jump) {
		jumping = jump;		
	}
	
	private void gestionStand(){
		changeHitbox(HitboxState.STANDING);
	}
	public boolean isJumping(){
		return jumping;
	}

	@Override
	public HitboxState getHitboxState() {
		return hitboxState;
	}
}
