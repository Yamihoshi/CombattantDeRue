import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import engine.components.character.CharacterFabrique;
import engine.components.character.Personnage;
import engine.impl.EngineImpl;
import engine.services.EngineService;
import engine.services.FightCharService;

public class CharacterTest {

	protected FightCharService fc;
	protected EngineService engine;
	//protected Player p1, p2;
	@Before
	public void init(){
		engine = new EngineImpl();
		//engine.init(h, w, s, j1, j2);
		fc = CharacterFabrique.init(Personnage.CHUN_LI, null, false);
	}
	@Test
	public void moveRight() {
		
	}

}
