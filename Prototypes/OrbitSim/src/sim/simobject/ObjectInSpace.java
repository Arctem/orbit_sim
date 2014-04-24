/**
 * 
 */
package sim.simobject;

import gui.GUIObject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import sim.util.Point3D;

/**
 * @author russell
 * 
 */
public abstract class ObjectInSpace implements SimObject, GUIObject {
	// we might need to make a component velocity ivar.
	private int mass, radius, density, speed;
	private Point3D position, velocity;

	private Color color;

	/**
	 * @param mass
	 * @param radius
	 * @param density
	 * @param velocity
	 * @param position
	 * @param color
	 */
	public ObjectInSpace(int mass, int radius, int density, int speed,
			Point3D position, Color color) {
		super();
		this.mass = mass;
		this.radius = radius;
		this.density = density;
		this.speed = speed;
		this.velocity = new Point3D(0, speed, 0);
		this.position = position;
		this.color = color;
	}

	/**
	 * 
	 * @param simObjects
	 * @param t the amount of time a step lasts, in milliseconds.
	 */
	public void step(ArrayList<ObjectInSpace> simObjects, int t) {
		Point3D forces = calculateForces(simObjects);
		
		// always start a planet on the right side of the sun, 
		// with its initial velocity completely in the positive y direction.
		// suns are assumed stationary.
		
		// multiply the forces by the mass to get the acceleration
		forces.multiply(mass);
		
		// multiply the acceleration (forces variable) by the time elapsed to get
		// the change in velocity.
		forces.multiply(t);
		
	}
	
	/**
	 * calculates all of the forces on the object
	 * @param simObjects the objects to calculate the forces with
	 * 
	 */
	private Point3D calculateForces(ArrayList<ObjectInSpace> simObjects){
		ArrayList<Point3D> forces = new ArrayList<Point3D>(); // the components of the force from each force
		double force; // stores the magnitude of the force
		double theta; // angle of the vector
		Point3D components;
		
		for(ObjectInSpace simObject : simObjects){
			// don't calculate any forces from itself
			if(simObject == this)
				continue;
			
			force = 0;
			theta = 0;
			
			// calculate the force between the objects
			force = (6.6726 * this.mass * simObject.mass * Math.pow(10, (-11))) /
					(Math.pow(Point3D.distance(this.position, simObject.position), 2));
			
			// calculate the direction the force is pulling the object.
			// assuming 2D forces
			theta = Math.atan(Math.abs((this.position.getY() - simObject.position.getY())) / 
					Math.abs((this.position.getX() - simObject.position.getX())));
			
			components = new Point3D((int)(Math.cos(theta) * force), (int)(Math.sin(theta) * force), 0);
			forces.add(components);
			
		}
		// sum the forces arraylist to figure out the total forces on each object
		// each step = 1 second? if so, see how much speed changes, and then how far it goes?
		components = new Point3D(0, 0, 0);
		
		for(Point3D point : forces){
			components.add(point);
		}
		
		return components;
	}

	public void render(Graphics2D g) {
		
	}
	
	public void create() {

	}

	public void delete() {

	}

	public void changeProperty(String propName, int value) {

	}

	/**
	 * @return the mass
	 */
	public int getMass() {
		return mass;
	}

	/**
	 * @return the radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * @return the density
	 */
	public int getDensity() {
		return density;
	}

	/**
	 * @return the velocity
	 */
	public int getSpeed() {
		return speed;
	}
	
	public Point3D getVelocity() {
		return velocity;
	}

	public void setVelocity(Point3D velocity) {
		this.velocity = velocity;
	}

	/**
	 * @return the position
	 */
	public Point3D getPosition() {
		return position;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

}
