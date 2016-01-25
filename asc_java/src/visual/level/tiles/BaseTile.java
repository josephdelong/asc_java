/**
 * 
 */
package visual.level.tiles;

import visual.gfx.Screen;
import visual.level.Level;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=aDZemglb2WI
 *
 */
public class BaseTile extends Tile {

	protected int tileId;
	protected int tileColor;
	
	public BaseTile(int id, int x, int y, int tileColor, int levelColor) { // this is the base Tile, therefore it is NOT solid NOR an emitter
		super(id, false, false, levelColor);
		this.tileId = x + y * 32;
		this.tileColor = tileColor;
	}
	
	public void tick() {
		// do nothing
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		screen.render(x, y, tileId, tileColor, 0x00, 1);
	}
	
}
