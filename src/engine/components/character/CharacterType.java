package engine.components.character;

public enum CharacterType {

	RANDOM("Random"),
	CHUN_LI("Chun_Li"),
	RYU("Ryu");
	
	private String name = "";
	
	CharacterType(String name) {
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
}
