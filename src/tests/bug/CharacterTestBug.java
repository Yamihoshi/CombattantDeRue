package tests.bug;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bug.FighterImpBug;
import bug.TechniqueBug;
import engine.components.character.CharacterFabrique;
import engine.components.character.Personnage;
import engine.components.character.Technique;
import engine.components.player.Commande;
import engine.components.player.Player;
import engine.contracts.EngineContract;
import engine.contracts.TechniqueContract;
import engine.impl.EngineBugImpl;
import engine.impl.EngineImpl;
import engine.services.FightCharService;
import engine.services.TechService;
import tests.abstractTest.CharacterTestAbstract;

public class CharacterTestBug extends CharacterTestAbstract{

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
	
	@Override
	public void testnoMvmtWhenTeching()
	{
		TechService tech = new TechniqueContract(new TechniqueBug());
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
	
	@Override
	public void testnoMvmtWhenStunned()
	{
		TechService tech = new TechniqueContract(new TechniqueBug());
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

}
