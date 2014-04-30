/**
 * 
 */
package sim.simobject;

import de.matthiasmann.twl.Widget;

/**
 * @author russell
 * 
 */
public interface SimObject {
	public void step(long timeScale);

	/**
	 * @return
	 */
	public Widget getDetailedMenu();

	public boolean isMenuDirty();

	public void setMenuDirty(boolean menuDirty);

}
