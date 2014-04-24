/**
 * 
 */
package sim.util;

/**
 * @author russell
 * 
 */
public class Point3D {
	private long x, y, z;

	public Point3D(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void add(long x, long y, long z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}

	public void add(Point3D point) {
		this.x += point.x;
		this.y += point.y;
		this.z += point.z;
	}

	public void subtract(Point3D point) {
		this.x -= point.x;
		this.y -= point.y;
		this.z -= point.z;
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

	public static Point3D add(Point3D p1, Point3D p2) {
		return new Point3D(p1.x + p2.x, p1.y + p2.y, p1.z + p2.z);
	}

	public static double distance(Point3D p1, Point3D p2) {
		return Math.sqrt(Math.pow((p1.x - p2.x), 2)
				+ Math.pow((p1.y - p2.y), 2) + Math.pow((p1.z - p2.z), 2));
	}

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

	public void setXYZ(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
