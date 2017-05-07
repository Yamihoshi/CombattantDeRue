package tests.abstractTest;

import static org.junit.Assert.*;

import org.junit.*;

import engine.components.character.Technique;
import engine.components.player.Commande;
import engine.contracts.TechniqueContract;
import engine.services.EngineService;
import engine.services.TechService;

public abstract class CharacterTestAbstract {
	
	protected EngineService engine;
	
	@Before
	public abstract void beforeTests();

	@After
	public abstract void afterTests();
	
	@Test
	public void testmoveLeft()
	{
		this.engine.getCharacter(1).getCharBox().setPositionX(this.engine.getWidth()-1);
		this.engine.getCharacter(1).moveLeft();
		assertTrue(this.engine.getCharacter(1).getPositionX()==this.engine.getWidth()-1-this.engine.getCharacter(1).getSpeed());
	}
	
	@Test
	public void moveRight()
	{
		this.engine.getCharacter(1).getCharBox().setPositionX(1);
		this.engine.getCharacter(1).moveRight();
		assertTrue(this.engine.getCharacter(1).getPositionX()==1+this.engine.getCharacter(1).getSpeed());
	}
	
	@Test
	public void moveLeft_Border()
	{
		this.engine.getCharacter(0).getCharBox().setPositionX(1);
		this.engine.getCharacter(0).moveLeft();
		assertTrue(this.engine.getCharacter(0).getCharBox().getPositionX().get()==1);
	}
	
	@Test
	public void moveRight_Border()
	{
		this.engine.getCharacter(1).getCharBox().setPositionX(this.engine.getWidth()-1-this.engine.getCharacter(1).getLargeur());
		this.engine.getCharacter(1).moveRight();
		assertTrue(this.engine.getCharacter(1).getCharBox().getPositionX().get()==this.engine.getWidth()-this.engine.getCharacter(1).getLargeur());
	}
	
	@Test
	public void moveLeft_collision()
	{
		this.engine.getCharacter(0).getCharBox().setPositionX(1);
		this.engine.getCharacter(1).getCharBox().setPositionX(1+this.engine.getCharacter(0).getLargeur()+1);
		this.engine.getCharacter(1).moveLeft();
		assertTrue(this.engine.getCharacter(1).getCharBox().getPositionX().get()==1+this.engine.getCharacter(0).getLargeur()+1);

	}
	
	@Test
	public void moveRight_collision()
	{
		this.engine.getCharacter(0).getCharBox().setPositionX(1);
		this.engine.getCharacter(1).getCharBox().setPositionX(1+this.engine.getCharacter(0).getLargeur()+1);
		this.engine.getCharacter(0).moveRight();
		assertTrue(this.engine.getCharacter(0).getCharBox().getPositionX().get()==1);
	}
	
	@Test
	public void moveDownLeft()
	{
		this.engine.getCharacter(1).getCharBox().setPositionX(this.engine.getWidth()-1);
		int pre_height = this.engine.getCharacter(1).getHauteur();
		this.engine.getCharacter(1).moveDownLeft();
		assertTrue(pre_height!=this.engine.getCharacter(1).getHauteur());
		assertTrue(this.engine.getCharacter(1).getPositionX()==this.engine.getWidth()-1-this.engine.getCharacter(1).getSpeed()/2);
	}
	
	@Test
	public void testmoveDownRight()
	{
		this.engine.getCharacter(1).getCharBox().setPositionX(1);
		int pre_height = this.engine.getCharacter(1).getHauteur();
		this.engine.getCharacter(1).moveDownRight();
		assertTrue(pre_height!=this.engine.getCharacter(1).getHauteur());
		assertTrue(this.engine.getCharacter(1).getPositionX()==1+this.engine.getCharacter(1).getSpeed()/2);
	}
	
	@Test
	public void testmoveDownLeft_Border()
	{
		this.engine.getCharacter(0).getCharBox().setPositionX(1);
		this.engine.getCharacter(0).moveDownLeft();
		assertTrue(this.engine.getCharacter(0).getCharBox().getPositionX().get()==1);
	}
	
	@Test
	public void testmoveDownRight_Border()
	{
		this.engine.getCharacter(1).getCharBox().setPositionX(this.engine.getWidth()-1-this.engine.getCharacter(1).getLargeur());
		this.engine.getCharacter(1).moveDownRight();
		assertTrue(this.engine.getCharacter(1).getCharBox().getPositionX().get()==this.engine.getWidth()-this.engine.getCharacter(1).getLargeur());
	}
	
	@Test
	public void testmoveDownLeft_collision()
	{
		this.engine.getCharacter(0).getCharBox().setPositionX(1);
		this.engine.getCharacter(1).getCharBox().setPositionX(1+this.engine.getCharacter(0).getLargeur()+1);
		this.engine.getCharacter(1).moveDownLeft();
		assertTrue(this.engine.getCharacter(1).getCharBox().getPositionX().get()==1+this.engine.getCharacter(0).getLargeur()+1);

	}
	
	@Test
	public void testmoveDownRight_collision()
	{
		this.engine.getCharacter(0).getCharBox().setPositionX(1);
		this.engine.getCharacter(1).getCharBox().setPositionX(1+this.engine.getCharacter(0).getLargeur()+1);
		this.engine.getCharacter(0).moveDownRight();
		assertTrue(this.engine.getCharacter(0).getCharBox().getPositionX().get()==1);
	}
	
	@Test
	public void testnoMvmtWhenTeching()
	{
		TechService tech = new TechniqueContract(new Technique());
		tech.init(10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
		this.engine.getCharacter(0).startTech(tech);
		int pre_X = this.engine.getCharacter(0).getCharBox().getPositionX().get(); 
		
		this.engine.step(Commande.LEFT,Commande.NEUTRAL);
		assertTrue(this.engine.getCharacter(0).getCharBox().getPositionX().get()==pre_X);
		
		this.engine.step(Commande.DOWNLEFT,Commande.NEUTRAL);
		assertTrue(this.engine.getCharacter(0).getCharBox().getPositionX().get()==pre_X);
		
		this.engine.step(Commande.RIGHT,Commande.NEUTRAL);
		assertTrue(this.engine.getCharacter(0).getCharBox().getPositionX().get()==pre_X);
		
		this.engine.step(Commande.DOWNRIGHT,Commande.NEUTRAL);
		assertTrue(this.engine.getCharacter(0).getCharBox().getPositionX().get()==pre_X);
	}
	
	@Test
	public void testnoMvmtWhenStunned()
	{
		TechService tech = new TechniqueContract(new Technique());
		tech.init(10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
		this.engine.getCharacter(0).takeAttack(10,10,10);
		
		int pre_X = this.engine.getCharacter(0).getCharBox().getPositionX().get(); 
		
		this.engine.step(Commande.LEFT,Commande.NEUTRAL);
		assertTrue(this.engine.getCharacter(0).getCharBox().getPositionX().get()==pre_X);
		
		this.engine.step(Commande.DOWNLEFT,Commande.NEUTRAL);
		assertTrue(this.engine.getCharacter(0).getCharBox().getPositionX().get()==pre_X);
		
		this.engine.step(Commande.RIGHT,Commande.NEUTRAL);
		assertTrue(this.engine.getCharacter(0).getCharBox().getPositionX().get()==pre_X);
		
		this.engine.step(Commande.DOWNRIGHT,Commande.NEUTRAL);
		assertTrue(this.engine.getCharacter(0).getCharBox().getPositionX().get()==pre_X);
	}
	
	@Test
	public void testTakeAttack_vie()
	{
		int vie = this.engine.getCharacter(0).getLife();
		this.engine.getCharacter(0).takeAttack(10,10,10);
		assertTrue(this.engine.getCharacter(0).getLife()==vie-10);
	}
	
	@Test
	public void testTakeAttack_Stunned()
	{
		this.engine.getCharacter(0).takeAttack(10,10,10);
		for(int i=0;i<10;i++)
		{
			assertTrue(this.engine.getCharacter(0).isHitStunned());
			this.engine.step(Commande.NEUTRAL, Commande.NEUTRAL);
		}
		
		assertFalse(this.engine.getCharacter(0).isHitStunned());
	}
}
