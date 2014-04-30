/**
 * 
 */
package sim.simobject;

import gui.GUIObject;

import java.awt.Color;

import sim.SolarSystem;
import sim.util.Point3D;
import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.Widget;

/**
 * @author russell, AJ
 * 
 *         This is an abstract class that contains all the information needed
 *         for each object in space Every object, like planets, suns, and others
 *         inherit from this class.
 */
public abstract class ObjectInSpace implements SimObject, GUIObject {
	private long radius, density, velocity;
	private double mass;
	private ObjectInSpace sun;
	private Point3D position;
	private Color color;
	private SolarSystem parent;
	protected Widget detailedMenu;
	protected boolean menuDirty;

	/**
	 * @param mass
	 * @param radius
	 * @param density
	 * @param velocity
	 * @param position
	 * @param color
	 */
	public ObjectInSpace(double mass, long radius, long density, long velocity,
			Point3D position, Color color, ObjectInSpace sun) {
		super();
		this.mass = mass;
		this.radius = radius;
		this.density = density;
		this.velocity = velocity;
		this.position = position;
		this.color = color;
		this.sun = sun;
		this.parent = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.GUIObject#createButton()
	 */
	@Override
	public Button createButton() {
		Button b = new Button(this.getClass().getName());
		b.setTooltipContent(this.toString());
		final ObjectInSpace t = this; // There's gotta be a better way to do
										// that.
		b.addCallback(new Runnable() {
			@Override
			public void run() {
				parent.setSelectedObject(t);
			}
		});

		return b;
	}

	/**
	 * @return
	 */
	public Widget getDetailedMenu() {
		if (detailedMenu == null || menuDirty) {

			detailedMenu = new Widget();

			final ObjectInSpace t = this;

			Label massInfo = new Label("Mass: " + this.getMass() + " kg");
			detailedMenu.add(massInfo);
			massInfo.setSize(120, 33);
			massInfo.setPosition(0, 0);

			Button plusMass = new Button("Increase Mass");
			plusMass.setTooltipContent("Double the mass.");
			plusMass.addCallback(new Runnable() {
				@Override
				public void run() {
					t.setMass(t.getMass() * 2);
				}
			});
			detailedMenu.add(plusMass);
			plusMass.setSize(100, 33);
			plusMass.setPosition(massInfo.getWidth(), 0);

			Button minusMass = new Button("Decrease Mass");
			minusMass.setTooltipContent("Halve the mass.");
			minusMass.addCallback(new Runnable() {
				@Override
				public void run() {
					t.setMass(t.getMass() / 2);
				}
			});
			detailedMenu.add(minusMass);
			minusMass.setSize(100, 33);
			minusMass.setPosition(massInfo.getWidth() + plusMass.getWidth(), 0);

			this.menuDirty = false;
		}
		return detailedMenu;
	}

	/**
	 * Perform calculations on each step of the simulation
	 * 
	 * @param t
	 *            the time elapsed in the step in seconds
	 */
	public void step(long t) {

	}

	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * @return the radius
	 */
	public long getRadius() {
		return radius;
	}

	/**
	 * sets the radius
	 * 
	 * @param radius
	 */
	public void setRadius(long radius) {
		this.radius = radius;
	}

	/**
	 * @return the density
	 */
	public long getDensity() {
		return density;
	}

	/**
	 * sets the velocity
	 * 
	 * @param velocity
	 */
	public void setVelocity(long velocity) {
		this.velocity = velocity;
	}

	/**
	 * @return the velocity
	 */
	public long getVelocity() {
		return velocity;
	}

	/**
	 * @return the position
	 */
	public Point3D getPosition() {
		return position;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	public ObjectInSpace getSun() {
		return sun;
	}

	public void setSun(Sun sun) {
		this.sun = sun;
	}

	public void setMass(double mass) {
		this.mass = mass;
		this.parent.updateOrbitersOf(this);
		this.menuDirty = true;
	}

	/**
	 * se the position of the object
	 * 
	 * @param position
	 *            the position to set it to
	 */
	public void setPosition(Point3D position) {
		this.position = position;
	}

	/**
	 * set the Cartesian position of the object
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param z
	 *            the z coordinate
	 */
	public void setPosition(long x, long y, long z) {
		this.position.setXYZ(x, y, z);
	}

	public SolarSystem getParent() {
		return parent;
	}

	public void setParent(SolarSystem parent) {
		this.parent = parent;
	}

	public boolean isMenuDirty() {
		return menuDirty;
	}

	public void setMenuDirty(boolean menuDirty) {
		this.menuDirty = menuDirty;
	}

}
