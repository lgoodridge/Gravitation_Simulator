package processing;

import processing.core.PVector;

public class Explosion {
	private Particle[] particles;
	private PVector position;
	private int size;
	private long expirationTime;
	//Constants
	public static final int EXPLOSION_TIME = 5000;
	  
	public Explosion (int expSize, float x, float y, float z) {
	    position = new PVector(x, y, z);
	    size = expSize;
	    particles = new Particle[size];
	    expirationTime = applet().millis() + EXPLOSION_TIME;
	    for (int i = 0; i < size; i++) {
	        particles[i] = makeParticle();
	    }
	}
	  
	public void update() {
	    for (Particle particle : particles) {
	        particle.update();
	    }
	}
	  
	public void render() {
	    for (Particle particle : particles) {
	    	particle.render();
	    }
	}
	  
	public Particle makeParticle() {
	    float xVel = applet().random(10) * applet().randomSign();
	    float yVel = applet().random(10) * applet().randomSign();
	    float zVel = applet().random(10) * applet().randomSign();
	    PVector particleVelocity = new PVector(xVel, yVel, zVel);
	    particleVelocity.normalize();
	    particleVelocity.mult(applet().getExplosionSpeed());
	    return new Particle(position.x, position.y, position.z, 
	    particleVelocity.x, particleVelocity.y, particleVelocity.z);
	}
	    
	boolean hasExpired() {
	    return applet().millis() > expirationTime;
	}
	
	private GravApplet applet() {
	    return GravApplet.getInstance();
	}
}
