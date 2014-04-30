/**
 * 
 */
package sim.simobject;

import java.awt.Graphics2D;

import de.matthiasmann.twl.Widget;
import gui.GUIObject;

/**
 * @author russell
 * 
 */
public class AsteroidBelt implements SimObject {

	private int asteroidCount, avgSize, innerRad, outerRad;
	protected boolean menuDirty;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see sim.simobject.SimObject#getDetailedMenu()
	 */
	@Override
	public Widget getDetailedMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sim.simobject.SimObject#isMenuDirty()
	 */
	@Override
	public boolean isMenuDirty() {
		// TODO Auto-generated method stub
		return this.menuDirty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sim.simobject.SimObject#setMenuDirty(boolean)
	 */
	@Override
	public void setMenuDirty(boolean menuDirty) {
		this.menuDirty = menuDirty;
	}

}
