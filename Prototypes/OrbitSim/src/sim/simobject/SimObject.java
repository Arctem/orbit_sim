/**
 * 
 */
package sim.simobject;

/**
 * @author russell
 *
 */
public interface SimObject {
	public void step();
	public void create();
	public void delete();
	public void changeProperty(String propName, int value);
	
}
