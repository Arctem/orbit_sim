/**
 * 
 */
package sim.simobject;

import gui.GUIObject;

import java.awt.Color;
import java.awt.Graphics2D;

import sim.util.Point3D;

/**
 * @author russell
 * 
 */
public abstract class ObjectInSpace implements SimObject, GUIObject {
	private long radius, density, velocity;
	private double mass;
	private Sun sun;
	private Point3D position;
	private Color color;

	/**
	 * @param mass
	 * @param radius
	 * @param density
	 * @param velocity
	 * @param position
	 * @param color
	 */
	public ObjectInSpace(double mass, long radius, long density, long velocity,
			Point3D position, Color color, Sun sun) {
		super();
		this.mass = mass;
		this.radius = radius;
		this.density = density;
		this.velocity = velocity;
		this.position = position;
		this.color = color;
		this.sun = sun;
	}

	/**
	 * 
	 * @param t the time elapsed in the step in seconds
	 */
	public void step(int t) {
		
	}
	
	public void render(Graphics2D g) {
		
	}
	
	public void create() {

	}

	public void delete() {

	}

	public void changeProperty(String propName, long value) {

	}

	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * @return the radius
	 */
	public long getRadius() {
		return radius;
	}
	
	/**
	 * sets the radius
	 * @param radius
	 */
	public void setRadius(long radius){
		this.radius = radius;
	}

	/**
	 * @return the density
	 */
	public long getDensity() {
		return density;
	}
	
	/**
	 * sets the velocity
	 * @param velocity
	 */
	public void setVelocity(long velocity){
		this.velocity = velocity;
	}

	/**
	 * @return the velocity
	 */
	public long getVelocity() {
		return velocity;
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

	public Sun getSun() {
		return sun;
	}

	public void setSun(Sun sun) {
		this.sun = sun;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public void setPosition(Point3D position) {
		this.position = position;
	}
	
	public void setPosition(long x, long y, long z){
		this.position.setXYZ(x, y, z);
	}

}
