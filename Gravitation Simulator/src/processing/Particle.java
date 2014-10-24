package processing;

import processing.core.PApplet;
import processing.core.PVector;

public class Particle {
	//Attributes
	private PVector position;
	private PVector velocity;
	private final long startTime;
	private long timeAlive;
	
	
	//Constructor
	public Particle (float x, float y, float z, float xVel, 
	float yVel, float zVel) {
	    position = new PVector(x, y, z);
	    velocity = new PVector(xVel, yVel, zVel);
	    startTime = applet().millis();
	    timeAlive = 0;
	}
	
	//Update Position
	public void update() {
	    position.add(velocity);
	    timeAlive = applet().millis() - startTime;
	}
	
	//Render the particle
	public void render() {
		int alpha = (int)(PApplet.max(((Explosion.EXPLOSION_TIME - timeAlive) * 255
		/ Explosion.EXPLOSION_TIME), 0));
	    float green = applet().random(255);
	    float red = PApplet.min((green + applet().random(255)), 255);
	    if (applet().getRainbowExplosions()) {
	    	applet().fill(applet().random(255), applet().random(255), 
	    	applet().random(255), alpha);
	    } else {
	    	applet().fill(red, green, 0, alpha);
	    }
	    applet().pushMatrix();
	    applet().translate(position.x, position.y, position.z);
	    applet().box(5);
	    applet().popMatrix();
	}
	
	private GravApplet applet() {
		return GravApplet.getInstance();
	}
}
