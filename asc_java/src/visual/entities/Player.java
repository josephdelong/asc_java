/**
 * 
 */
package visual.entities;

import visual.InputHandler;
import visual.gfx.Colors;
import visual.gfx.Font;
import visual.gfx.Screen;
import visual.level.Level;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=dQP7mFhqgg
 *   
 *   Walking Animation tutorial taken from https://www.youtube.com/watch?v=dnvNcjVNqqs
 *   TODO: Fix up animation / rendering for single-width sprite
 *   
 *   Collision Box tutorial taken from https://www.youtube.com/watch?v=NtXDuJHLxRs
 *   
 *   Animated Tiles & Swimming tutorial taken from https://www.youtube.com/watch?v=0D8yFim_nIc
 *   
 *   Player name tutorial taken from https://www.youtube.com/watch?v=Uwr4WCxzB_A
 *
 */
public class Player extends Mob {

	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	private InputHandler input;
	private int color = Colors.get(-1, 212, 225, 445); // BLACK BG is transparent
	private int scale = 1;
	protected boolean isSwimming = false;
	private int tickCount;
	
	private String userName;
	
	public Player(Level level, int x, int y, InputHandler input, String userName) {
		super(level, "Player", x, y, 1);
		this.input = input;
		this.userName = userName;
	}

	public void tick() {
		int xa = 0;
		int ya = 0;
		
		if(input != null) {
			if(input.up.isPressed()) {
				ya--;
			}
			if(input.down.isPressed()) {
				ya++;
			}
			if(input.left.isPressed()) {
				xa--;
			}
			if(input.right.isPressed()) {
				xa++;
			}
		}
		
		// if we're moving in some direction
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			isMoving = true;
		} else {
			isMoving = false;
		}
		
		//check for swimming
		if(level.getTile(this.x >> 3, this.y >> 3).getId() == 8) { // if this Tile is WATER
			isSwimming = true;
		}
		
		if(isSwimming && level.getTile(this.x >> 3, this.y >> 3).getId() != 8) { // if this Tile is NOT water
			isSwimming = false;
		}
		
		tickCount++;
	}

	public void render(Screen screen) { // pixel sheet has character drawn at bottom left of screen (index #28)
		int xTile = 0;
		int yTile = 28;
		
		int displayWalkingSpeed = 4; // arbitrary walk speed
		int flipTop = (numSteps >> displayWalkingSpeed) & 1;
		int flipBottom = (numSteps >> displayWalkingSpeed) & 1;
		
		if(movingDir == 1) {
			xTile += 2; // move over to the FRONT FACING tile
		} else if (movingDir > 1) { // moving LEFT or RIGHT
			xTile += 4 + ((numSteps >> displayWalkingSpeed) & 1) * 2; // move over to the SIDE FACING TILE & get walking animation tile
			flipTop = (movingDir - 1) % 2;
			flipBottom = (movingDir - 1) % 2;
		}
		
		int modifier = 8 * scale; // num pixels per block * scale
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4; // makes center of Player sprite - 4 the center of location
		
		if(isSwimming) {
			int waterColor = 0;
			yOffset += 4; // 
			if(tickCount % 60 < 15) { // cycle colors for "moving" water ripple
				waterColor = Colors.get(-1, -1, 225, -1);
			} else if(15 <= tickCount % 60 && tickCount % 60 < 30) {
				yOffset -= 1;
				waterColor = Colors.get(-1, 225, 115, -1);
			} else if(30 <= tickCount % 60 && tickCount % 60 < 45) {
				waterColor = Colors.get(-1, 115, -1, 225);
			} else {
				yOffset -= 1;
				waterColor = Colors.get(-1, 225, 115, -1);
			}
			screen.render(xOffset, yOffset + 3, 0 + 27 * 32, waterColor, 0x00, 1);
			screen.render(xOffset + 8, yOffset + 3, 0 + 27 * 32, waterColor, 0x01, 1);
		}
		
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, color, flipTop, scale); // tile #1: UL
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, color, flipTop, scale); // TILE #2: UR
		
		// TODO: change this to show "swimming" animation for upper body also
		if(!isSwimming) {
			screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, color, flipBottom, scale); // tile #3: LL
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, color, flipBottom, scale); // TILE #4: LR
		}
		
		if(userName != null) {
			Font.render(userName, screen, xOffset - ((userName.length() - 1) / 2 * 8), yOffset - 10, Colors.get(-1, -1, -1, 555), 1);
		}
	}
	
	public boolean hasCollided(int xa, int ya) {
		int xMin = 1; // start at 1 for X (1px of padding for our Sprite)
		int xMax = 6; // stop at 6 for X (1px of padding for our Sprite)
		int yMin = 1; // start at 1 for Y (1px of padding for our Sprite)
		int yMax = 7; // stop at 7 for Y (no padding on bottom)
		
		for (int x = xMin; x < xMax; x++) { // TOP SIDE OF BOUNDING BOX
			if(isSolidTile(xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) { // BOTTOM SIDE OF BOUNDING BOX
			if(isSolidTile(xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) { // LEFT SIDE OF BOUNDING BOX
			if(isSolidTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) { // RIGHT SIDE OF BOUNDING BOX
			if(isSolidTile(xa, ya, xMax, y)) {
				return true;
			}
		}
		
		return false;
	}
	
	public String getUserName() {
		return this.userName;
	}

}
