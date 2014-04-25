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
import sim.simobject.Sun;
import sim.util.Point3D;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.examples.LwjglInitHelper;
import de.lessvoid.nifty.tools.TimeProvider;

/**
 * @author russell
 * 
 */
public class Renderer {
	private static final float RENDER_SCALE = 0.000005f;
	private static final float SUN_SIZE = 30f;
	private static final float PLANET_SIZE = 20f;

	public static final int MENU_SIZE = 300;
	private ArrayList<GUIObject> guiObjects;

	private static float zoomLevel, zoomGoal;
	private static float xRotation;
	private static float rotationRate = 0.2f;

	// ----------- Variables added for Lighting Test -----------//
	private static FloatBuffer matSpecular, lightPosition, whiteLight,
			lModelAmbient;

	public static void main(String args[]) {
		Renderer.initialize();
		Renderer.startMainLoop();
	}

	public static void startMainLoop() {
		// Nifty nifty = new Nifty(new LwjglRenderDevice(), null,
		//			LwjglInitHelper.getInputSystem(), new TimeProvider());

		Sun sun = new Sun(0, 0, new Point3D(0, 0, 0), Color.YELLOW, 100);
		Planet planet = new Planet(0, SolarSystem.ASTRONOMICAL_UNIT, 5,
				Color.GREEN, sun);

		while (!Display.isCloseRequested()) {
			// Render stuff.
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

			zoomGoal += Mouse.getDWheel() * 0.1f;
			zoomLevel = (zoomGoal + zoomLevel * 2) / 3;

			if (Mouse.isButtonDown(1))
				xRotation += Mouse.getDX() * 0.1f;

			GL11.glLoadIdentity();
			GL11.glTranslatef(0, 0, zoomLevel);
			GL11.glRotatef(20f, 1f, 0, 0);
			GL11.glRotatef(xRotation, 0f, 1f, 0f);

			/*
			 * GL11.glBegin(GL11.GL_POINTS); for (Point p : points) { // Draw
			 * the point at its coordinates GL11.glVertex3f(p.x, p.y, p.z); }
			 * GL11.glEnd();
			 */

			Renderer.setColor(sun.getColor());
			Renderer.renderSphere(sun.getPosition(), SUN_SIZE, RENDER_SCALE);
			Renderer.setColor(planet.getColor());
			Renderer.renderSphere(planet.getPosition(), PLANET_SIZE,
					RENDER_SCALE);

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}

	public static void initialize() {
		initGL();
		initLighting();

		zoomLevel = -200f;
		zoomGoal = -200f;
		xRotation = 0.0f;
	}

	private static void initGL() {
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
		float zFar = 20000f;
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

	private static void initLighting() {
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
	private static void initLightArrays() {
		matSpecular = BufferUtils.createFloatBuffer(4);
		matSpecular.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();

		lightPosition = BufferUtils.createFloatBuffer(4);
		lightPosition.put(1.0f).put(1.0f).put(1.0f).put(0.0f).flip();

		whiteLight = BufferUtils.createFloatBuffer(4);
		whiteLight.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();

		lModelAmbient = BufferUtils.createFloatBuffer(4);
		lModelAmbient.put(0.5f).put(0.5f).put(0.5f).put(1.0f).flip();
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

	public static void render() {

	}

	public static void stop() {

	}

}
