/**
 * 
 */
package gui.menu;

import gui.GUIObject;

import java.awt.Color;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import sim.SolarSystem;
import sim.simobject.ObjectInSpace;
import sim.simobject.Planet;
import sim.simobject.SimObject;
import sim.simobject.Star;
import sim.simobject.Sun;
import sim.util.Point3D;
import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.matthiasmann.twl.theme.ThemeManager;

/**
 * @author russell
 * 
 */
public class Renderer implements Runnable {
	private static final float RENDER_SCALE = 0.000005f;
	private static final float SUN_SIZE = 50f;
	private static final float PLANET_SIZE_MAX = 25f;
	private static final float PLANET_SIZE_MIN = 10f;

	/**
	 * The Renderer object - only one can exist at once.
	 */
	public static Renderer renderer;

	private ArrayList<GUIObject> guiObjects;

	private float zoomLevel, zoomGoal;
	private float xRotation;

	private SolarSystem solarSystem;
	private MainMenu mainMenu;

	private LWJGLRenderer guiRenderer;
	private GUI gui;
	private ThemeManager themeManager;

	// ----------- Variables added for Lighting Test -----------//
	private static FloatBuffer matSpecular, lightPosition, whiteLight,
			lModelAmbient;

	/**
	 * Creates a Renderer object to handle graphics.
	 * 
	 * @param mainMenu
	 *            The main menu that handles user input and will be displayed.
	 * @param solarSystem
	 *            The main simulation controller.
	 */
	public Renderer(MainMenu mainMenu, SolarSystem solarSystem) {
		this.mainMenu = mainMenu;
		this.solarSystem = solarSystem;

		this.zoomLevel = -100000f;
		this.zoomGoal = -1000f;
		this.xRotation = 0.0f;

		Renderer.renderer = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 * 
	 * Start the execution of the main project. Handles LWJGL initiation.
	 */
	public void run() {
		synchronized (this.solarSystem) {
			this.initGL();
			this.initLighting();
			this.initGUI();
		}

		while (!Display.isCloseRequested()) {
			this.render();
			Display.sync(60);
		}
		System.out.println("Display closing.");

		Display.destroy();
	}

	/**
	 * Handles rendering of all objects.
	 */
	public void render() {
		// Render stuff.
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		zoomLevel = (zoomGoal + zoomLevel * 2) / 3;

		if (Mouse.isButtonDown(1))
			xRotation += Mouse.getDX() * 0.2f;

		GL11.glLoadIdentity();
		GL11.glTranslatef(0, 0, zoomLevel);
		GL11.glRotatef(110f, 1f, 0, 0);
		GL11.glRotatef(xRotation, 0f, 0f, 1f);

		for (SimObject o : solarSystem.getSimObjects()) {
			if (o == solarSystem.getSelectedObject()) {
				// Push the GL attribute bits so that we don't wreck any
				// settings
				GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
				// Enable polygon offsets, and offset filled polygons forward by
				// 2.5
				GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
				GL11.glPolygonOffset(-2.5f, -2.5f);
				// Set the render mode to be line rendering with a thick line
				// width
				GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
				GL11.glLineWidth(2.0f);
				// Render the object
				ObjectInSpace object = (ObjectInSpace) o;
				Renderer.setColor(new Color(1.0f, 0.0f, 0.0f));
				Renderer.renderSphere(object.getPosition(),
						Renderer.determineRenderSize(object) + 1, RENDER_SCALE);
				// Set the polygon mode to be filled triangles
				GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
				GL11.glEnable(GL11.GL_LIGHTING);
				// // Set the colour to the background
				// GL11.glColor3f(0.0f, 0.0f, 0.0f);
				// // Render the object
				// GL11.RenderMesh3();
				// // Pop the state changes off the attribute stack
				// // to set things back how they were
				GL11.glPopAttrib();
			}

			if (o instanceof Planet || o instanceof Sun) {
				ObjectInSpace object = (ObjectInSpace) o;
				Renderer.setColor(object.getColor());
				Renderer.renderSphere(object.getPosition(),
						Renderer.determineRenderSize(object), RENDER_SCALE);

			} else if (o instanceof Star) {
				Star s = (Star) o;
				Renderer.setColor(s.getColor());
				GL11.glBegin(GL11.GL_POINTS);
				GL11.glVertex3f(s.getPosition().getX(), s.getPosition().getY(),
						s.getPosition().getZ());
				GL11.glEnd();
			} else {
				System.out.println("Unknown object type: " + o);
			}
		}

		this.mainMenu.update();
		gui.update();

		Display.update();
	}

	/**
	 * @param object
	 *            Object that has an unknown render size. Currently only Sun and
	 *            Planet are recognized.
	 * @return The render size of the object. Non-Sun and Planets are of size 1.
	 */
	private static float determineRenderSize(ObjectInSpace object) {
		if (object instanceof Sun)
			return Renderer.SUN_SIZE;
		else if (object instanceof Planet) {
			if (((Planet) object).getSun() instanceof Planet)
				return Renderer.PLANET_SIZE_MIN;
			else
				return Renderer.PLANET_SIZE_MAX;
		} else
			return 1;
	}

	/**
	 * Initialize window and rendering mode.
	 */
	private void initGL() {
		try {
			Display.setDisplayMode(new DisplayMode(1600, 1000));
			Display.setTitle("Orbit Simulator");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		/** The minimal distance from the camera where objects are rendered. */
		float zNear = 0.3f;
		/** The maximal distance from the camera where objects are rendered. */
		float zFar = 200000f;
		/** Defines the field of view. */
		int fov = 90;

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(fov,
				(float) Display.getWidth() / (float) Display.getHeight(),
				zNear, zFar);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();

		// To make sure the points closest to the camera are shown in front
		// of the points that are farther away.
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	/**
	 * Initializing lighting engine.
	 */
	private void initLighting() {
		initLightArrays();
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, matSpecular);
		GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 50.0f);

		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosition);
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, whiteLight);
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, whiteLight);
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, lModelAmbient);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LIGHT0);

		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glColorMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE);
	}

	/**
	 * Initialize the GUI using MainMenu.
	 */
	private void initGUI() {
		try {
			this.guiRenderer = new LWJGLRenderer();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		this.gui = new GUI(this.mainMenu, this.guiRenderer);

		try {
			this.themeManager = ThemeManager.createThemeManager(this.getClass()
					.getResource("menu.xml"), this.guiRenderer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.gui.applyTheme(themeManager);

	}

	/**
	 * Initialize various values for lighting.
	 */
	private void initLightArrays() {
		matSpecular = BufferUtils.createFloatBuffer(4);
		matSpecular.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();

		lightPosition = BufferUtils.createFloatBuffer(4);
		lightPosition.put(1.0f).put(1.0f).put(1.0f).put(0.0f).flip();

		whiteLight = BufferUtils.createFloatBuffer(4);
		whiteLight.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();

		lModelAmbient = BufferUtils.createFloatBuffer(4);
		lModelAmbient.put(0.5f).put(0.5f).put(0.5f).put(1.0f).flip();
	}

	/**
	 * @param zoomAmount
	 *            How many levels to adjust zoom by.
	 */
	public void zoom(int zoomAmount) {
		zoomGoal += zoomAmount * 20.0f;
	}

	/**
	 * @return Whether the Display is active.
	 */
	public boolean isRunning() {
		return Display.isCreated();
	}

	/**
	 * Draws a sphere of the given size.
	 * 
	 * @param x
	 *            x sphere location.
	 * @param y
	 *            y sphere location.
	 * @param z
	 *            z sphere location.
	 * @param radius
	 *            Radius of the sphere.
	 */
	private static void renderSphere(float x, float y, float z, float radius) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		Sphere s = new Sphere();
		s.draw(radius, 16, 16);
		GL11.glPopMatrix();
	}

	/**
	 * Scales the location and then uses the other renderSphere method.
	 * 
	 * @param position
	 *            Position of the sphere.
	 * @param radius
	 *            Radius of the sphere.
	 * @param scalar
	 *            Scales the position so that things aren't ridiculously spread
	 *            out.
	 */
	private static void renderSphere(Point3D position, float radius,
			float scalar) {
		Renderer.renderSphere(position.getX() * scalar, position.getY()
				* scalar, position.getZ() * scalar, radius);
	}

	/**
	 * @param color
	 *            Set the current rendering color.
	 */
	private static void setColor(Color color) {
		GL11.glColor3f(color.getRed() / (float) 255, color.getGreen()
				/ (float) 255, color.getBlue() / (float) 255);
	}

	/**
	 * @return The solar system.
	 */
	public SolarSystem getSolarSystem() {
		return solarSystem;
	}

	/**
	 * @param solarSystem
	 *            The solar system to set.
	 */
	public void setSolarSystem(SolarSystem solarSystem) {
		this.solarSystem = solarSystem;
	}

	/**
	 * @return The current main menu.
	 */
	public MainMenu getMainMenu() {
		return mainMenu;
	}

	/**
	 * @param mainMenu
	 *            The main menu to set.
	 */
	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

}
