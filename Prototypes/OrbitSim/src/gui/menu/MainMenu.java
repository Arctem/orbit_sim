/**
 * 
 */
package gui.menu;

import java.util.ArrayList;

import sim.SolarSystem;
import sim.simobject.SimObject;
import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.Event;
import de.matthiasmann.twl.Widget;

/**
 * @author russell
 * 
 */
public class MainMenu extends Widget {

	private ArrayList<Button> elements;
	private SimObject lastSelection;
	private Widget detailedMenu;
	private SolarSystem solarSystem;

	/**
	 * 
	 */
	public MainMenu() {
		this.elements = new ArrayList<Button>();
		this.lastSelection = null;
		this.solarSystem = null;
	}

	protected void layout() {
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).setPosition(50, 50 + i * 33);
			elements.get(i).setSize(100, 33);
		}
		this.updateDetailedMenu();
	}

	protected boolean handleEvent(Event e) {
		boolean hitGUI = super.handleEvent(e);

		if (e.getKeyChar() == '+' || e.getKeyChar() == '=') {
			this.solarSystem.setTimeScale(2 * this.solarSystem.getTimeScale());
		} else if (e.getKeyChar() == '-') {
			this.solarSystem.setTimeScale(this.solarSystem.getTimeScale() / 2);
		}

		if (e.getMouseWheelDelta() != 0) {
			Renderer.renderer.zoom(e.getMouseWheelDelta());
		}
		return hitGUI;
	}

	public void update() {
		if (this.solarSystem.getSelectedObject() != null
				&& this.solarSystem.getSelectedObject().isMenuDirty())
			this.updateDetailedMenu();
	}

	public void updateDetailedMenu() {
		if (this.solarSystem.getSelectedObject() != this.lastSelection
				|| (this.solarSystem.getSelectedObject() != null && this.solarSystem
						.getSelectedObject().isMenuDirty())) {

			if (this.detailedMenu != null)
				this.removeChild(this.detailedMenu);

			this.lastSelection = this.solarSystem.getSelectedObject();

			Widget detailedMenu = this.lastSelection.getDetailedMenu();
			this.add(detailedMenu);
			this.detailedMenu = detailedMenu;
			detailedMenu.adjustSize();
			detailedMenu.setPosition(0, 33 * elements.size() + 50 + 20);
		}
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
