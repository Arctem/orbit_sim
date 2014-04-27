package sim.simobject;

import java.awt.Color;
import java.util.ArrayList;

import sim.SolarSystem;
import sim.util.Point3D;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Planet planet = new Planet(60000000000000000000000000.0, SolarSystem.ASTRONOMICAL_UNIT, 10, new Color(0), 
				new Sun(2000000000000000000000000000000.0, 10, new Point3D(0, 0, 0), new Color(0), 10), 1000);
		Planet planet2 = new Planet(641850000000000000000000.0, (long)(SolarSystem.ASTRONOMICAL_UNIT * 1.665), 10, new Color(0), 
				new Sun(2000000000000000000000000000000.0, 10, new Point3D(0, 0, 0), new Color(0), 10), 1000);
		Planet planet3 = new Planet(641850000000000000000000.0, 10, new Color(0), 
				new Sun(2000000000000000000000000000000.0, 10, new Point3D(0, 0, 0), new Color(0), 10), 1500000, 10000);
		
		SolarSystem ss = new SolarSystem(new ArrayList<SimObject>(), null);
		
		ss.addSimObject(planet);
		ss.addSimObject(planet2);
		ss.addSimObject(planet3);
		
		System.out.println(planet.getAngularVelocity());
		System.out.println(planet.getPeriod());
		System.out.println(planet.getVelocity());
		System.out.println("");
		System.out.println(planet2.getAngularVelocity());
		System.out.println(planet2.getPeriod());
		System.out.println(planet2.getVelocity());
		System.out.println("");
		System.out.println(planet3.getAngularVelocity());
		System.out.println(planet3.getPeriod());
		System.out.println(planet3.getVelocity());
		System.out.println(planet3.getOrbitRadius());
		System.out.println("");
		for(int i = 0; i < 100; i++){
			System.out.println(planet.getPosition().getX() + ", " + planet.getPosition().getY());
			System.out.println(planet2.getPosition().getX() + ", " + planet2.getPosition().getY());
			System.out.println(planet3.getPosition().getX() + ", " + planet3.getPosition().getY() + "\n");
			ss.step(1000);
		}
	}

}
