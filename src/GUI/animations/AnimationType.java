package GUI.animations;

public enum AnimationType {

	STAND("stand"),
	WALK_FORWARD("walk_forward"),
	GUARD("guard");
	
	private String name = "";
	
	AnimationType(String name) {
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
}
