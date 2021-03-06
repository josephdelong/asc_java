/**
 * 
 */
package game.entities;

import game.InputHandler;
import game.gfx.Colors;
import game.gfx.Font;
import game.gfx.Screen;
import game.level.Level;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=dQP7mFhqgg
 *   
 *   Walking Animation tutorial taken from https://www.youtube.com/watch?v=dnvNcjVNqqs
 *   TODO: Fix up animation / rendering for single-width sprite?
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
	protected boolean isSprinting = false;
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
			isSprinting = false;
			int speed = 1;
			if(input.shift.isPressed()) {
				isSprinting = true;
				speed = 2;
			}
			if(input.up.isPressed()) {
				ya -= speed;
			}
			if(input.down.isPressed()) {
				ya += speed;
			}
			if(input.left.isPressed()) {
				xa -= speed;
			}
			if(input.right.isPressed()) {
				xa += speed;
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

	public void render(Screen screen) { // pixel sheet has character drawn at bottom left of screen (yTile = 24)
				
		int xTile = 0;
		int yTile = 24; // player animations begin on this line
		
		int displayWalkingSpeed = 4; // arbitrary walk speed
		int flipTop = (this.numSteps >> displayWalkingSpeed) & 1;
		int flipBottom = (this.numSteps >> displayWalkingSpeed) & 1;
		
		int modifier = 8 * scale; // num pixels per block * scale
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4; // makes center of Player sprite - 4 the center of location
		
		/*
		 * Player animations:
		 * 	STANDING STILL (NORMAL):
		 * 		BACK: yTile = 26, xTile = 0
		 * 			ANIMATION 2nd FRAME: yTile = 26, xTile = 2
		 * 		FRONT: yTile = 26, xTile = 4
		 * 			ANIMATION 2nd FRAME: yTile = 26, xTile = 6
		 * 		SIDE: yTile = 26, xTile = 8
		 * 			ANIMATION 2nd FRAME: yTile = 26, xTile = 10
		 * 	STANDING STILL (ACTIVE):
		 * 		BACK: yTile = 26, xTile = 16
		 * 			ANIMATION 2nd FRAME: yTile = 26, xTile = 18
		 * 		FRONT: yTile = 26, xTile = 20
		 * 			ANIMATION 2nd FRAME: yTile = 26, xTile = 22
		 * 		SIDE: yTile = 26, xTile = 24
		 * 			ANIMATION 2nd FRAME: yTile = 26, xTile = 26
		 * 	MOVING (NORMAL):
		 * 		BACK: yTile = 24, xTile = 0
		 * 		FRONT: yTile = 24, xTile = 2
		 * 		SIDE: yTile = 24, xTile = 4
		 * 			ANIMATION 2nd FRAME: yTile = 24, xTile = 6
		 * 	MOVING (ACTIVE):
		 * 		BACK: yTile = 24, xTile = 8
		 * 			ANIMATION 2nd FRAME: yTile = 24, xTile = 10
		 * 			ANIMATION 3rd FRAME: yTile = 24, xTile = 12
		 * 			ANIMATION 4th FRAME: yTile = 24, xTile = 14
		 * 		FRONT: yTile = 24, xTile = 16
		 * 			ANIMATION 2nd FRAME: yTile = 24, xTile = 18
		 * 			ANIMATION 3rd FRAME: yTile = 24, xTile = 20
		 * 			ANIMATION 4th FRAME: yTile = 24, xTile = 22
		 * 		SIDE: yTile = 24, xTile = 24
		 * 			ANIMATION 2nd FRAME: yTile = 24, xTile = 26
		 * 			ANIMATION 3rd FRAME: yTile = 24, xTile = 28
		 * 			ANIMATION 4th FRAME: yTile = 24, xTile = 30
		 * 
		 * TODO: Fix up references to isSprinting
		 */
		if(isSprinting) { // PUMPED up and ready for ACTION
			if(!isMoving) { // standing still
				if(movingDir == 0) { // facing BACK
					if(tickCount % 60 < 30) { // cycle animation frames
						yTile += 2;
						xTile += 16; // BACK PUMPED animation FRAME 1
					} else {
						yTile += 2;
						xTile += 18; // BACK PUMPED animation FRAME 2
					}
				} else if(movingDir == 1) { // facing FRONT
					if(tickCount % 60 < 30) { // cycle animation frames
						yTile += 2;
						xTile += 20; // FRONT PUMPED animation FRAME 1
					} else {
						yTile += 2;
						xTile += 22; // FRONT PUMPED animation FRAME 2
					}
				} else if(movingDir > 1) { // facing LEFT or RIGHT
					if(tickCount % 60 < 30) { // cycle animation frames
						yTile += 2;
						xTile += 24; // SIDE PUMPED FRAME 1
					} else {
						yTile += 2;
						xTile += 26; // SIDE PUMPED FRAME 2
					}
					flipTop = (movingDir - 1) % 2;
					flipBottom = (movingDir - 1) % 2;
				}
			} else { // moving
				if(movingDir == 0) { //facing BACK
					if(tickCount % 60 < 10) { //cycle animation frames
						xTile += 8; // BACK RUNNING frame 1
						yOffset -= 1; // fully off the ground
					} else if(10 <= tickCount && tickCount <= 20) {
						xTile += 10; // BACK RUNNING frame 2
						yOffset -= 1; // fully off the ground
					} else if(20 <= tickCount && tickCount <= 30) {
						xTile += 12; // BACK RUNNING frame 3
					} else if(30 <= tickCount && tickCount <= 40) {
						xTile += 14; // BACK RUNNING frame 4
					} else if(40 <= tickCount && tickCount <= 50) {
						xTile += 12; // BACK RUNNING frame 5
					} else {
						xTile += 10; // BACK RUNNING frame 6
						yOffset -= 1; // fully off the ground
					}
				} else if(movingDir == 1) { // facing FRONT
					if(tickCount % 60 < 10) { //cycle animation frames
						xTile += 16; // FRONT RUNNING frame 1
						yOffset -= 1; // fully off the ground
					} else if(10 <= tickCount && tickCount <= 20) {
						xTile += 18; // FRONT RUNNING frame 2
						yOffset -= 1; // fully off the ground
					} else if(20 <= tickCount && tickCount <= 30) {
						xTile += 20; // FRONT RUNNING frame 3
					} else if(30 <= tickCount && tickCount <= 40) {
						xTile += 22; // FRONT RUNNING frame 4
					} else if(40 <= tickCount && tickCount <= 50) {
						xTile += 20; // FRONT RUNNING frame 5
					} else {
						xTile += 18; // FRONT RUNNING frame 6
						yOffset -= 1; // fully off the ground
					}
				} else if(movingDir > 1) { // facing LEFT or RIGHT
//					int currentNumSteps = this.numSteps;
					if(tickCount % 60 < 10) { //cycle animation frames
//					int frameIndex = (this.numSteps - currentNumSteps) % 6;
//					if(frameIndex == 4) frameIndex = 2;
//					if(frameIndex == 5) frameIndex = 1;
						xTile += 24; // SIDE RUNNING frame 1 // + (frameIndex - 1 * 2)
						yOffset -= 1; // fully off the ground
					} else if(10 <= tickCount && tickCount <= 20) {
						xTile += 26; // SIDE RUNNING frame 2
						yOffset -= 1; // fully off the ground
					} else if(20 <= tickCount && tickCount <= 30) {
						xTile += 28; // SIDE RUNNING frame 3
					} else if(30 <= tickCount && tickCount <= 40) {
						xTile += 30; // SIDE RUNNING frame 4
					} else if(40 <= tickCount && tickCount <= 50) {
						xTile += 28; // SIDE RUNNING frame 5
					} else {
						xTile += 26; // SIDE RUNNING frame 6
						yOffset -= 1; // fully off the ground
					}
					flipTop = (movingDir - 1) % 2;
					flipBottom = (movingDir - 1) % 2;
				}
			}
		} else { // not PUMPED
			if(!isMoving) { // standing still
				if(movingDir == 0) { // facing BACK
					// TODO: insert *WAIT PERIOD* before animating IDLE frames
					if(tickCount % 60 < 30) { // cycle animation frames
						yTile += 2;
						xTile += 0; // NORMAL BACK animation FRAME 1
					} else {
						yTile += 2;
						xTile += 2; // NORMAL BACK animation FRAME 2
					}
				} else if(movingDir == 1) { // facing FRONT
					if(tickCount % 60 < 30) { // cycle animation frames
						yTile += 2;
						xTile += 4; // NORMAL FRONT animation FRAME 1
					} else {
						yTile += 2;
						xTile += 6; // NORMAL FRONT animation FRAME 2
					}
				} else if(movingDir > 1) { // facing LEFT or RIGHT
					if(tickCount % 60 < 30) { // cycle animation frames
						yTile += 2;
						xTile += 8; // SIDE NORMAL FRAME 1
					} else {
						yTile += 2;
						xTile += 10; // SIDE NORMAL FRAME 2
					}
					flipTop = (movingDir - 1) % 2;
					flipBottom = (movingDir - 1) % 2;
				}
			} else { // moving
				if(movingDir == 0) { //facing BACK
//					if(tickCount % 60 < 15) { //cycle animation frames
//						xTile += 0; // BACK WALKING frame 1
//					} else if(15 <= tickCount && tickCount <= 30) {
//						xTile += 0; // BACK WALKING frame 2
//					} else if(30 <= tickCount && tickCount <= 45) {
//						xTile += 0; // BACK WALKING frame 3
//					} else {
//						xTile += 0; // BACK WALKING frame 4
//					}
				} else if(movingDir == 1) { // facing FRONT
//					if(tickCount % 60 < 15) { //cycle animation frames
						xTile += 2; // FRONT WALKING frame 1
//					} else if(15 <= tickCount && tickCount <= 30) {
//						xTile += 0; // FRONT WALKING frame 2
//					} else if(30 <= tickCount && tickCount <= 45) {
//						xTile += 0; // FRONT WALKING frame 3
//					} else {
//						xTile += 0; // FRONT WALKING frame 4
//					}
				} else if(movingDir > 1) { // facing LEFT or RIGHT
//					if(tickCount % 60 < 30) { //cycle animation frames
						xTile += 4 + ((this.numSteps >> displayWalkingSpeed) & 1) * 2; // SIDE WALKING frame 1
//					} else if(30 <= tickCount && tickCount <= 60) {
//						xTile += 6; // SIDE WALKING frame 2
//					} else if(30 <= tickCount && tickCount <= 45) {
//						xTile += 0; // SIDE WALKING frame 3
//					} else {
//						xTile += 0; // SIDE WALKING frame 4
//					}
					flipTop = (movingDir - 1) % 2;
					flipBottom = (movingDir - 1) % 2;
				}
			}
		}
		
//		if(movingDir == 1) {
//			xTile += 2; // move over to the FRONT FACING tile
//		} else if (movingDir > 1) { // moving LEFT or RIGHT
//			if(isSprinting) {
//				if(tickCount % 60 < 10) { // cycle animation frames
//					xTile += 8 + ((this.numSteps >> displayWalkingSpeed / 2) & 1) * 2; // get FIRST frame of running animation
//				} else if(10 <= tickCount % 60 && tickCount % 60 < 20) {
//					xTile += 10 + ((this.numSteps >> displayWalkingSpeed / 2) & 1) * 2; // get SECOND frame of running animation
//				} else if(20 <= tickCount % 60 && tickCount % 60 < 30) {
//					xTile += 12 + ((this.numSteps >> displayWalkingSpeed / 2) & 1) * 2; // get THIRD frame of running animation
//				} else if(30 <= tickCount % 60 && tickCount % 60 < 40) {
//					xTile += 14 + ((this.numSteps >> displayWalkingSpeed / 2) & 1) * 2; // get FOURTH frame of running animation
//				} else if(40 <= tickCount % 60 && tickCount % 60 < 50) {
//					xTile += 12 + ((this.numSteps >> displayWalkingSpeed / 2) & 1) * 2; // get FIFTH frame of running animation
//				} else /*if(50 <= tickCount % 60 && tickCount % 60 < 60)*/ {
//					xTile += 10 + ((this.numSteps >> displayWalkingSpeed / 2) & 1) * 2; // get SIXTH frame of running animation{
//				}
//			} else {
//				xTile += 4 + ((this.numSteps >> displayWalkingSpeed) & 1) * 2; // move over to the SIDE FACING TILE & get walking animation tile
//			}
//			flipTop = (movingDir - 1) % 2;
//			flipBottom = (movingDir - 1) % 2;
//		}
		
		if(isSwimming) { // render the WATER RIPPLE tile
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
			screen.render(Screen.TILE_SIZE_PLAYER, xOffset, yOffset + 3, 0 + 23 * 32, waterColor, 0x00, 1); // water ripple tile has yIndex of 23
			screen.render(Screen.TILE_SIZE_PLAYER, xOffset + 8, yOffset + 3, 0 + 23 * 32, waterColor, 0x01, 1);
		}
		
		// UPPER BODY
		screen.render(Screen.TILE_SIZE_PLAYER, xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, color, flipTop, scale); // tile #1: UL
		screen.render(Screen.TILE_SIZE_PLAYER, xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, color, flipTop, scale); // TILE #2: UR
		
		// LOWER BODY TODO: change this to show "swimming" animation for upper body also
		if(!isSwimming) {
			screen.render(Screen.TILE_SIZE_PLAYER, xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, color, flipBottom, scale); // tile #3: LL
			screen.render(Screen.TILE_SIZE_PLAYER, xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, color, flipBottom, scale); // TILE #4: LR
		}
		
		if(userName != null) {
			if(userName.length() % 2 == 0) {
				Font.render(userName, screen, "small", xOffset - ((userName.length() - 1) / 2 * 4), yOffset - 10, Colors.get(-1, -1, -1, 555), 1);
			} else {
				Font.render(userName, screen, "small", xOffset - ((userName.length() - 1) / 2 * 4) + 4, yOffset - 10, Colors.get(-1, -1, -1, 555), 1);
			}
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
