package GUI.animations;

public enum AnimationType {

	STAND("stand");
	
	private String name = "";
	
	AnimationType(String name) {
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
}
