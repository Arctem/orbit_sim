/**
 * 
 */
package sim.util;

/**
 * @author russell
 * 
 */
public class Point3D {
	private int x, y, z;

	public Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void add(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}

	public void add(Point3D point) {
		this.x += point.x;
		this.y += point.y;
		this.z += point.z;
	}
	
	public void subtract(Point3D point){
		this.x -= point.x;
		this.y -= point.y;
		this.z -= point.z;
	}
	
	/**
	 * multiply by another point. x * x, y * y, z * z.
	 * @param point the point to multiply by
	 */
	public void multiply(Point3D point){
		this.x *= point.x;
		this.y *= point.y;
		this.z *= point.z;
	}
	
	/**
	 * multiply by a constant
	 * 
	 * @param c the constant to multiply by.
	 */
	public void multiply(int c){
		this.x *= c;
		this.y *= c;
		this.z *= c;
	}

	public static Point3D add(Point3D p1, Point3D p2) {
		return new Point3D(p1.x + p2.x, p1.y + p2.y, p1.z + p2.z);
	}
	
	public static double distance(Point3D p1, Point3D p2){
		return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2) + Math.pow((p1.z - p2.z), 2));
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}
	
	public void setXYZ(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
