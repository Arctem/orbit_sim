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
 * This is the sun, the center of the solar system.
 * 
 */ 
public class Sun extends ObjectInSpace {

	long brightness;

	/**
	 * @param mass
	 * @param radius
	 * @param density
	 * @param velocity
	 * @param position
	 * @param color
	 * @param brightness
	 */
	public Sun(double mass, long density, Point3D position, Color color, long brightness) {
		super(mass, 0, density, 0, position, color, null);
		this.brightness = brightness;
	} 

}
