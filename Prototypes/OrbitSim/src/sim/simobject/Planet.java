/**
 * 
 */
package sim.simobject;

import java.awt.Color;

import sim.util.Point3D;

/**
 * @author russell
 *
 */
public class Planet extends ObjectInSpace {
	
	private boolean habitable;
	private double period;

	/**
	 * @param mass
	 * @param radius
	 * @param density
	 * @param velocity
	 * @param position
	 * @param color
	 */
	public Planet(int mass, int radius, int density, int velocity,
			Point3D position, Color color, boolean habitable, Sun sun) {
		super(mass, radius, density, velocity, position, color, sun);
		this.habitable = habitable;
		setPeriod();
	}
	
	/**
	 * contructs an object with a radius, and calculates the velocity
	 * @param mass
	 * @param radius
	 * @param density
	 * @param position
	 * @param color
	 * @param sunMass
	 */
	public Planet(int mass, int radius, int density, 
			Color color, Sun sun){
		super(mass, radius, density, (int)Math.sqrt((6.67 * Math.pow(10, (-11)) * sun.getMass())/(double)radius), 
				new Point3D(radius, 0, 0), color, sun);
	}
	
	/**
	 * constructs an object with a velocity, and calculates the radius
	 * @param mass
	 * @param density
	 * @param position
	 * @param color
	 * @param sunMass
	 * @param velocity
	 */
	public Planet(int mass, int density, 
			Color color, Sun sun, int velocity){
		super(mass, (int)((6.67 * Math.pow(10, (-11)) * sun.getMass())/Math.pow(velocity, 2)), density, velocity, 
				new Point3D((int)((6.67 * Math.pow(10, (-11)) * sun.getMass())/Math.pow(velocity, 2)), 0, 0), color, sun);
	}


	/**
	 * @return the period of the orbit
	 */
	public double getPeriod(){
		return this.period;
	}
	
	/**
	 * set the period based off the other variables in place
	 */
	public void setPeriod(){
		this.period = Math.sqrt((4 * Math.pow(Math.PI, 2) * Math.pow(getRadius(), 3)) /
				(double)(6.67 * Math.pow(10, (-11)) * getSun().getMass()));
	}
	
	/**
	 * sets the radius, and what velocity is needed to orbit at that radius.
	 * @param radius
	 */
	public void setRadius(int radius){
		super.setRadius(radius);
		super.setVelocity((int)Math.sqrt((double)(getRadius() * Math.pow(getVelocity(), 2)/(double)radius)));
		setPeriod();
	}

	/**
	 * sets the velocity, and what radius is needed for that velocity to orbit
	 * @param velocity
	 */
	public void setVelocity(int velocity){
		super.setVelocity(velocity);
		super.setRadius((int)((double)(getRadius() * Math.pow(getVelocity(), 2))/Math.pow(velocity, 2)));
		setPeriod();
	}
}
