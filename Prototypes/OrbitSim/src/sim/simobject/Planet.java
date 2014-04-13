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

	/**
	 * @param mass
	 * @param radius
	 * @param density
	 * @param velocity
	 * @param position
	 * @param color
	 */
	public Planet(int mass, int radius, int density, int velocity,
			Point3D position, Color color, boolean habitable) {
		super(mass, radius, density, velocity, position, color);
		this.habitable = habitable;
	}

}
