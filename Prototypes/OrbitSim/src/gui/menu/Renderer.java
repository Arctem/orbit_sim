/**
 * 
 */
package gui.menu;

import gui.GUIObject;

import java.awt.Color;
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
import sim.simobject.Planet;
import sim.simobject.SimObject;
import sim.simobject.Star;
import sim.simobject.Sun;
import sim.util.Point3D;

/**
 * @author russell
 * 
 */
public class Renderer implements Runnable {
	private static final float RENDER_SCALE = 0.000005f;
	private static final float SUN_SIZE = 50f;
	private static final float PLANET_SIZE_MAX = 25f;
	private static final float PLANET_SIZE_MIN = 10f;

	public static final int MENU_SIZE = 300;
	private ArrayList<GUIObject> guiObjects;

	private float zoomLevel, zoomGoal;
	private float xRotation;
	private float rotationRate = 0.2f;

	private SolarSystem solarSystem;
	private MainMenu mainMenu;

	// ----------- Variables added for Lighting Test -----------//
	private static FloatBuffer matSpecular, lightPosition, whiteLight,
			lModelAmbient;

	public Renderer(MainMenu mainMenu, SolarSystem solarSystem) {
		this.mainMenu = mainMenu;
		this.solarSystem = solarSystem;

		this.zoomLevel = -100000f;
		this.zoomGoal = -1000f;
		this.xRotation = 0.0f;
	}

	public void run() {
		synchronized (this.solarSystem) {
			this.initGL();
			this.initLighting();
		}

		while (!Display.isCloseRequested()) {
			this.render();
			Display.sync(60);
		}
		System.out.println("Display closing.");

		Display.destroy();
	}

	public void render() {
		// Render stuff.
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		zoomGoal += Mouse.getDWheel() * 0.1f;
		zoomLevel = (zoomGoal + zoomLevel * 2) / 3;

		if (Mouse.isButtonDown(1))
			xRotation += Mouse.getDX() * 0.2f;

		GL11.glLoadIdentity();
		GL11.glTranslatef(0, 0, zoomLevel);
		GL11.glRotatef(110f, 1f, 0, 0);
		GL11.glRotatef(xRotation, 0f, 0f, 1f);

		/*
		 * GL11.glBegin(GL11.GL_POINTS); for (Point p : points) { // Draw the
		 * point at its coordinates GL11.glVertex3f(p.x, p.y, p.z); }
		 * GL11.glEnd();
		 */

		for (SimObject o : solarSystem.getSimObjects()) {
			if (o instanceof Sun) {
				Sun s = (Sun) o;
				Renderer.setColor(s.getColor());
				Renderer.renderSphere(s.getPosition(), SUN_SIZE, RENDER_SCALE);
			} else if (o instanceof Planet) {
				Planet p = (Planet) o;
				Renderer.setColor(p.getColor());
				if (p.getSun() instanceof Sun)
					Renderer.renderSphere(p.getPosition(), PLANET_SIZE_MAX,
							RENDER_SCALE);
				else
					Renderer.renderSphere(p.getPosition(), PLANET_SIZE_MIN,
							RENDER_SCALE);
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
		// Renderer.setColor(sun.getColor());
		// Renderer.renderSphere(sun.getPosition(), SUN_SIZE, RENDER_SCALE);
		// Renderer.setColor(planet.getColor());
		// Renderer.renderSphere(planet.getPosition(), PLANET_SIZE,
		// RENDER_SCALE);

		Display.update();
	}

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

	// ------- Added for Lighting Test----------//
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

	public boolean isRunning() {
		return Display.isCreated();
	}

	private static void renderSphere(float x, float y, float z, float radius) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		Sphere s = new Sphere();
		s.draw(radius, 16, 16);
		GL11.glPopMatrix();
	}

	private static void renderSphere(Point3D position, float radius,
			float scalar) {
		Renderer.renderSphere(position.getX() * scalar, position.getY()
				* scalar, position.getZ() * scalar, radius);
	}

	private static void setColor(Color color) {
		GL11.glColor3f(color.getRed() / (float) 255, color.getGreen()
				/ (float) 255, color.getBlue() / (float) 255);
	}

	public void stop() {

	}

	public SolarSystem getSolarSystem() {
		return solarSystem;
	}

	public void setSolarSystem(SolarSystem solarSystem) {
		this.solarSystem = solarSystem;
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

}
