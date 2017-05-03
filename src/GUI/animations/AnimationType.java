package GUI.animations;

public enum AnimationType {

	STAND("stand"),
	WALK_FORWARD("walk_forward"),
	GUARD("guard"),
	CROUCH("crouch"),
	PUNCH("punch"),
	KICK("kick"),
<<<<<<< HEAD
	START_JUMP("stand"),
	VERTICAL_JUMP_UP("stand");
=======
	HIT("hit"),
	START_JUMP("start_jump"),
	VERTICAL_JUMP_UP("vertical_jump_up");
>>>>>>> origin/master
	
	private String name = "";
	
	AnimationType(String name) {
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
}
