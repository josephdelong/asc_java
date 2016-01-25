/**
 * 
 */
package visual.level.tiles;


/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=0D8yFim_nIc
 *
 */
public class AnimatedTile extends BaseTile {
	
	private int[][] animationTioleCoords;
	private int currentAnimationIndex;
	private long lastIterationTime; // ms of last update
	private int animationSwitchDeLay;
	
	public AnimatedTile(int id, int[][] animationCoords, int tileColor, int levelColor, int animationSwitchDelay) {
		super(id, animationCoords[0][0], animationCoords[0][1], tileColor, levelColor);
		this.animationTioleCoords = animationCoords;
		this.currentAnimationIndex = 0;
		this.lastIterationTime = System.currentTimeMillis();
		this.animationSwitchDeLay = animationSwitchDelay;;
	}
	
	public void tick() {
		if((System.currentTimeMillis() - lastIterationTime) >= (animationSwitchDeLay)) {
			lastIterationTime = System.currentTimeMillis();
			currentAnimationIndex = (currentAnimationIndex + 1) % animationTioleCoords.length;
			tileId = (this.animationTioleCoords[currentAnimationIndex][0] + (animationTioleCoords[currentAnimationIndex][1] * 32)); // update tileId with what it should be next
		}
	}
}
