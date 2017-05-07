package tests.bug;

import bug.TechniqueBug;
import engine.components.character.Technique;
import engine.contracts.TechniqueContract;
import tests.abstractTest.TechniqueTestAbstract;

public class TechniqueTestBug extends TechniqueTestAbstract{

	@Override
	public void beforeTests() {
		// TODO Auto-generated method stub
		this.tech = new TechniqueContract(new TechniqueBug());
	}

	@Override
	public void afterTests() {
		// TODO Auto-generated method stub
		
	}

}
