/**
 * 
 */
package visual.gfx;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben, taken from https://www.youtube.com/watch?v=7eotyB7oNHE
 *
 */
public class Colors {

	/**
	 * Returns an integer representation of the 4 possible colors (color 1-4)
	 * @param color1
	 * @param color2
	 * @param color3
	 * @param color4
	 * @return
	 */
	public static int get(int color1, int color2, int color3, int color4) {
		return (get(color4) << 24) | (get(color3) << 16) | (get(color2) << 8) | get(color1);
	}
	
	/**
	 * Parse color
	 * @param color
	 * @return
	 */
	public static int get(int color) {
		if(0 > color) {
			return 255; // negative numbers will also be transparent!
		}
		int r = color / 100 % 10; // divide by 100 to get 1st numeral; mod(10) to make sure it's in bounds
		int g = color / 10 % 10; // divide by 10 to get 2nd numeral; mod(10) to make sure it's in bounds
		int b = color % 10; // no division to get 3rd numeral; mod(10) to make sure it's in bounds
		return (r * 36) + (g * 6) + (b); // since we have 6 *shades* of RGB colors, each channel
	}
	
}
