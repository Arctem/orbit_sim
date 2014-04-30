/**
 * 
 */
package gui;

import de.matthiasmann.twl.Button;

/**
 * @author russell
 * 
 */
public interface GUIObject {
	/**
	 * Generate a button to select this object.
	 * 
	 * @return
	 */
	public Button createButton();
}
