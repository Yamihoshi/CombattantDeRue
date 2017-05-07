package tests;

import engine.contracts.EngineContract;
import engine.impl.EngineImpl;
import tests.abstractTest.ComboTestAbstract;

public class ComboTest extends ComboTestAbstract{

	@Override
	public void beforeTests() {
		// TODO Auto-generated method stub
		this.engine = new EngineContract(new EngineImpl());
	}

	@Override
	public void afterTests() {
		// TODO Auto-generated method stub
		this.engine = null;
	}

}
