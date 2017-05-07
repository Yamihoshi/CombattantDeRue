package tests.abstractTest;

import static org.junit.Assert.*;

import org.junit.*;

public abstract class TechniqueTestAbstract {
	
	@Before
	public abstract void beforeTests();

	@After
	public abstract void afterTests();
	
}
