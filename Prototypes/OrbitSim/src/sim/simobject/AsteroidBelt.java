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
public class AsteroidBelt implements SimObject, GUIObject {

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
	public void step() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sim.simobject.SimObject#create()
	 */
	@Override
	public void create() {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sim.simobject.SimObject#delete()
	 */
	@Override
	public void delete() {
		// TODO Auto-generated method stub

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.GUIObject#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

	}

}
