package processing;

import processing.core.PVector;

public class Planet {
	//Attributes
	PVector position;
	PVector velocity;
	PVector acceleration;
	float mass;
	float radius;
	  
	//Constructor
	public Planet(float planetMass, float x, float y, float z, 
	float xVel, float yVel, float zVel) {
	    mass = planetMass;
	    radius = 0;
	    position = new PVector(x, y, z);
	    velocity = new PVector(xVel, yVel, zVel);
	    acceleration = new PVector(0, 0, 0);
	}
	  
	//Updates the vectors
	public void update() {
	    velocity.add(acceleration);
	    position.add(velocity);
	    acceleration.mult(0);
	}
	  
	//Renders the Planet
	public void render() {
	    determineColor();
	    applet().pushMatrix();
	    applet().translate(position.x, position.y, position.z);
	    applet().sphere(radius);
	    applet().popMatrix();
	}
	
	//Determines what type of planet it is + assigns a color to the object
	private void determineColor() {
	    applet().noStroke();
	    if (mass > applet().getBlackHoleMass()) applet().fill(0, 0, 0, 255);
	    else if (mass > applet().getSunMass()) applet().fill(255, 255, 0);
	    else if (mass > applet().getGasGiantMass()) applet().fill(255, 128, 0);
	    else if (mass > applet().getPlanetMass()) applet().fill(0, 0, 255);
	    else if (mass > applet().getMoonMass()) applet().fill(200, 200, 200);
	    else applet().fill(0, 100, 100);
	    if (mass > applet().getBlackHoleMass()) radius = 100;
        else radius = mass * applet().getZoom();
	}
	  
	//Has two planets gravitate toward each other - a.k.a. "The Physics"
	public void gravitate(Planet otherPlanet, float G) {
	    //Calculate the distance and gravitational force between the planets
	    PVector distanceVector = PVector.sub(position, otherPlanet.position);
	    float distance = distanceVector.mag();
	    float gravitationalForce = G * mass * otherPlanet.mass / (distance * distance);
	    //Calculate the magnitude of each planet's acceleration
	    float thisPlanetAccelerationMagnitude = gravitationalForce / mass;
	    float otherPlanetAccelerationMagnitude = gravitationalForce / otherPlanet.mass;
	    //Calculate the acceleration vector for this planet
	    PVector thisPlanetAccelerationVector = PVector.sub(otherPlanet.position, position);
	    thisPlanetAccelerationVector.normalize();
	    thisPlanetAccelerationVector.mult(thisPlanetAccelerationMagnitude);
	    //Calculate the acceleration vector for the other planet
	    PVector otherPlanetAccelerationVector = PVector.sub(position, otherPlanet.position);
	    otherPlanetAccelerationVector.normalize();
	    otherPlanetAccelerationVector.mult(otherPlanetAccelerationMagnitude);
	    //Add the calculated acceleration vectors to each planet's own acceleration vector
	    acceleration.add(thisPlanetAccelerationVector);
	    otherPlanet.acceleration.add(otherPlanetAccelerationVector);
	}
	
	//If a planet has collided with another planet
	public boolean hasCollided(Planet otherPlanet) {
	    return position.dist(otherPlanet.position) < radius + otherPlanet.radius;
	}
	  
	private GravApplet applet() {
	     return GravApplet.getInstance();
	}
}
