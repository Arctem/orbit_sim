/**
 * 
 */
package gui.menu;

import gui.GUIObject;

import java.awt.Graphics2D;
import java.util.ArrayList;

import de.matthiasmann.twl.Button;
import sim.simobject.SimObject;

/**
 * @author russell
 * 
 */
public class Menu implements GUIObject {

	private boolean visible;
	private SimObject simObject;
	private ArrayList<MenuElement> elements;

	/**
	 * @param visible
	 * @param simObject
	 */
	public Menu(boolean visible, SimObject simObject) {
		this.visible = visible;
		this.simObject = simObject;
		this.elements = new ArrayList<MenuElement>();
	}

	public void toggleVisible() {
		this.visible = !this.visible;
	}

	public void start() {

	}

	public void stop() {

	}

	public void update() {

	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible
	 *            the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * @return the simObject
	 */
	public SimObject getSimObject() {
		return simObject;
	}

	/**
	 * @param simObject
	 *            the simObject to set
	 */
	public void setSimObject(SimObject simObject) {
		this.simObject = simObject;
	}

	/**
	 * @return the elements
	 */
	public ArrayList<MenuElement> getElements() {
		return elements;
	}

	public void addElement(MenuElement element) {
		this.elements.add(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.GUIObject#createButton()
	 */
	@Override
	public Button createButton() {
		// TODO Auto-generated method stub
		return null;
	}

}
