/**
 * 
 */
package sim;

import gui.menu.MainMenu;

import java.util.ArrayList;

import sim.simobject.ObjectInSpace;
import sim.simobject.SimObject;

/**
 * @author russell
 * 
 */
public class SolarSystem {

	private ArrayList<ObjectInSpace> simObjects;
	private MainMenu mainMenu;
	private int timeScale;

	/**
	 * @param simObjects
	 * @param mainMenu
	 */
	public SolarSystem(ArrayList<ObjectInSpace> simObjects, MainMenu mainMenu) {
		this.simObjects = simObjects;
		this.mainMenu = mainMenu;
	}

	public void step() {
		for (ObjectInSpace o : simObjects) {
			o.step();
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
	public int getTimeScale() {
		return timeScale;
	}

	/**
	 * @param timeScale
	 *            the timeScale to set
	 */
	public void setTimeScale(int timeScale) {
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
