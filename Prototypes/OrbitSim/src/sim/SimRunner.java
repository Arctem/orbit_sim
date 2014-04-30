/**
 * 
 */
package sim;

import gui.menu.MainMenu;
import gui.menu.Renderer;

import java.awt.Color;

import sim.simobject.Planet;
import sim.simobject.Star;
import sim.simobject.Sun;
import sim.util.Point3D;

/**
 * @author Russell
 * 
 */
public class SimRunner {

	final static int TARGET_FPS = 240;
	final static long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
		SolarSystem system = new SolarSystem(menu, 2500);
		Renderer renderer = new Renderer(menu, system);
		menu.setSolarSystem(system);

		Sun sun = new Sun(2000000000000000000000000000000.0, 10, new Point3D(0,
				0, 0), new Color(255, 120, 50), 10);
		system.addSimObject(sun);
		Planet test = new Planet(60000000000000000000000000.0,
				SolarSystem.ASTRONOMICAL_UNIT, 10, new Color(124, 255, 12),
				sun, 2000, SolarSystem.ASTRONOMICAL_UNIT/3);
		system.addSimObject(test);
		system.addSimObject(new Planet(60000000000000000000000000.0,
				SolarSystem.ASTRONOMICAL_UNIT * 3 / 2, 10, new Color(124, 255,
						12), sun, 2000, 0));
		system.addSimObject(new Planet(6000000000000000000.0,
				SolarSystem.ASTRONOMICAL_UNIT / 20, 10,
				new Color(124, 255, 12), test, 1000, 0));
		

		for (int i = 0; i < 5000; i++) {
			long x = (long) (Math.random() * 400000 - 200000);
			long y = (long) (Math.random() * 400000 - 200000);
			long z = (long) (Math.random() * 40000 - 20000);
			int brightness = (int) (Math.random() * 255);

			system.addSimObject(new Star(new Point3D(x, y, z), brightness));
		}

		Thread renderThread = new Thread(renderer);
		renderThread.start();

		// Make sure window is prepared.
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		synchronized (system) {
			//Make sure Display is ready.
		}

		long lastLoopTime = System.nanoTime();
		long lastFpsTime = System.nanoTime();
		float fps = 0;

		// Loop code comes mostly from
		// http://www.java-gaming.org/index.php?topic=24220.0
		while (renderer.isRunning()) {
			// work out how long its been since the last update, this
			// will be used to calculate how far the entities should
			// move this loop
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) OPTIMAL_TIME);

			// update the frame counter
			lastFpsTime += updateLength;
			fps++;

			// update our FPS counter if a second has passed since
			// we last recorded
			if (lastFpsTime >= 1000000000) {
				System.out.println("(FPS: " + fps + ")");
				lastFpsTime = 0;
				fps = 0;
			}

			// update the game logic
			// system.step(delta);

			system.step();

			// testing printing out statistics for time elapsed
			// System.out.println("\n" + system.getDaysElapsed() + ", " +
			// system.getMonthsElapsed() +
			// ", " + system.getYearsElapsed() + "\n");

			// we want each frame to take 10 milliseconds, to do this
			// we've recorded when we started the frame. We add 10 milliseconds
			// to this and then factor in the current time to give
			// us our final value to wait for
			// remember this is in ms, whereas our lastLoopTime etc. vars are in
			// ns.
			try {
				Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
