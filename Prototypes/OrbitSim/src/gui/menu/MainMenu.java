/**
 * 
 */
package gui.menu;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sim.SolarSystem;
import sim.simobject.ObjectInSpace;
import sim.simobject.Planet;
import sim.simobject.SimObject;
import sim.simobject.Sun;
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
	private Button addPlanet;

	/**
	 * 
	 */
	public MainMenu() {
		this.elements = new ArrayList<Button>();
		this.lastSelection = null;
		this.solarSystem = null;

		final MainMenu t = this;

		this.addPlanet = new Button("Add Planet");
		this.addPlanet.setTooltipContent("Add a new planet.");
		this.addPlanet.addCallback(new Runnable() {
			@Override
			public void run() {
				synchronized (t.getSolarSystem()) {
					t.newPlanetDialog();

					// system.addSimObject(new
					// Planet(60000000000000000000000000.0,
					// SolarSystem.ASTRONOMICAL_UNIT * 3 / 2, 10, new Color(124,
					// 255,
					// 12), sun, 2000, 0));
				}
			}
		});
		this.add(this.addPlanet);

		this.addPlanet.setSize(120, 33);
		this.addPlanet.setPosition(0, 0);
	}

	protected void layout() {
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).setPosition(50, 50 + i * 33);
			elements.get(i).setSize(100, 33);
		}
		this.updateDetailedMenu();
	}

	public void newPlanetDialog() {
		JTextField massField = new JTextField("60000000000000000000000000.0");
		JTextField orbitField = new JTextField("1");

		DefaultComboBoxModel sunPicker = new DefaultComboBoxModel();
		for (SimObject o : this.getSolarSystem().getSimObjects()) {
			if (o instanceof Planet || o instanceof Sun) {
				// sunPicker.addElement(o.toString());
				sunPicker.addElement(o);
			}
		}
		JComboBox sunComboBox = new JComboBox(sunPicker);

		JTextField tiltField = new JTextField("0");

		JPanel inputPanel = new JPanel();

		inputPanel.add(new JLabel("Mass (kg): "));
		inputPanel.add(massField);
		inputPanel.add(new JLabel("Orbit Radius (AU): "));
		inputPanel.add(orbitField);
		inputPanel.add(new JLabel("Object to orbit: "));
		inputPanel.add(sunComboBox);
		inputPanel.add(new JLabel("Tilt: "));
		inputPanel.add(tiltField);

		int result = JOptionPane.showConfirmDialog(null, inputPanel,
				"Please Enter the above values", JOptionPane.OK_CANCEL_OPTION);

		System.out.println(sunComboBox.getSelectedItem());
		if (result == JOptionPane.OK_OPTION) {
			ObjectInSpace sun = (ObjectInSpace) sunComboBox.getSelectedItem();
			double mass = Double.parseDouble(massField.getText());
			long radius = SolarSystem.ASTRONOMICAL_UNIT
					* Long.parseLong(orbitField.getText());

			this.getSolarSystem().addSimObject(
					new Planet(mass, radius, 10, new Color(200, 255, 30), sun,
							0, Integer.parseInt(tiltField.getText())));
		}
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
