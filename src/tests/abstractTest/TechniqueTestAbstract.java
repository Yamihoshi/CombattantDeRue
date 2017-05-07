package tests.abstractTest;

import static org.junit.Assert.*;

import org.junit.*;

import engine.components.character.CharacterFabrique;
import engine.components.character.Personnage;
import engine.contracts.error.PreconditionError;
import engine.services.FightCharService;
import engine.services.TechService;

public abstract class TechniqueTestAbstract {
	
	protected TechService tech;
	
	@Before
	public abstract void beforeTests();

	@After
	public abstract void afterTests();
	
	@Test
	public void testInit()
	{
		this.tech.init(10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
	}
	
	@Test
	public void testInit_Negatif()
	{
		try
		{
			this.tech.init(0, 10, 10, 10, 10, 10, 10, 10, 10, 10);
			assert(false);
		}catch(PreconditionError e){assert(true);}
	}
	
	@Test
	public void testLaunch()
	{
		this.tech.init(10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
		this.tech.launchTechnique();
		assertTrue(this.tech.isInStartUp());
		assertFalse(this.tech.isInRecovery());
		assertFalse(this.tech.isInHit());
	}
	
	@Test
	public void testIsInHit()
	{
		this.tech.init(10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
		this.tech.launchTechnique();
		
		FightCharService chara1 = CharacterFabrique.init(Personnage.CHUN_LI,null, true);
		FightCharService chara2 = CharacterFabrique.init(Personnage.CHUN_LI,null, false);
		
		for(int i=0;i<this.tech.getStart_up_frame();i++)
			this.tech.step(chara1, chara2);
		
		assertTrue(this.tech.isInHit());
	}
	
	@Test
	public void isInRecovery()
	{
		this.tech.init(10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
		this.tech.launchTechnique();
		
		FightCharService chara1 = CharacterFabrique.init(Personnage.CHUN_LI,null, true);
		FightCharService chara2 = CharacterFabrique.init(Personnage.CHUN_LI,null, false);
		
		for(int i=0;i<this.tech.getStart_up_frame();i++)
			this.tech.step(chara1, chara2);
		
		for(int i=0;i<this.tech.getHit_frame();i++)
			this.tech.step(chara1, chara2);
		
		assertTrue(this.tech.isInRecovery());
	}
	
}
