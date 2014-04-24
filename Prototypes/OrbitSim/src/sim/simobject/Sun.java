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
	public Sun(int mass, int density, Point3D position, Color color, int brightness) {
		super(mass, 0, density, 0, position, color, null);
		this.brightness = brightness;
	}

}
