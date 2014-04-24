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
	public Planet(long mass, long radius, long density, long velocity,
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
	public Planet(long mass, long radius, long density, 
			Color color, Sun sun){
		super(mass, radius, density, (long)Math.sqrt((6.67 * Math.pow(10, (-11)) * sun.getMass())/(double)radius), 
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
	public Planet(long mass, long density, 
			Color color, Sun sun, long velocity){
		super(mass, (long)((6.67 * Math.pow(10, (-11)) * sun.getMass())/Math.pow(velocity, 2)), density, velocity, 
				new Point3D((long)((6.67 * Math.pow(10, (-11)) * sun.getMass())/Math.pow(velocity, 2)), 0, 0), color, sun);
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
	public void setRadius(long radius){
		super.setRadius(radius);
		super.setVelocity((long)Math.sqrt((double)(getRadius() * Math.pow(getVelocity(), 2)/(double)radius)));
		setPeriod();
	}

	/**
	 * sets the velocity, and what radius is needed for that velocity to orbit
	 * @param velocity
	 */
	public void setVelocity(long velocity){
		super.setVelocity(velocity);
		super.setRadius((long)((double)(getRadius() * Math.pow(getVelocity(), 2))/Math.pow(velocity, 2)));
		setPeriod();
	}

	@Override
	public void changeProperty(String propName, int value) {
		// TODO Auto-generated method stub
		
	}
}
