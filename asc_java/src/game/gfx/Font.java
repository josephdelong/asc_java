/**
 * 
 */
package game.gfx;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=XSBTWhV75hM
 *
 */
public class Font {

	private static String chars = "" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + "0123456789.,:;'\"!?$%()-=+/_     "; // include every character (AS IS) from the SPRITE SHEET, including trailing spaces
	private static String charsSmall = "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + "0123456789.,:;'\"!?$%()-=+/_     ";
	
	public static void render(String message, Screen screen, String fontSize, int x, int y, int color, int scale) {
		message = message.toUpperCase(); // since our current FONT doesn't have any lower-case letters
		
		for (int i = 0; i < message.length(); i++) {
			if(fontSize != null && fontSize.equalsIgnoreCase("small")) {
				int charIndex = charsSmall.indexOf(message.charAt(i));
				if(charIndex >= 0) { // as long as we have a valid character, RENDER it
					screen.render(Screen.TILE_SIZE_FONT_SMALL, x + (i * 8), y, charIndex + 28 * 32, color, 0x00, scale); // small font is on line 29 + 30 (indexes 28 + 29)
				}
			} else if(fontSize != null && fontSize.equalsIgnoreCase("medium")) {
				int charIndex = chars.indexOf(message.charAt(i));
				if(charIndex >= 0) { // as long as we have a valid character, RENDER it
					screen.render(Screen.TILE_SIZE_FONT_MEDIUM, x + (i * 8), y, charIndex + 30 * 32, color, 0x00, scale); // regular font is on line 31 + 32 (indexes 30 & 31)
				}
			}
		}
	}
	
}
