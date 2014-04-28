/**
 * 
 */
package sim.simobject;

import java.awt.Color;
import java.awt.Graphics2D;

import sim.util.Point3D;

/**
 * @author russell
 * 
 * This is a rocket that can travel between worlds.
 * 
 * Note: This has not been implemented.
 */
public class Rocket extends ObjectInSpace {

	private long maxFuel, currentFuel, power;

	private SimObject source, destination;

	/**
	 * @param mass
	 * @param radius
	 * @param density
	 * @param velocity
	 * @param position
	 * @param color
	 * @param maxFuel
	 * @param currentFuel
	 * @param power
	 * @param source
	 * @param destination
	 */
	public Rocket(long mass, long radius, long density, long velocity,
			Point3D position, Color color, long maxFuel, long currentFuel,
			long power, SimObject source, SimObject destination, Sun sun) {
		super(mass, radius, density, velocity, position, color, sun);
		this.maxFuel = maxFuel;
		this.currentFuel = currentFuel;
		this.power = power;
		this.source = source;
		this.destination = destination;
	}
	


}
