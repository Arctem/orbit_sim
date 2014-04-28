/**
 * 
 */
package sim.simobject;

import java.awt.Color;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see sim.simobject.SimObject#changeProperty(java.lang.String, int)
	 */
	@Override
	public void changeProperty(String propName, int value) {
		// TODO Auto-generated method stub

	}

}
