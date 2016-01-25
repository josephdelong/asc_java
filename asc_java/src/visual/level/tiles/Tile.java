/**
 * 
 */
package visual.level.tiles;

import visual.gfx.Colors;
import visual.gfx.Screen;
import visual.level.Level;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=aDZemglb2WI
 *
 */
public abstract class Tile {
	
	public static final Tile[] tiles = new Tile[256]; // 32 x 32 Tiles on current board
	public static final Tile VOID = new BaseSolidTile(0, 0, 0, Colors.get(000,  -1, -1, -1), 0xFF000000);
	public static final Tile STONE = new BaseSolidTile(1, 1, 0, Colors.get(-1, 333, 444, -1), 0xFF555555);
	public static final Tile GRASS = new BaseTile(2, 2, 0, Colors.get(-1, 131, 141, -1), 0xFF00FF00);
	public static final Tile SNOW = new BaseTile(3, 3, 0, Colors.get(-1, -1, 544, 555), 0xFFFFFFFF);
	public static final Tile ICE = new BaseTile(4, 4, 0, Colors.get(-1, -1, 544, 555), 0xFFFFF0F0);
	public static final Tile DIRT = new BaseTile(5, 5, 0, Colors.get(-1, 123, 124, -1), 0xFFC09060);
	public static final Tile MUD = new BaseTile(6, 6, 0, Colors.get(-1, 012, 123, -1), 0xFF403020);
	public static final Tile SAND = new BaseTile(7, 7, 0, Colors.get(-1, -1, 244, 355), 0xFFF0E080);
	public static final Tile WATER = new AnimatedTile(8, new int[][] {{0,5},{1,5},{2,5},{3,5}}, Colors.get(-1, 533, 544, -1), 0xFF0000FF, 500);
	
	public byte id;
	protected boolean solid; // for collision detection
	protected boolean emitter; // for light emission
	
	private int levelColor;
	
	public Tile(int id, boolean isSolid, boolean isEmitter, int levelColor) {
		this.id = (byte) id;
		if(tiles[id] != null) { // if tile at this index already exists
			throw new RuntimeException("Duplicate Tile ID on " + id);
		}
		this.solid = isSolid;
		this.emitter = isEmitter;
		tiles[id] = this;
		this.levelColor = levelColor;
	}
	
	public byte getId() {
		return id;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public boolean isEmitter() {
		return emitter;
	}
	
	public abstract void tick();
	
	public abstract void render(Screen screen, Level level, int x, int y);
	
	public int getLevelColor() {
		return levelColor;
	}

}
