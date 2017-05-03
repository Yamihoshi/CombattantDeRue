package GUI.animations;

public enum AnimationType {

	STAND("stand"),
	WALK_FORWARD("walk_forward"),
	GUARD("guard"),
	CROUCH("crouch"),
	PUNCH("punch"),
	KICK("kick"),
	START_JUMP("stand"),
	VERTICAL_JUMP_UP("stand"),
	HIT("hit"),
	SUPER_PUNCH("punch");

	
	private String name = "";
	
	AnimationType(String name) {
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
}
