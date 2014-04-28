/**
 * 
 */
package sim.simobject;

import java.awt.Graphics2D;

import gui.GUIObject;

/**
 * @author russell
 * 
 */
public class AsteroidBelt implements SimObject {

	private int asteroidCount, avgSize, innerRad, outerRad;

	/**
	 * @param asteroidCount
	 * @param avgSize
	 * @param innerRad
	 * @param outerRad
	 */
	public AsteroidBelt(int asteroidCount, int avgSize, int innerRad,
			int outerRad) {
		super();
		this.asteroidCount = asteroidCount;
		this.avgSize = avgSize;
		this.innerRad = innerRad;
		this.outerRad = outerRad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sim.simobject.SimObject#step()
	 */
	@Override
	public void step(long t) {
		// TODO Auto-generated method stub

	}

}
