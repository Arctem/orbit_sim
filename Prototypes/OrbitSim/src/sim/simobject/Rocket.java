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
 */
public class Rocket extends ObjectInSpace {

	private int maxFuel, currentFuel, power;

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
	public Rocket(int mass, int radius, int density, int velocity,
			Point3D position, Color color, int maxFuel, int currentFuel,
			int power, SimObject source, SimObject destination) {
		super(mass, radius, density, velocity, position, color);
		this.maxFuel = maxFuel;
		this.currentFuel = currentFuel;
		this.power = power;
		this.source = source;
		this.destination = destination;
	}
	
	public void render(Graphics2D g) {
		
	}

}
