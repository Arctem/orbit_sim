/**
 * 
 */
package sim.simobject;

import java.awt.Color;

import de.matthiasmann.twl.Button;
import sim.util.Point3D;

/**
 * @author Russell
 * 
 */
public class Star extends ObjectInSpace {

	/**
	 * @param mass
	 * @param radius
	 * @param density
	 * @param velocity
	 * @param position
	 * @param color
	 * @param sun
	 */
	public Star(Point3D position, int brightness) {
		super(0, 0, 0, 0, position, new Color(brightness, brightness,
				brightness), null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Button createButton() {
		return null;
	}


}
