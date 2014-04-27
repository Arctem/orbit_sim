/**
 * 
 */
package sim;

import gui.menu.MainMenu;

import java.util.ArrayList;

import sim.simobject.SimObject;

/**
 * @author russell
 * 
 */
public class SolarSystem {
	
	public static final long ASTRONOMICAL_UNIT = 149600000; // Au in km

	private ArrayList<SimObject> simObjects;
	private MainMenu mainMenu;
	private long timeScale;

	/**
	 * @param simObjects
	 * @param mainMenu
	 */
	public SolarSystem(ArrayList<SimObject> simObjects, MainMenu mainMenu) {
		this.simObjects = simObjects;
		this.mainMenu = mainMenu;
	}

	public void step(int t) {
		for (SimObject o : simObjects) {
			o.step(t);
		}
	}

	public void start() {

	}

	public void stop() {

	}

	public void reset() {

	}

	public void save() {

	}

	public static SolarSystem load() {
		return null;
	}

	public void destroy() {

	}

	/**
	 * @return the mainMenu
	 */
	public MainMenu getMainMenu() {
		return mainMenu;
	}

	/**
	 * @param mainMenu
	 *            the mainMenu to set
	 */
	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	/**
	 * @return the timeScale
	 */
	public long getTimeScale() {
		return timeScale;
	}

	/**
	 * @param timeScale
	 *            the timeScale to set
	 */
	public void setTimeScale(long timeScale) {
		this.timeScale = timeScale;
	}

	/**
	 * @return the simObjects
	 */
	public ArrayList<SimObject> getSimObjects() {
		return simObjects;
	}

	public void addSimObject(SimObject simObject) {
		this.simObjects.add(simObject);
	}
}
