package tests;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.*;

import engine.components.hitbox.HitboxImpl;
import engine.services.HitboxService;

public class HitboxTest {
	

	private HitboxService hitbox;

	public HitboxTest(){
		this.hitbox=null;
	}
	
	public HitboxService getHitbox() {
		return hitbox;
	}

	public void setHitbox(HitboxService hitbox) {
		this.hitbox = hitbox;
	}
	
	@Before
	public void beforeTests() {
		hitbox = new HitboxImpl();
		hitbox.init(new AtomicInteger(100), 120, 100, 100);
	} 

	@After
	public final void afterTests() {
		hitbox=null;
	}
	
	@Test
	public void testInit(){
		assertEquals("position x: 300",300,hitbox.getPositionX().get());
		assertEquals("position y : 300",300,hitbox.getPositionY());
	}
	
	@Test
	public void testMoveTo(){
		hitbox.moveTo(300, 300);
		assertEquals("position x: 300", 300,hitbox.getPositionX());
		assertEquals("position y : 300", 300,hitbox.getPositionY());
	}
	
	@Test
	public void testSetPosX(){
		hitbox.setPositionX(300);
		assertEquals("position x: 300", 300, hitbox.getPositionX().get());
	}
	
	@Test
	public void testSetPosY(){
		hitbox.setPositionY(300);
		assertEquals("position y: 300", 300,hitbox.getPositionY());
	}
	
	@Test
	public void testSetHauteur(){
		hitbox.setHauteur(300);
		assertEquals("hauteur x: 300", 300, hitbox.getHauteur());
	}
	
	@Test
	public void testSetPosLargeur(){
		hitbox.setLargeur(300);
		assertEquals("largeur: 300", 300,hitbox.getLargeur());
	}
}
