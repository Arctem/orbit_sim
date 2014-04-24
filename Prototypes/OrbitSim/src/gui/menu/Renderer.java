/**
 * 
 */
package gui.menu;

import gui.GUIObject;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import sim.SolarSystem;
import sim.simobject.Planet;
import sim.simobject.Sun;
import sim.util.Point3D;

/**
 * @author russell
 * 
 */
public class Renderer extends Canvas {

	public static final boolean LWJGL = true;

	public static final int MENU_SIZE = 300;
	private ArrayList<GUIObject> guiObjects;
	private JFrame window;

	public static void main(String args[]) {
		if (LWJGL) {
			try {
				Display.setDisplayMode(new DisplayMode(800, 600));
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
			int fov = 68;

			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GLU.gluPerspective(fov, (float) Display.getWidth()
					/ (float) Display.getHeight(), zNear, zFar);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();

			// To make sure the points closest to the camera are shown in front
			// of the points that are farther away.
			GL11.glEnable(GL11.GL_DEPTH_TEST);

			Sun sun = new Sun(0, 0, new Point3D(0, 0, 0), Color.YELLOW, 100);
			Planet planet = new Planet(0, SolarSystem.ASTRONOMICAL_UNIT,
					Color.GREEN, sun, 10);

			while (!Display.isCloseRequested()) {
				// Render stuff.
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT
						| GL11.GL_DEPTH_BUFFER_BIT);

				GL11.glTranslatef(0, 0, 0.05f);

				/*
				 * GL11.glBegin(GL11.GL_POINTS); for (Point p : points) { //
				 * Draw the point at its coordinates GL11.glVertex3f(p.x, p.y,
				 * p.z); } GL11.glEnd();
				 */

				GL11.glBegin(GL11.GL_LINE_LOOP);
				GL11.glVertex3f(-0.5f, -0.5f, -10f);
				GL11.glVertex3f(-0.5f, 0.5f, -10f);
				GL11.glVertex3f(0.5f, 0.5f, -10f);
				GL11.glVertex3f(0.5f, -0.5f, -10f);
				GL11.glEnd();

				Renderer.renderSphere(-0.5f, 0.0f, -5f, 0.4f);
				Renderer.renderSphere(0.5f, 0.0f, -5f, 0.4f);

				GL11.glBegin(GL11.GL_TRIANGLES);
				GL11.glVertex3f(-0.5f, -0.5f, -6f);
				GL11.glVertex3f(-0.5f, 0.5f, -5f);
				GL11.glVertex3f(0.5f, 0.5f, -4f);
				GL11.glEnd();

				Display.update();
				Display.sync(60);
			}

			Display.destroy();
		} else {

			Renderer rend = new Renderer();
			while (rend.getWindow().isEnabled()) {
				// rend.render();
				rend.repaint();

				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void renderSphere(float x, float y, float z, float radius) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		Sphere s = new Sphere();
		s.draw(radius, 16, 16);
		GL11.glPopMatrix();
	}

	/**
	 * 
	 */
	public Renderer() {
		this.window = new JFrame("Orbit Simulator");
		this.setSize(800, 600);
		this.window.add(this);
		this.window.setFocusable(true);
		this.window.setResizable(true);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.pack();
		this.window.setVisible(true);
	}

	public void paint(Graphics screen) {
		BufferedImage buffer = new BufferedImage(this.getSize().width,
				this.getSize().height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = buffer.createGraphics();

		BufferedImage menuBuffer = new BufferedImage(MENU_SIZE,
				this.getSize().height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D gMenu = menuBuffer.createGraphics();

		BufferedImage mainScreenBuffer = new BufferedImage(
				this.getSize().width, this.getSize().height,
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D gMain = mainScreenBuffer.createGraphics();

		gMain.setColor(Color.green);
		gMain.fillRect(200, 200, 500, 500);

		gMenu.setColor(new Color(0, 0, (int) Math.abs((System
				.currentTimeMillis() / 10) % 255), 150));
		gMenu.fillRect(0, 0, MENU_SIZE, this.getSize().height);

		g.drawImage(mainScreenBuffer, 0, 0, null);
		g.drawImage(menuBuffer, this.getSize().width - MENU_SIZE, 0, null);

		// g.drawRect(this.getSize().width / 4, this.getSize().height / 4,
		// this.getSize().width / 2, this.getSize().height / 2);

		screen.drawImage(buffer, 0, 0, null);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void render() {

	}

	public void initialize() {

	}

	public void stop() {

	}

	/**
	 * @return the window
	 */
	public JFrame getWindow() {
		return window;
	}

}
