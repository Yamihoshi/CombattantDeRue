package tests;

import engine.components.character.Technique;
import engine.contracts.TechniqueContract;
import tests.abstractTest.TechniqueTestAbstract;

public class TechniqueTest extends TechniqueTestAbstract {

	@Override
	public void beforeTests() {
		// TODO Auto-generated method stub
		this.tech = new TechniqueContract(new Technique());
	}

	@Override
	public void afterTests() {
		// TODO Auto-generated method stub
		this.tech = null;
	}

}
