/**
 * 
 */
package visual.entities;

import javax.swing.JComponent;

import visual.gfx.Screen;
import visual.level.Level;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=dQP7mFhqgg
 *
 */
public abstract class Entity extends JComponent {

	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	public int x, y;
	protected Level level;
	
	public Entity(Level level) {
		init(level);
	}
	
	public final void init(Level level) { // this will run ONLY once!
		this.level = level;
	}
	
	public abstract void tick();
	
	public abstract void render(Screen screen);
	
}
