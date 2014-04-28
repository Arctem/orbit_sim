/**
 * 
 */
package sim.simobject;

import java.awt.Color;

import sim.util.Point3D;
import de.matthiasmann.twl.Button;

/**
 * @author russell, AJ
 * 
 *         Each object of this is a planet. A planet can either orbit a sun or
 *         it can orbit another planet, making it a moon.
 */

public class Planet extends ObjectInSpace {

	private boolean habitable;
	private double period;
	private long orbitRadius;
	private double angularVelocity;
	private double angle; // the angle the planet is at in radians

	/**
	 * @param mass
	 * @param orbitRadius
	 * @param density
	 * @param velocity
	 * @param position
	 * @param color
	 */
	public Planet(double mass, long orbitRadius, long density, long velocity,
			Point3D position, Color color, boolean habitable,
			ObjectInSpace sun, long radius) {
		super(mass, radius, density, velocity, position, color, sun);
		this.orbitRadius = 0;
		this.habitable = habitable;
		this.angle = 0;

		setPeriod();
		setAngularVelocity();
	}

	/**
	 * contructs an object with a radius, and calculates the velocity
	 * 
	 * @param mass
	 * @param orbitRadius
	 * @param density
	 * @param position
	 * @param color
	 * @param sunMass
	 */
	public Planet(double mass, long orbitRadius, long density, Color color,
			ObjectInSpace sun, long radius) {
		super(mass, radius, density, (long) Math.sqrt((6.67 * Math.pow(10,
				(-11)) * sun.getMass()) / (double) orbitRadius), new Point3D(
				orbitRadius, 0, 0), color, sun);
		this.orbitRadius = orbitRadius;
		this.angle = 0;

		setPeriod();
		setAngularVelocity();
	}

	/**
	 * constructs an object with a velocity, and calculates the radius
	 * 
	 * @param mass
	 * @param density
	 * @param position
	 * @param color
	 * @param sunMass
	 * @param velocity
	 */
	public Planet(double mass, long density, Color color, ObjectInSpace sun,
			long velocity, long radius) {
		super(
				mass,
				radius,
				density,
				velocity,
				new Point3D(
						(long) ((6.67 * Math.pow(10, (-11)) * sun.getMass()) / Math
								.pow(velocity, 2)), 0, 0), color, sun);
		this.orbitRadius = (long) ((6.67 * Math.pow(10, (-11)) * sun.getMass()) / Math
				.pow(velocity, 2));
		this.angle = 0;

		setPeriod();
		setAngularVelocity();
	}

	/**
	 * @return the period of the orbit
	 */
	public double getPeriod() {
		return this.period;
	}

	/**
	 * set the period based off the other variables in place
	 */
	public void setPeriod() {
		this.period = Math
				.sqrt(((4.0 * Math.pow(Math.PI, 2) * Math.pow(
						(orbitRadius * 1000), 3)) / ((6.67 * Math
						.pow(10, (-11))) * getSun().getMass())));
	}

	public double getAngularVelocity() {
		return this.angularVelocity;
	}

	/**
	 * sets the angular velocity of the planet based off other variables
	 * 
	 * @note must be called after setPeriod to work properly.
	 */
	public void setAngularVelocity() {
		this.angularVelocity = (2 * Math.PI) / this.period;
	}

	/**
	 * sets the radius, and what velocity is needed to orbit at that radius.
	 * 
	 * @param radius
	 */
	public void setOrbitRadius(long radius) {
		super.setRadius(radius);
		super.setVelocity((long) Math.sqrt((double) (getRadius()
				* Math.pow(getVelocity(), 2) / (double) radius)));
		setPeriod();
	}

	/**
	 * 
	 * @return the orbit radius of the planet
	 */
	public long getOrbitRadius() {
		return this.orbitRadius;
	}

	/**
	 * sets the velocity, and what radius is needed for that velocity to orbit
	 * 
	 * @param velocity
	 */
	public void setVelocity(long velocity) {
		super.setVelocity(velocity);
		super.setRadius((long) ((double) (getRadius() * Math.pow(getVelocity(),
				2)) / Math.pow(velocity, 2)));

		setPeriod();
	}

	/**
	 * calculates the new position of the planet after t seconds
	 * 
	 * @param t
	 *            the time elapsed in seconds
	 */
	public void calculateNewPosition(long t) {
		long x;
		long y;

		angle += angularVelocity * t;
		if (angle >= (2 * Math.PI)) {
			angle -= (2 * Math.PI);
		}

		x = (long) (this.orbitRadius * Math.cos(angle));
		y = (long) (this.orbitRadius * Math.sin(angle));

		// Adjust for if Sun is not in the center of the Universe i.e. another
		// planet.
		x += this.getSun().getPosition().getX();
		y += this.getSun().getPosition().getY();

		this.setPosition(x, y, 0);
	}

	public void calculateNewPosition(long t, double tilt) {
		long x, y, z;

		calculateNewPosition(t);

	}

	@Override
	public void step(long t) {
		super.step(t);

		calculateNewPosition(t);
	}

	

}
