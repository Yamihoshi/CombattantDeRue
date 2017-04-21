package engine.components.character;

public enum Personnage {

	RANDOM("Random"),
	CHUN_LI("Chun_Li"),
	RYU("Ryu");
	
	private String name = "";
	
	Personnage(String name) {
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
}
