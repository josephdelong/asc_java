/**
 * 
 */
package game.level.tiles;

import game.gfx.Screen;
import game.level.Level;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=NtXDuJHLxRs
 *
 */
public class BaseSolidTile extends BaseTile {

	public BaseSolidTile(int id, int x, int y, int tileColor, int levelColor) {
		super(id, x, y, tileColor, levelColor);
		this.solid = true;
	}

	public void render(Screen screen, Level level, int x, int y) {
		screen.render(Screen.TILE_SIZE_TERRAIN, x, y, tileId, tileColor, 0x00, 1);
	}

}
