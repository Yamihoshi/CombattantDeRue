package engine.services;

public interface FightChar extends CharacterService {
	boolean isBlocking();
	boolean isBlockstunned();
	boolean isHitstunned();
	boolean isTeching();
	TechService tech();
}
