/**
 * 
 */
package visual.gfx;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben, taken from https://www.youtube.com/watch?v=6FMgQNDNMJc
 *
 */
public class Screen {

	public static final int MAP_WIDTH = 64;
	public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;
	
	private static final byte BIT_MIRROR_X = 0x01;
	private static final byte BIT_MIRROR_Y = 0x02;
	
	public int[] pixels; // pixel data

	public int xOffset = 0;
	public int yOffset = 0;
	
	public int width;
	public int height;
	
	public SpriteSheet sheet;
	
	public Screen(int width, int height, SpriteSheet sheet) {
		this.width = width;
		this.height = height;
		this.sheet = sheet;
		
		pixels = new int[width * height]; // new pixel data
	}
	
	/**
	 * Newer render method from https://www.youtube.com/watch?v=7eotyB7oNHE
	 *   Sprite flipping added from https://www.youtube.com/watch?v=gWSx7S8YiPQ
	 * @param xPos
	 * @param yPos
	 * @param tile Tile index on the pixel sheet
	 * @param color
	 * @param mirrorDir
	 */
	public void render(int xPos, int yPos, int tile, int color, int mirrorDir, int scale) {
		xPos -= xOffset; // keep in bounds
		yPos -= yOffset; // keep in bounds
		
		boolean mirrorX = (mirrorDir & BIT_MIRROR_X) > 0; // logical AND
		boolean mirrorY = (mirrorDir & BIT_MIRROR_Y) > 0;
		
		int scaleMap = scale - 1;
		
		int xTile = tile % 32; // get x coordinate in pixel sheet
		int yTile = tile / 32; // get y coordinate in pixel sheet
		int tileOffset = (xTile << 3) + (yTile << 3) * sheet.width; // get offset
		
		for (int y = 0; y < 8; y++) { // each tile is 8 pixels high
			int ySheet = y;
			if(mirrorY) {
				ySheet = 7 - y; // invert Y drawing
			}
			
			int yPixel = y + yPos + (y * scaleMap) - ((scaleMap << 3) / 2); // << 3 because tile size is 8x8
			
			for (int x = 0; x < 8; x++) { // each tile is 8 pixels wide
				int xSheet = x;
				if(mirrorX) {
					xSheet = 7 - x; // invert X drawing
				}
				
				int xPixel = x + xPos + (x * scaleMap) - ((scaleMap << 3) / 2);
				
				int col = (color >> (sheet.pixels[xSheet + ySheet * sheet.width + tileOffset] * 8)) & 255; // give a 1, 2, 3, or 4, multiplied by 255
				if(col < 255) { // if col is NOT transparent.. this is ignored currently
					for(int yScale = 0; yScale < scale; yScale++) {
						if(yPixel + yScale < 0 || yPixel + yScale >= height) { // if out of bounds for yPos
							continue; // skip
						}
						for (int xScale = 0; xScale < scale; xScale++) {
							if(xPixel + xScale < 0 || xPixel + xScale >= width) { // if out of bounds for xPos
								continue; // skip
							}
							pixels[(xPixel + xScale) + (yPixel + yScale) * width] = col;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Sets this Screen's xOffset & yOffset to the given integer values
	 * @param xOffset
	 * @param yOffset
	 */
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
}
