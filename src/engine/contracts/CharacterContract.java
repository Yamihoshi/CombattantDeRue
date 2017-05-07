package engine.contracts;

import engine.components.character.Personnage;
import engine.components.character.State;
import engine.components.hitbox.HitboxState;
import engine.components.player.Commande;
import engine.contracts.error.InvariantError;
import engine.contracts.error.PostconditionError;
import engine.contracts.error.PreconditionError;
import engine.decorators.CharacterDecorator;
import engine.services.CharacterService;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.TechService;

public class CharacterContract extends CharacterDecorator {

	public CharacterContract(FightCharService character) {
		super(character);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(Personnage personnage, int l, int s, EngineService engine, boolean faceRight, int ecart) {
		if(!(l > 0 && s>0)){
			throw new PreconditionError("Erreur init precondition character");
		}
		
		super.init(personnage, l, s, engine, faceRight, ecart);
	
		if(!(getLife() == l && getSpeed() == s && isFaceRight() == faceRight && getEngine().equals(engine)) && getCharBox() != null ){
			throw new PostconditionError("Erreur post condition error");
		}
		
		if(!(this.getTech()!=null))
			throw new PostconditionError("Technique not initialized");
		
		if(!(this.getComboService()!=null))
			throw new PostconditionError("ComboService not initialized");
	}
 


	@Override
	public void moveLeft() {
		if(isTeching() || isBlocking() || isBlockStunned() || isHitStunned()){
			throw new PreconditionError("Actuellement en teching..");
		}
		int other = getEngine().getOtherIndice(getId());
		int pre_positionX = getPositionX();
		boolean faceRight = isFaceRight();
		int pre_position_y = getPositionY();
		checkInvariant();
		super.moveLeft();
		checkInvariant();
		
		if(!(!(getCharBox().collidesWith(getEngine().getCharacter(other).getCharBox()))
				|| (getPositionX() == pre_positionX))
				){
			throw new PostconditionError("Collision with a changement of posX" + getPositionX() + "==" + pre_positionX);
		}
		
		if(!(faceRight == isFaceRight())){
			throw new PostconditionError("No more faceRight");
		}
		if(!(pre_position_y == getPositionY())){
			throw new PostconditionError("Y changing..");
		}
		
		if((pre_positionX <= getSpeed() && !getCharBox().collidesWith(getEngine().getCharacter(other).getCharBox()))){
			if(!(getPositionX() == 1)){
				throw new PostconditionError("x not equals to 1..");
			}
		}
		
		if(getHitboxState() != HitboxState.STANDING){
			throw new PostconditionError("not standing");
		}

		if(isTeching() || isBlocking() || isBlockStunned() || isHitStunned()){
			throw new PostconditionError("Actuellement en teching..");
		}
		
		
	}
	@Override
	public void moveRight() {
		
		if(isTeching() || isBlocking() || isBlockStunned() || isHitStunned()){
			throw new PreconditionError("Actuellement en teching..");
		}
		int other = getEngine().getOtherIndice(getId());
		int pre_positionX = getPositionX();
		boolean faceRight = isFaceRight();
		int pre_position_y = getPositionY();
		int life = getLife();
		int combo = getCombo();
		

		checkInvariant();
		super.moveRight();
		
		if(!(!(getCharBox().collidesWith(getEngine().getCharacter(other).getCharBox()))
				|| (getPositionX() == pre_positionX))
				){
			new PostconditionError("Collision with a deplacement of posX on a right deplacement");
		}
		
		checkInvariant();

		
		if((pre_positionX > getSpeed() && !getCharBox().collidesWith(getEngine().getCharacter(other).getCharBox()))){
			if(!(getPositionX() == getEngine().getWidth() - getLargeur())){
				throw new PostconditionError("x not equals to a width engine - width..");
			}
		}
		
		
		if(!(faceRight == isFaceRight() && life == getLife() && combo == getCombo())){
			throw new PostconditionError("No more faceRight or life..");
		}
		if(!(pre_position_y == getPositionY())){
			throw new PostconditionError("Y changing..");
		}
		
		if(getHitboxState() != HitboxState.STANDING){
			throw new PostconditionError("not standing");
		}
		if(isTeching() || isBlocking() || isBlockStunned() || isHitStunned()){
			throw new PostconditionError("Actuellement en teching..");
		}
		
	}

	@Override
	public void switchSide() {
		checkInvariant();
		// TODO Auto-generated method stub
		boolean pre_faceRight = isFaceRight();
		int pre_positionX = getPositionX();
		super.switchSide();
		if(!(pre_positionX == getPositionX() && pre_faceRight != isFaceRight())){
			throw new PostconditionError("Erreur post condition error switchSide");
		}
		checkInvariant();
	}

	@Override
	public void step(Commande c) {
		int frame = getComboService().getFrameRestante();
		checkInvariant();
		super.step(c);
		checkInvariant();
		if(!(frame -1 == getComboService().getFrameRestante() || getComboService().getFrameRestante() == 95)){
			throw new PostconditionError("Erreur de frame après step pour els combo" +  getComboService().getFrameRestante()); 
		}
		
	}
	
	public void checkInvariant(){
		if(!(getPositionX() > 0 && getPositionX() + getLargeur() < getEngine().getWidth())){
			//throw new InvariantError("Erreur Character X " + getPositionX());
		}
		if(!(getPositionY() > 0 && getPositionY() < getEngine().getHeight())){
			throw new InvariantError("Erreur Character Y " + getPositionY());
		}
		if(!(isDead() == !(getLife()>0))){
			throw new InvariantError("Erreur Character not really dead kek");
		}
		
		if(!(!isTeching() || this.getCurrentTechnique()!=null))
			throw new InvariantError("Erreur in getCurrentTechniquement != null but was not in Teching State");
		
		if(!(!isTeching() || !isBlocking()))
			throw new InvariantError("Blocking while performing a Technique");
		
		if(!(!isBlocking() || !(isHitStunned() || isBlockStunned())))
			throw new InvariantError("Blocking while being stunned");
		
	}
	@Override
	public void moveUpRight() {
		
		if(isTeching() || isBlocking() || isBlockStunned() || isHitStunned()){
			throw new PostconditionError("Actuellement en teching..");
		}
		super.moveUpRight();
		
		
		
	}

	@Override
	public void moveUpLeft() {
		
		if(isTeching() || isBlocking() || isBlockStunned() || isHitStunned()){
			throw new PostconditionError("Actuellement en teching..");
		}
		
		super.moveUpLeft();
	}

	@Override
	public void moveUp() {
		
		int other = getEngine().getOtherIndice(getId());
		int pre_positionX = getPositionX();
		boolean faceRight = isFaceRight();
		int pre_position_y = getPositionY();
		int life = getLife();
		int combo = getCombo();
		
		
		if(isTeching() || isBlocking() || isBlockStunned() || isHitStunned() || isJumping()){
			throw new PostconditionError("Actuellement en teching..");
		}
		
		super.moveUp();
		
		if(isTeching() || isBlocking() || isBlockStunned() || isHitStunned()){
			throw new PostconditionError("Actuellement en teching..");
		}
		
		if((pre_positionX > getSpeed() && !getCharBox().collidesWith(getEngine().getCharacter(other).getCharBox()))){
			if(!(getPositionX() == getEngine().getWidth() - getLargeur())){
				throw new PostconditionError("x not equals to a width engine - width..");
			}
		}
		
		
		if(!(faceRight == isFaceRight() && life == getLife() && combo == getCombo())){
			throw new PostconditionError("No more faceRight or life..");
		}
		if(!(pre_position_y == getPositionY())){
			throw new PostconditionError("Y changing..");
		}
		
	}

	@Override
	public void moveDown() {
		if(isTeching() || isBlocking() || isBlockStunned() || isHitStunned()){
			throw new PostconditionError("Actuellement en teching..");
		}
		int other = getEngine().getOtherIndice(getId());
		int pre_positionX = getPositionX();
		boolean faceRight = isFaceRight();
		int pre_position_y = getPositionY();
		int life = getLife();
		int combo = getCombo();
		
		super.moveDown();
		
		if(getHitboxState() != HitboxState.CROUCHING){
			throw new PostconditionError("not standing");
		}
		
		if(isTeching() || isBlocking() || isBlockStunned() || isHitStunned()){
			throw new PostconditionError("Actuellement en teching..");
		}
		
		if((pre_positionX > getSpeed() && !getCharBox().collidesWith(getEngine().getCharacter(other).getCharBox()))){
			if(!(getPositionX() == getEngine().getWidth() - getLargeur())){
				throw new PostconditionError("x not equals to a width engine - width..");
			}
		}
		
		
		if(!(faceRight == isFaceRight() && life == getLife() && combo == getCombo())){
			throw new PostconditionError("No more faceRight or life..");
		}
		if(!(pre_position_y == getPositionY())){
			throw new PostconditionError("Y changing..");
		}

	}

	@Override
	public void moveDownLeft() {
		

		int other = getEngine().getOtherIndice(getId());
		int pre_positionX = getPositionX();
		boolean faceRight = isFaceRight();
		int pre_position_y = getPositionY();
		int life = getLife();
		int combo = getCombo();
		
		
		if(isTeching() || isBlocking() || isBlockStunned() || isHitStunned()){
			throw new PreconditionError("Actuellement en teching..");
		}
		
		checkInvariant();
		super.moveDownLeft();
		
		if(!(!(getCharBox().collidesWith(getEngine().getCharacter(other).getCharBox()))
				|| (getPositionX() == pre_positionX))
				){
			new PostconditionError("Collision with a deplacement of posX on a right deplacement");
		}
		
		checkInvariant();
		
		if(getHitboxState() != HitboxState.CROUCHING){
			throw new PostconditionError("not standing");
		}
		
		if(isTeching() || isBlocking() || isBlockStunned() || isHitStunned()){
			throw new PostconditionError("Actuellement en teching..");
		}
		
		if((pre_positionX > getSpeed() && !getCharBox().collidesWith(getEngine().getCharacter(other).getCharBox()))){
			if(!(getPositionX() == getEngine().getWidth() - getLargeur())){
				throw new PostconditionError("x not equals to a width engine - width..");
			}
		}
		
		
		if(!(faceRight == isFaceRight() && life == getLife() && combo == getCombo())){
			throw new PostconditionError("No more faceRight or life..");
		}
		if(!(pre_position_y == getPositionY())){
			throw new PostconditionError("Y changing..");
		}
		
	}

	@Override
	public void moveDownRight() {

		int other = getEngine().getOtherIndice(getId());
		int pre_positionX = getPositionX();
		boolean faceRight = isFaceRight();
		int pre_position_y = getPositionY();
		int life = getLife();
		int combo = getCombo();
		
		
		if(isTeching() || isBlocking() || isBlockStunned() || isHitStunned()){
			throw new PreconditionError("Actuellement en teching/blocking..");
		}
		
		checkInvariant();
		super.moveDownRight();
		
		if(!(!(getCharBox().collidesWith(getEngine().getCharacter(other).getCharBox()))
				|| (getPositionX() == pre_positionX))
				){
			new PostconditionError("Collision with a deplacement of posX on a right deplacement");
		}
		
		checkInvariant();
		
		
		if(getHitboxState() != HitboxState.CROUCHING){
			throw new PostconditionError("not standing");
		}
		
		if(isTeching() || isBlocking() || isBlockStunned() || isHitStunned()){
			throw new PostconditionError("Actuellement en teching..");
		}
		if((pre_positionX > getSpeed() && !getCharBox().collidesWith(getEngine().getCharacter(other).getCharBox()))){
			if(!(getPositionX() == getEngine().getWidth() - getLargeur())){
				throw new PostconditionError("x not equals to a width engine - width..");
			}
		}
		
		
		if(!(faceRight == isFaceRight() && life == getLife() && combo == getCombo())){
			throw new PostconditionError("No more faceRight or life..");
		}
		if(!(pre_position_y == getPositionY())){
			throw new PostconditionError("Y changing..");
		}
	}

	@Override
	public String getName() {
		return super.getName();
	}



	@Override
	public State getState() {
		return super.getState();
	}

	@Override
	public void neutral() {
		super.neutral();
	}

	@Override
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return super.isBlocking();
	}

	@Override
	public boolean isBlockStunned() {
		// TODO Auto-generated method stub
		return super.isBlockStunned();
	}

	@Override
	public boolean isHitStunned() {
		return super.isHitStunned();
	}

	@Override
	public boolean isTeching() {
		return super.isTeching();
	}
	
	@Override
	public TechService getCurrentTechnique()
	{
		return super.getCurrentTechnique();
	}



	@Override
	public void startTech(TechService tech) {
		
		checkInvariant();
		
		if(!(super.isTeching()))
				throw new PreconditionError("Trying to Start Technique but was not in Teching State");
		
		super.startTech(tech);
		
		if(!(this.getCurrentTechnique()!=null))
			throw new PostconditionError("Technique started but NULL currentTechnique");
		if(!(this.isTeching()))
			throw new PostconditionError("Technique started but not in Teching State");
		
		checkInvariant();
	}

	public void endTechnique() {
		
		checkInvariant();
		
		if(!(super.isTeching()))
				throw new PreconditionError("Trying to End Technique but was not in Teching State");
		
		super.endTechnique();
		checkInvariant();
		if(State.WAITING != getState())
			throw new PostconditionError("Trying to End Technique but was not in Wainting State");

		if((super.isTeching()))
			throw new PostconditionError("Trying to End Technique but was not in Teching State");
	}

	public void takeAttack(int damage, int hstun, int bstun) {
		
		boolean blocking = isBlocking();
		int life = getLife();
		int hit_stun_frame = getHit_stun();
		
		boolean teching = isTeching();
		int block_stun_frame = getBlock_stun();
		checkInvariant();
		
		if(!(damage>0))
			throw new PreconditionError("Taking attack of 0 damage");
		if(!(hstun>0))
			throw new PreconditionError("0 Hit Stun = impossible");
		if(!(bstun>0))
			throw new PreconditionError("0 Block Stun = impossible");
		if(!(!this.isDead()))
			throw new PreconditionError("Taking damage but was already DEAD");
		
		super.takeAttack(damage, hstun, bstun);
		checkInvariant();
		
		if(isBlocking()){
			throw new PostconditionError("Impossible de bloquer..");
		}
		if(!(getCombo() == 0)){
			throw new PostconditionError("Combo non remis à 0");
		}
		if(blocking){
			if(!isBlockStunned()){
				throw new PostconditionError("Non blockStun..");
			}
			
			if(!(life == getLife())){
				throw new PostconditionError("Life decrease but attack blocked");
			}
			
			if(!(bstun == getBlock_stun())){
				throw new PostconditionError("BlockStun Not Increased");
			}
		}
		if(!blocking){
			if(!isHitStunned()){
				throw new PostconditionError("Non hitStun");
			}
			if(teching){
				if(!(life == getLife() + damage *2)){
					throw new PostconditionError("Life no decreased...");
				}
				if(!(hstun *2  == getHit_stun())){
					throw new PostconditionError("HitStun Not Increased" + getHit_stun() + " must be equals to" + hit_stun_frame + "+" + hstun );
				}
			}else{
				if(!(life == getLife() + damage)){
					throw new PostconditionError("Life no decreased...");
				}
				if(!(hstun == getHit_stun())){
					throw new PostconditionError("HitStun Not Increased" + getHit_stun() + " must be equals to" + hit_stun_frame + "+" + hstun );
				}
			}
		}
		
		
	
	}
	

	@Override
	public int getHauteur() {
		// TODO Auto-generated method stub
		return super.getHauteur();
	}

	@Override
	public int getLargeur() {
		return super.getLargeur();
	}

	@Override
	public Personnage getPersonnage() {
		return super.getPersonnage();
	}
	@Override
	public int getPositionX() {
		return super.getPositionX();
	}

	@Override
	public int getPositionY() {
		return super.getPositionY();
	}

	@Override
	public EngineService getEngine() {
		return super.getEngine();
	}

	@Override
	public HitboxService getCharBox() {
		return super.getCharBox();
	}

	@Override
	public int getLife() {
		return super.getLife();
	}

	@Override
	public int getSpeed() {
		return super.getSpeed();
	}

	@Override
	public boolean isFaceRight() {
		return super.isFaceRight();
	}


	@Override
	public boolean isDead() {
		return super.isDead();
	}
	
	@Override
	public void stepCombo(boolean hit)
	{
		checkInvariant();
		
		int pre_combo = this.getCombo();
		
		super.stepCombo(hit);
		
		if(!(!(hit && super.getComboService().comboPossible()) || this.getCombo()==pre_combo+1 ))
			throw new PostconditionError("Hitted + combo possible but combo was not incremented");
		
		if(!(!(hit && !super.getComboService().comboPossible()) || this.getCombo()==pre_combo+1 ))
			throw new PostconditionError("Hitted + combo not possible but combo was incremented");
		
		checkInvariant();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
