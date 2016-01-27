/**
 * 
 */
package visual.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import visual.entities.Entity;
import visual.entities.PlayerMP;
import visual.gfx.Screen;
import visual.level.tiles.Tile;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=aDZemglb2WI
 *
 */
public class Level {

	private byte[] tiles; // array of IDs for what tile resides in which location
	public int width;
	public int height;
	public List<Entity> entities = new ArrayList<Entity>();
	private String imagePath; // only needed here if we want to save changes to level 
	private BufferedImage image;
	
	public Level(String imagePath) {
		if(imagePath != null) {
			this.imagePath = imagePath;
			this.loadLevelFromFile();
		} else {
			this.width = 64;
			this.height = 64;
			tiles = new byte[height * width];
			this.generateLevel();
		}
	}
	
	private void loadLevelFromFile() {
		try{
			this.image = ImageIO.read(Level.class.getResource(this.imagePath));
			this.width = image.getWidth();
			this.height = image.getHeight();
			tiles =  new byte[width * height];
			this.loadTiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Parses out an Image, pixel by pixel & creates new Tiles based on those parsed values
	 */
	private void loadTiles() {
		int[] tileColors = this.image.getRGB(0, 0, width, height, null, 0, width); // translate image data from BufferedImage into an int
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tileCheck: for(Tile t: Tile.tiles) {
					if(t != null && t.getLevelColor() == tileColors[x + y * width]) {
						this.tiles[x + y * width] = t.getId();
						break tileCheck;
					}
				}
			}
		}
	}
	
	/**
	 * Takes image and inserts it into the new File (as a PNG)
	 */
	@SuppressWarnings("unused")
	private void saveLevelToFile() {
		try {
			ImageIO.write(image, "png", new File(Level.class.getResource(this.imagePath).getFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Changes the Tile at coordinates (x, y) to the new Tile specified
	 * @param xPos
	 * @param yPos
	 * @param newTile
	 */
	public void alterTile(int x, int y, Tile newTile) {
		this.tiles[x + y * width] = newTile.getId();
		image.setRGB(x, y, newTile.getLevelColor());
	}
	
	public void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(x * y % 10 < 7) {
					tiles[x + y * width] = Tile.GRASS.getId();
				} else {
					tiles[x + y * width] = Tile.STONE.getId();
				}
			}
		}
	}
	
	public void tick() {
		for(Entity e: entities) {
			e.tick();
		}
		
		//Hack-y way of updating all tiles all at once (for animated tiles, etc.)
		for(Tile t: Tile.tiles) {
			if(t == null) {
				break;
			}
			t.tick();
		}
	}
	
	public void renderTiles(Screen screen, int xOffset, int yOffset) {
		if(xOffset < 0) {
			xOffset = 0;
		}
		if(xOffset > ((width << 3) - screen.width)) {
			xOffset = ((width << 3) - screen.width);
		}
		if(yOffset < 0) {
			yOffset = 0;
		}
		if(yOffset > ((height << 3) - screen.height)) {
			yOffset = ((height << 3) - screen.height);
		}
		
		screen.setOffset(xOffset, yOffset); // center the "camera" on the screen
		
		// render ONLY those tiles which could possibly be seen
		for (int y = (yOffset >> 3); y < (yOffset + screen.height >> 3) + 1; y++) {
			for (int x = (xOffset >> 3); x < (xOffset + screen.width) + 1; x++) {
				getTile(x, y).render(screen, this, x << 3, y << 3);
			}
		}
	}
	
	public void renderEntities(Screen screen) {
		for(Entity e: entities) {
			e.render(screen);
		}
	}
	
	public Tile getTile(int x, int y) {
		if(0 > x || x >= width || 0 > y || y >= height) {
			return Tile.VOID;
		}
		return Tile.tiles[tiles[x + y * width]];
	}
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	
	public void removePlayerMP(String userName) {
		int index = 0;
		for(Entity e: entities) {
			if(e instanceof PlayerMP && ((PlayerMP)e).getUserName().equalsIgnoreCase(userName)) {
				this.entities.remove(index);
				break;
			}
			index++;
		}
		//this.entities.remove(index); // I moved this into the if() just because
	}
	
}
