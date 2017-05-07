package engine.services;

public interface PlayerService {
	public FightCharService getCharacter();
	public void setCharacter(FightCharService character);
	public void init(FightCharService character);
}
