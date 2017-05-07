package bug;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import engine.components.character.FighterImpl;
import engine.components.hitbox.HitboxImpl;
import engine.components.hitbox.HitboxState;
import engine.components.player.Commande;
import engine.services.ComboService;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.JumpService;
import engine.services.TechService;

public class FighterImpBug extends FighterImpl implements FightCharService {

	@Override
	public void takeAttack(int damage, int hstun, int bstun) {
		super.takeAttack(hstun, damage, bstun);
	}

	@Override
	public void moveLeft() {
		AtomicInteger new_x = new AtomicInteger(getPositionX() + this.vitesse);
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
	public void moveDownLeft() {
		AtomicInteger new_x = new AtomicInteger(getPositionX() + this.vitesse);
		int y = getPositionY();
		HitboxService tmp = new HitboxImpl();
		tmp.init(new_x, y, getHauteur(), getLargeur());
		getCharBox().moveTo(new_x.get(), getPositionY());
	}
}
