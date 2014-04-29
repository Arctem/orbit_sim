/**
 * 
 */
package gui.menu;

import gui.GUIObject;

import java.awt.Graphics2D;
import java.util.ArrayList;

import sim.SolarSystem;
import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.Event;
import de.matthiasmann.twl.Widget;

/**
 * @author russell
 * 
 */
public class MainMenu extends Widget {

	private ArrayList<Button> elements;
	private SolarSystem solarSystem;

	/**
	 * 
	 */
	public MainMenu() {
		this.elements = new ArrayList<Button>();
	}

	protected void layout() {
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).setPosition(50, 50 + i * 33);
			elements.get(i).setSize(100, 33);
		}
	}

	protected boolean handleEvent(Event e) {
		boolean hitGUI = super.handleEvent(e);

		if (e.getMouseWheelDelta() != 0) {
			Renderer.renderer.zoom(e.getMouseWheelDelta());
		}
		return hitGUI;
	}

	public void update() {

	}

	public SolarSystem getSolarSystem() {
		return solarSystem;
	}

	public void setSolarSystem(SolarSystem solarSystem) {
		this.solarSystem = solarSystem;
	}

	public void addButton(Button button) {
		if (button == null)
			return;
		button.setTheme("button");
		this.elements.add(button);
		this.add(button);
	}

}
