package tests.abstractTest;

import static org.junit.Assert.*;

import org.junit.*;

import engine.components.character.CharacterFabrique;
import engine.components.character.Personnage;
import engine.components.player.Commande;
import engine.components.player.Player;
import engine.contracts.EngineContract;
import engine.impl.EngineImpl;
import engine.services.EngineService;
import engine.services.FightCharService;
import engine.services.HitboxService;
import engine.services.TechService;

public abstract class ComboTestAbstract {
	
	protected EngineService engine;
	
	@Before
	public abstract void beforeTests();

	@After
	public abstract void afterTests();
	
	@Test
	public void testComboSiNonTouche()
	{
		
		FightCharService chara1 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, true);
		FightCharService chara2 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, false);
		
		Player J1 = new Player(chara1);
		Player J2 = new Player(chara2);
		
		this.engine.init(720, 1280, 500, J1, J2);
		
		int combo = chara1.getCombo();
		
		assertTrue(combo==0);
		
		int i = 0;
		
		while(i<4 && !this.engine.isGameOver())
		{
			this.engine.step(Commande.PUNCH, Commande.NEUTRAL);
			
			while(chara1.isTeching())
			{
				this.engine.step(Commande.NEUTRAL, Commande.NEUTRAL);
			}
			
			i++;
		}
		
		assertTrue("0 de combo",chara1.getCombo()==0);
	}
	
	@Test
	public void testComboSiTouche()
	{		
		FightCharService chara1 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, true);
		FightCharService chara2 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, false);
		
		Player J1 = new Player(chara1);
		Player J2 = new Player(chara2);
		
		this.engine.init(720, 1280, 500, J1, J2);
		
		int combo = chara1.getCombo();
		
		assertTrue(combo==0);
		
		int i = 0;
		
		TechService tech = chara1.getTech().get(Commande.PUNCH);
		
		while(!chara2.getCharBox().belongsTo(chara1.getCharBox().getPositionX().get()+tech.getHitbox().getLargeur(),chara1.getCharBox().getPositionY()+tech.getHitbox().getHauteur()))
		{
			this.engine.step(Commande.RIGHT, Commande.LEFT);
		}
		
		while(i<4 && !this.engine.isGameOver())
		{
			this.engine.step(Commande.PUNCH, Commande.NEUTRAL);
			
			while(chara1.isTeching())
			{
				this.engine.step(Commande.NEUTRAL, Commande.NEUTRAL);
			}
			
			i++;
		}
		
		assertTrue(i+" coups lancés -> "+i+" combos ?= "+chara1.getCombo(),chara1.getCombo()==i);
	}
	
	@Test
	public void testCombo_Reset_Frame_restante()
	{		
		FightCharService chara1 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, true);
		FightCharService chara2 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, false);
		
		Player J1 = new Player(chara1);
		Player J2 = new Player(chara2);
		
		this.engine.init(720, 1280, 500, J1, J2);
		
		int combo = chara1.getCombo();
		
		assertTrue(combo==0);
		
		int i = 0;
		
		TechService tech = chara1.getTech().get(Commande.PUNCH);
		
		while(!chara2.getCharBox().belongsTo(chara1.getCharBox().getPositionX().get()+tech.getHitbox().getLargeur(),chara1.getCharBox().getPositionY()+tech.getHitbox().getHauteur()))
		{
			this.engine.step(Commande.RIGHT, Commande.LEFT);
		}
		
		while(i<4 && !this.engine.isGameOver())
		{
			this.engine.step(Commande.PUNCH, Commande.NEUTRAL);
			
			while(chara1.isTeching())
			{
				this.engine.step(Commande.NEUTRAL, Commande.NEUTRAL);
			}
			
			i++;
		}
		
		int frameRestante = chara1.getComboService().getFrameRestante();
		
		for(i=0;i<frameRestante;i++)
			this.engine.step(Commande.NEUTRAL,Commande.NEUTRAL);
		
		assertTrue(chara1.getComboService().getFrameRestante()==0);
	}
	
	@Test
	public void testComboReset()
	{		
		FightCharService chara1 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, true);
		FightCharService chara2 = CharacterFabrique.init(Personnage.CHUN_LI, this.engine, false);
		
		Player J1 = new Player(chara1);
		Player J2 = new Player(chara2);
		
		this.engine.init(720, 1280, 500, J1, J2);
		
		int combo = chara1.getCombo();
		
		assertTrue(combo==0);
		
		int i = 0;
		
		TechService tech = chara1.getTech().get(Commande.PUNCH);
		
		while(!chara2.getCharBox().belongsTo(chara1.getCharBox().getPositionX().get()+tech.getHitbox().getLargeur(),chara1.getCharBox().getPositionY()+tech.getHitbox().getHauteur()))
		{
			this.engine.step(Commande.RIGHT, Commande.LEFT);
		}
		
		while(i<4 && !this.engine.isGameOver())
		{
			this.engine.step(Commande.PUNCH, Commande.NEUTRAL);
			
			while(chara1.isTeching())
			{
				this.engine.step(Commande.NEUTRAL, Commande.NEUTRAL);
			}
			
			i++;
		}
		
		while(chara1.getComboService().getFrameRestante()>0)
			this.engine.step(Commande.NEUTRAL, Commande.NEUTRAL);
		
		assertTrue(chara1.getCombo()==0);
	}
}
