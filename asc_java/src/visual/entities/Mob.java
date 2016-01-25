/**
 * 
 */
package visual.entities;

import visual.level.Level;
import visual.level.tiles.Tile;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=dQP7mFhqgg
 *
 */
public abstract class Mob extends Entity {

	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	protected String name;
	protected int speed;
	protected int numSteps = 0;
	protected boolean isMoving;
	protected int movingDir = 1; // 0 = UP, 1 = DOWN, 2 = LEFT, 3 = RIGHT
	protected int scale = 1;
	
	public Mob(Level level, String name, int x, int y, int speed) {
		super(level);
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public void move(int xa, int ya) {
		if(xa != 0 && ya!= 0) { // if multiple directional keys pressed simultaneously
			move(xa, 0);
			move(0, ya);
			numSteps--;
			return;
		}
		numSteps++;
		
		if(!hasCollided(xa, ya)) {
			if(ya < 0) {
				movingDir = 0; // moving UP
			}
			if(ya > 0) {
				movingDir = 1; // moving DOWN
			}
			if(xa < 0) {
				movingDir = 2; // moving LEFT
			}
			if(xa > 0) {
				movingDir = 3; // moving RIGHT
			}
			x += xa * speed;
			y += ya * speed;
		}
	}
	
	public abstract boolean hasCollided(int xa, int ya);
	
	protected boolean isSolidTile(int xa, int ya, int x, int y) {
		if(level == null) {
			return false;
		}
		Tile lastTile = level.getTile((this.x + x) >> 3, (this.y + y) >> 3);
		Tile newTile = level.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
		if(!lastTile.equals(newTile) && newTile.isSolid()) { // make sure newTile isn't lastTile && newTile IS solid
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}

}
