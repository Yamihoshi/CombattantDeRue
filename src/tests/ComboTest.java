package tests;

import static org.junit.Assert.*;

import org.junit.*;

import engine.components.character.CharacterFabrique;
import engine.components.character.Personnage;
import engine.components.player.Commande;
import engine.components.player.Player;
import engine.contracts.EngineContract;
import engine.impl.EngineImpl;
import engine.services.FightCharService;

public class ComboTest {
	
	EngineContract engine;
	
	@Before
	public void beforeTests()
	{
		
	}

	@After
	public void afterTests() {
		
	}
	
	@Test
	public void testCombo_stance()
	{
		this.engine = new EngineContract(new EngineImpl());
		
		FightCharService chara1 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, true);
		FightCharService chara2 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, false);
		
		Player J1 = new Player(chara1);
		Player J2 = new Player(chara2);
		
		this.engine.init(720, 1280, 500, J1, J2);
		
		this.engine.step(Commande.PUNCH, Commande.NEUTRAL);
		
		int combo = chara1.getCombo();
		
		assertTrue(combo==0);
		
		//A continuer
	}
}
