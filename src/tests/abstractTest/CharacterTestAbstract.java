package tests.abstractTest;

import static org.junit.Assert.*;

import org.junit.*;

import engine.services.EngineService;

public abstract class CharacterTestAbstract {
	
	protected EngineService engine;
	
	@Before
	public abstract void beforeTests();

	@After
	public abstract void afterTests();
}
