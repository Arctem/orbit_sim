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
public class MainMenu extends Widget implements GUIObject {

	private ArrayList<MenuElement> elements;
	private Button button;
	private SolarSystem solarSystem;

	/**
	 * 
	 */
	public MainMenu() {
		this.elements = new ArrayList<MenuElement>();

		button = new Button("Click");
		button.setTheme("button");
		button.setTooltipContent("This is a test.");
		button.addCallback(new Runnable() {
			public void run() {
				button.setText("D:");
			}
		});
		this.add(button);

	}

	protected void layout() {
		button.setPosition(100, 100);
		button.setSize(100, 33);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.GUIObject#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.GUIObject#create()
	 */
	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	public SolarSystem getSolarSystem() {
		return solarSystem;
	}

	public void setSolarSystem(SolarSystem solarSystem) {
		this.solarSystem = solarSystem;
	}

}
