package sim.simobject;

import java.awt.Color;

import sim.SolarSystem;
import sim.util.Point3D;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Planet planet = new Planet(60000000000000000000000000.0, SolarSystem.ASTRONOMICAL_UNIT, 10, new Color(0), 
				new Sun(2000000000000000000000000000000.0, 10, new Point3D(0, 0, 0), new Color(0), 10), 1000);
		
		System.out.println(planet.getAngularVelocity());
		System.out.println(planet.getPeriod());
		System.out.println("");
		for(int i = 0; i < 100; i++){
			System.out.println(planet.getPosition().getX() + ", " + planet.getPosition().getY());
			planet.step(60);
		}
	}

}
