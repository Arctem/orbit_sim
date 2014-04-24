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
	public Sun(long mass, long density, Point3D position, Color color, long brightness) {
		super(mass, 0, density, 0, position, color, null);
		this.brightness = brightness;
	}

	@Override
	public void changeProperty(String propName, int value) {
		// TODO Auto-generated method stub
		
	}

}
