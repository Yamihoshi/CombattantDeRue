package tests.abstractTest;

import static org.junit.Assert.*;

import org.junit.*;

import engine.components.character.CharacterFabrique;
import engine.components.character.FighterImpl;
import engine.components.character.Personnage;
import engine.components.player.Player;
import engine.contracts.EngineContract;
import engine.contracts.error.PostconditionError;
import engine.contracts.error.PreconditionError;
import engine.impl.EngineImpl;
import engine.services.FightCharService;

public abstract class EngineTestAbstract {

	protected EngineContract engine;
	
	@Before
	public abstract void beforeTests();

	@After
	public abstract void afterTests();
	
	@Test
	public void testInit()
	{		
		FightCharService chara1 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, true);
		FightCharService chara2 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, false);
		
		Player J1 = new Player(chara1);
		Player J2 = new Player(chara2);
		
		this.engine.init(720, 1280, 500, J1, J2);
	}
	
	@Test
	public void testInit_Negatif()
	{		
		FightCharService chara1 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, true);
		FightCharService chara2 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, false);
		
		Player J1 = new Player(chara1);
		Player J2 = new Player(chara2);
		
		try{
			this.engine.init(0, 1280, 500, J1, J2);
			assertTrue("Engine initialisée même avec des mauvaises val",false);
		}catch(PreconditionError e){assert(true);};
	}
	
	@Test
	public void testGameOver_J1()
	{		
		FightCharService chara1 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, true);
		FightCharService chara2 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, false);
		
		Player J1 = new Player(chara1);
		Player J2 = new Player(chara2);
		
		this.engine.init(720, 1280, 500, J1, J2);
		
		assertTrue(!this.engine.isGameOver());
		
		chara1.takeAttack(chara1.getMaxLife(), 100, 100);
		
		assertTrue(this.engine.isGameOver());
	}
	
	@Test
	public void testGameOver_J2()
	{		
		FightCharService chara1 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, true);
		FightCharService chara2 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, false);
		
		Player J1 = new Player(chara1);
		Player J2 = new Player(chara2);
		
		this.engine.init(720, 1280, 500, J1, J2);
		
		assertTrue(!this.engine.isGameOver());
		
		chara1.takeAttack(chara2.getMaxLife(), 100, 100);
		
		assertTrue(this.engine.isGameOver());
	}
}
