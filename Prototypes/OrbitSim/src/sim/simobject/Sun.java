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
public class Sun extends ObjectInSpace {

	int brightness;

	/**
	 * @param mass
	 * @param radius
	 * @param density
	 * @param velocity
	 * @param position
	 * @param color
	 * @param brightness
	 */
	public Sun(int mass, int radius, int density, int velocity,
			Point3D position, Color color, int brightness) {
		super(mass, radius, density, velocity, position, color);
		this.brightness = brightness;
	}

}
