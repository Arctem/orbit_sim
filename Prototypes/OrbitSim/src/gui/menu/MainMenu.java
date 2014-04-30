/**
 * 
 */
package gui.menu;

import gui.GUIObject;

import java.awt.Graphics2D;
import java.util.ArrayList;

import sim.SolarSystem;
import sim.simobject.ObjectInSpace;
import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.Event;
import de.matthiasmann.twl.Widget;

/**
 * @author russell
 * 
 */
public class MainMenu extends Widget {

	private ArrayList<Button> elements;
	private Button save;
	private Button load;
	private SolarSystem solarSystem;

	/**
	 * 
	 */
	public MainMenu() {
		this.elements = new ArrayList<Button>();
		this.reinitializeSaveLoadButtons();
	}

	private void reinitializeSaveLoadButtons() {
		this.removeChild(save);
		this.removeChild(load);

		save = new Button("Save");
		save.setTooltipContent("Save");
		final MainMenu t = this; // There's gotta be a better way to do
									// that.
		save.addCallback(new Runnable() {
			@Override
			public void run() {
				t.getSolarSystem().save();
			}
		});

		load = new Button("Load");
		load.setTooltipContent("Load");
		load.addCallback(new Runnable() {
			@Override
			public void run() {
				SolarSystem newSystem = SolarSystem.load();
				if (newSystem != null)
					t.setSolarSystem(newSystem);
			}
		});

		this.add(save);
		this.add(load);
	}

	protected void layout() {
		this.save.setPosition(0, 0);
		this.save.setSize(50, 33);

		this.load.setPosition(50, 0);
		this.load.setSize(50, 33);

		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).setPosition(50, 50 + i * 33);
			elements.get(i).setSize(100, 33);
		}
	}

	protected boolean handleEvent(Event e) {
		boolean hitGUI = super.handleEvent(e);

		System.out.println("event.");
		if (e.getKeyChar() == '+' || e.getKeyChar() == '=') {
			this.solarSystem.setTimeScale(2 * this.solarSystem.getTimeScale());
		} else if (e.getKeyChar() == '-') {
			this.solarSystem.setTimeScale(this.solarSystem.getTimeScale() / 2);
		}

		System.out.println(this.solarSystem.getTimeScale());

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
		this.reinitializeSaveLoadButtons();
	}

	public void addButton(Button button) {
		if (button == null)
			return;
		button.setTheme("button");
		this.elements.add(button);
		this.add(button);
	}

}
