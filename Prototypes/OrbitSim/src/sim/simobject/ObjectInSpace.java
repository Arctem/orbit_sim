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
	private int mass, radius, density, velocity;
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
	public ObjectInSpace(int mass, int radius, int density, int velocity,
			Point3D position, Color color) {
		super();
		this.mass = mass;
		this.radius = radius;
		this.density = density;
		this.velocity = velocity;
		this.position = position;
		this.color = color;
	}

	public void step() {

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
	public int getVelocity() {
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

}
