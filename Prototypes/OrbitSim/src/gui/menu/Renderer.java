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

import javax.swing.JFrame;

/**
 * @author russell
 * 
 */
public class Renderer extends Canvas {

	public static final int MENU_SIZE = 300;
	private ArrayList<GUIObject> guiObjects;
	private JFrame window;

	public static void main(String args[]) {
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

		
		gMenu.setColor(new Color(0, 0,
				(int) Math.abs((System.currentTimeMillis() / 10) % 255), 150));
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
