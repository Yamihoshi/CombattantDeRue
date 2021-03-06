package tests.bug;

import engine.components.character.CharacterFabrique;
import engine.components.character.Personnage;
import engine.components.player.Player;
import engine.contracts.EngineContract;
import engine.impl.EngineBugImpl;
import engine.services.FightCharService;
import tests.ComboTest;
import tests.abstractTest.ComboTestAbstract;

public class ComboTestBug extends ComboTestAbstract{

	@Override
	public void beforeTests() {
		// TODO Auto-generated method stub
		this.engine = new EngineContract(new EngineBugImpl());
		FightCharService chara1 = CharacterFabrique.initBug(Personnage.CHUN_LI, this.engine, true);
		FightCharService chara2 = CharacterFabrique.initBug(Personnage.CHUN_LI, this.engine, false);
		
		Player J1 = new Player(chara1);
		Player J2 = new Player(chara2);
		
		this.engine.init(720, 1280, 500, J1, J2);
	}

	@Override
	public void afterTests() {
		// TODO Auto-generated method stub
		
	}

}
