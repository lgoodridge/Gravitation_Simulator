package processing;

import java.util.ArrayList;

import display.DisplayFrame;

import processing.core.PApplet;
import processing.core.PImage;

public class GravApplet extends PApplet {
	//Singleton Instance
	private static GravApplet instance;
	//Applet Attributes
	private ArrayList<Planet> planets;
	private ArrayList<Explosion> explosions;
	private PImage spaceBackground;
	//User View Position Variables
	private float xTranslation;
	private float yTranslation;
	private float zTranslation;
	private float xRotation;
	private float yRotation;
	private float zRotation;
	//User Graphics Options
	private float moonMass;
	private float planetMass;
	private float gasGiantMass;
	private float sunMass;
	private float blackHoleMass;
	private float explosionSpeed;
	private boolean rainbowExplosions;
	//User Constants
	private float g;
	private float zoom;
	private boolean isPaused;
	//Applet Constants
	private final int WIDTH;
	private final int HEIGHT;
	
	//Pseudo-Singleton Constructor
	public GravApplet() {
		WIDTH = DisplayFrame.WIDTH * 3 / 4 ;
		HEIGHT = DisplayFrame.HEIGHT - 50;
	}
	
	//Psuedo-Singleton Methods
	public static GravApplet getInstance() {
		return instance;
	}
	public void setInstance(GravApplet applet) {
		instance = applet;
	}
	
	//Set up an Applet
	public void setup() {
		//Initialize Window and smooth the images
		size(WIDTH, HEIGHT, P3D);
		smooth();
		//Initialize View Position variables
		xTranslation = 0;
		yTranslation = 0;
		zTranslation = 0;
		xRotation = 0;
		yRotation = 0;
		zRotation = 0;
		//Initialize User Graphics Options
		moonMass = 5;
		planetMass = 15;
		gasGiantMass = 30;
		sunMass = 50;
		blackHoleMass = 200;
		explosionSpeed = 2.0f;
		rainbowExplosions = false;
		//Initialize User Constants
		g = 16;
		zoom = 3;
		isPaused = false;
		//Initialize images and textures
		spaceBackground = loadImage("res/starfield.jpg");
		//Initialize the list of planets
		planets = new ArrayList<Planet>();
		sunAndMoonsOrbit();
		//Initialize the list of explosions
		explosions = new ArrayList<Explosion>();
	}
	
	public void draw() {
		//If its paused, don't draw
		if (isPaused) return;
		//Draw the background
		background(0);
		hint(DISABLE_DEPTH_MASK);
		image(spaceBackground, 0, 0, width, height);
		hint(ENABLE_DEPTH_MASK);
		//Ready the canvas to be drawn
		lights();
		translate(xTranslation + WIDTH / 2, yTranslation + WIDTH / 2, zTranslation);
		rotateX(radians(xRotation));
		rotateY(radians(yRotation));
		rotateZ(radians(zRotation));
		//Perform calculations and render the planets
		invokeGravity();
		for (Planet planet : planets) {
		    planet.update();
		    planet.render();
		}
		checkForCollisions();
		//Render the Explosions
		for (Explosion explosion : explosions) {
		    if (!explosion.hasExpired()) {
		    	explosion.update();
		    	explosion.render();
		    }
		}
	}
	
	//Inits a relative stable orbit
	private final void sunAndMoonsOrbit() {
		planets.add(new Planet(100, 0, 0, 0, 0, 0, 0));
		planets.add(new Planet(6, 0, 400, 0, -2, 0, 0));
		planets.add(new Planet(6, 0, -400, 0, 2, 0, 0));
		planets.add(new Planet(6, 400, 0, 0, 0, 0, -2));
		planets.add(new Planet(6, -400, 0, 0, 0, 0, 2));
	}
	
	//Has all the planets gravitate toward each other
	private void invokeGravity() {
		if (planets.size() < 2) return;
		for (int i = 0; i < planets.size() - 1; i++) {
			for (int j = i+1; j < planets.size(); j++) {
				planets.get(i).gravitate(planets.get(j), g);
			}
		}
	}

	//Check for collisions between planets
	private void checkForCollisions() {
		ArrayList<Planet> planetsToClear = new ArrayList<Planet>();
		for (int i = 0; i < planets.size() - 1; i++) {
			for (int j = i+1; j < planets.size(); j++) {
				if (planets.get(i).hasCollided(planets.get(j))) {
					if (planets.get(i).mass < planets.get(j).mass * 2.0)
						planetsToClear.add(planets.get(i));
					if (planets.get(j).mass < planets.get(i).mass * 2.0)
						planetsToClear.add(planets.get(j));
					if (planets.get(i).mass / planets.get(j).mass < 2.0 ||
						planets.get(j).mass / planets.get(i).mass < 2.0) {
						int size = (int)((planets.get(i).mass + planets.get(j).mass) * zoom);
						explosions.add(new Explosion(size, planets.get(i).position.x,
						planets.get(i).position.y, planets.get(i).position.z));
					}
				}
			}
		}
		for (Planet planetToClear : planetsToClear) {
	    	planets.remove(planetToClear);
		}
	}
	
	//Key Listener
	public void keyPressed() {
		if (key == CODED) {
		    if (keyCode == UP) {
		    	yTranslation += 5;
		    } if (keyCode == DOWN) {
		    	yTranslation -= 5;
		    } if (keyCode == LEFT) {
		    	xTranslation += 5;
		    } if (keyCode == RIGHT) {
		    	xTranslation -= 5;
		    } if (keyCode == SHIFT) {
		    	zTranslation -= 5;
		    } 
		} else {
		    if (keyCode == ENTER) {
		    	zTranslation += 5;
		    } if (key == 'p') {
		    	flipPause();
		    } if (key == 'a') {
		    	yRotation += 5;
		    } if (key == 'd') {
		    	yRotation -= 5;
		    } if (key == 'w') {
		    	xRotation += 5;
		    } if (key == 's') {
		    	xRotation -= 5;
		    } if (key == 'q') {
		    	zRotation -= 5;
		    } if (key == 'e') {
		    	zRotation += 5;
		    }
		}
	}
	
	//Add a planet
	public void addPlanet(float planetMass, float x, float y, float z,
	float vx, float vy, float vz) {
		planets.add(new Planet(planetMass, x, y, z, vx, vy, vz));
	}
	
	//Randomize the planets
	public void randomize() {
		resetView();
		planets.clear();
		planets.add(new Planet(100, 0, 0, -600, 0, 0, 0));
		for (int i = 0; i < 4; i++) {
			planets.add(new Planet(random(60), random(WIDTH), random(HEIGHT), random(300) * -1, 
			random(3) * randomSign(), random(3) * randomSign(), random(3) * randomSign()));
		}
	}
	
	//Reset the View
	public void resetView() {
		xTranslation = 0;
		yTranslation = 0;
		zTranslation = 0;
		xRotation = 0;
		yRotation = 0;
		zRotation = 0;
	}
	
	//Return 1 or -1
	public int randomSign() {
	    float rand = random(10);
	    if (rand >= 5) return 1;
	    else return -1;
	}
	
	//Flip pause / unpause
	public void flipPause() {
		isPaused = !isPaused;
	}
	
	//Get User View Variables
	public float getXTranslation() {
		return xTranslation;
	}
	public float getYTranslation() {
		return yTranslation;
	}
	public float getZTranslation() {
		return zTranslation;
	}
	
	//Set User Graphics Options
	public void setMoonMass(float newMoonMass) {
		moonMass = newMoonMass;
	}
	public void setPlanetMass(float newPlanetMass) {
		planetMass = newPlanetMass;
	}
	public void setGasGiantMass(float newGasGiantMass) {
		gasGiantMass = newGasGiantMass;
	}
	public void setSunMass(float newSunMass) {
		sunMass = newSunMass;
	}
	public void setBlackHoleMass(float newBlackHoleMass) {
		blackHoleMass = newBlackHoleMass;
	}
	public void setExplosionSpeed(float newExplosionSpeed) {
		explosionSpeed = newExplosionSpeed;
	}
	public void setRainbowExplosions(boolean newRainbowExplosions) {
		rainbowExplosions = newRainbowExplosions;
	}
	
	//Set User Constants
	public void setG(float newG) {
		g = newG;
	}
	public void setZoom(float newZoom) {
		zoom = newZoom;
	}
	public void setIsPaused(boolean newIsPaused) {
		isPaused = newIsPaused;
	}
	
	//Get User Graphics Options
	public float getMoonMass() {
		return moonMass;
	}
	public float getPlanetMass() {
		return planetMass;
	}
	public float getGasGiantMass() {
		return gasGiantMass;
	}
	public float getSunMass() {
		return sunMass;
	}
	public float getBlackHoleMass() {
		return blackHoleMass;
	}
	public float getExplosionSpeed() {
		return explosionSpeed;
	}
	public boolean getRainbowExplosions() {
		return rainbowExplosions;
	}
	
	//Get User Constants
	public float getG() {
		return g;
	}
	public float getZoom() {
		return zoom;
	}
	public boolean getIsPaused() {
		return isPaused;
	}
}
