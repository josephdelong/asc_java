/**
 * 
 */
package visual.gfx;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=XSBTWhV75hM
 *
 */
public class Font {

	private static String chars = "" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + "0123456789.,:;'\"!?$%()-=+/      "; // include every character (AS IS) from the SPRITE SHEET, including trailing spaces
	
	public static void render(String message, Screen screen, int x, int y, int color, int scale) {
		message = message.toUpperCase(); // since our current FONT doesn't have any lower-case letters
		
		for (int i = 0; i < message.length(); i++) {
			int charIndex = chars.indexOf(message.charAt(i));
			if(charIndex >= 0) { // as long as we have a valid character, RENDER it
				screen.render(x + (i * 8), y, charIndex + 30 * 32, color, 0x00, scale);
			}
		}
	}
	
}
