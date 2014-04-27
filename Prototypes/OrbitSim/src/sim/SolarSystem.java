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
	private long timeScale; // number of seconds per step
	private double daysElapsed; // number of Earth days elapsed since starting the sim
	private double monthsElapsed; // the number of Earth months elapsed (30.42 days per month)
	private double yearsElapsed; // the Earth years elapsed since the start of the sim (12 months per year)

	/**
	 * @param simObjects
	 * @param mainMenu
	 */
	public SolarSystem(MainMenu mainMenu, long timeScale) {
		this.simObjects = new ArrayList<SimObject>();
		this.mainMenu = mainMenu;
		this.timeScale = timeScale;
		
		daysElapsed = 0;
		monthsElapsed = 0;
		yearsElapsed = 0;
	}

	public void step() {
		for (SimObject o : simObjects) {
			o.step(timeScale);
		}
		
		// increment time elasped
		daysElapsed += ((double)(timeScale)) / 86400.0;
		
		// if more than 30.42 days have elapsed, increment months
		if(daysElapsed >= 30.42){
			monthsElapsed++;
			daysElapsed -= 30.42;
		}
		
		// if more than 12 months have elapsed, increment years
		if(monthsElapsed >= 12){
			yearsElapsed++;
			monthsElapsed -= 12;
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
	
	/**
	 * @return the daysElapsed
	 */
	public double getDaysElapsed() {
		return daysElapsed;
	}

	/**
	 * @return the monthsElapsed
	 */
	public double getMonthsElapsed() {
		return monthsElapsed;
	}

	/**
	 * @return the yearsElapsed
	 */
	public double getYearsElapsed() {
		return yearsElapsed;
	}
}
