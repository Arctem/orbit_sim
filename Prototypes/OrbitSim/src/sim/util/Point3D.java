/**
 * 
 */
package sim.util;

/**
 * @author russell, AJ
 * 
 * This stores the location in a 3D Cartesian graph
 */
public class Point3D {
	private long x, y, z;

	/**
	 * @param x, x coordinate
	 * @param y, y coordinate
	 * @param z, z coordinate
	 */
	public Point3D(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * add the given values to the coordinates
	 * @param x what to add to the x coordinate
	 * @param y what to add to the y coordinate
	 * @param z what to add to the z coordinate
	 */
	public void add(long x, long y, long z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}

	/**
	 * add the given values to the coordinates
	 * @param point contains the value to add to each respective coordinate
	 */
	public void add(Point3D point) {
		this.x += point.x;
		this.y += point.y;
		this.z += point.z;
	}

	/**
	 * multiply by another point. x * x, y * y, z * z.
	 * 
	 * @param point
	 *            the point to multiply by
	 */
	public void multiply(Point3D point) {
		this.x *= point.x;
		this.y *= point.y;
		this.z *= point.z;
	}

	/**
	 * multiply by a constant
	 * 
	 * @param c
	 *            the constant to multiply by.
	 */
	public void multiply(long c) {
		this.x *= c;
		this.y *= c;
		this.z *= c;
	}

	/**
	 * static version of the add functions
	 * @param p1 the first point to add
	 * @param p2 the second point to add
	 * @return the sum of the 2 points
	 */
	public static Point3D add(Point3D p1, Point3D p2) {
		return new Point3D(p1.x + p2.x, p1.y + p2.y, p1.z + p2.z);
	}

	/**
	 * find the Cartesian distance between the 2 points
	 * @param p1 the first point
	 * @param p2 the second point
	 * @return the distance between p1 and p2
	 */
	public static double distance(Point3D p1, Point3D p2) {
		return Math.sqrt(Math.pow((p1.x - p2.x), 2)
				+ Math.pow((p1.y - p2.y), 2) + Math.pow((p1.z - p2.z), 2));
	}

	
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}

	/**
	 * @return the x
	 */
	public long getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public long getY() {
		return y;
	}

	/**
	 * @return the z
	 */
	public long getZ() {
		return z;
	}

	/**
	 * set x, y, z to the given values
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setXYZ(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
