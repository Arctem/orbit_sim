/**
 * 
 */
package gui.menu;

import gui.GUIObject;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * @author russell
 * 
 */
public class Renderer extends Canvas {

	private Graphics2D g;
	private ArrayList<GUIObject> guiObjects;
	private JFrame window;

	public static void main(String args[]) {
		Renderer rend = new Renderer();
		while (rend.getWindow().isActive()) {
			// rend.render();
			rend.repaint();

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
		screen.drawRect(this.getSize().width / 4, this.getSize().height / 4,
				this.getSize().width / 2, this.getSize().height / 2);
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
