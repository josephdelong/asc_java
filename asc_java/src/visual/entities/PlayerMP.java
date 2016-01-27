/**
 * 
 */
package visual.entities;

import java.net.InetAddress;

import visual.InputHandler;
import visual.level.Level;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=7Qcg6Hvx_WU
 *
 */
public class PlayerMP extends Player {

	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	public InetAddress ipAddress;
	public int port;
	
	public PlayerMP(Level level, int x, int y, InputHandler input, String userName, InetAddress ipAddress, int port) {
		super(level, x, y, input, userName);
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	/**
	 * Local connected Player (on same local machine), so in put is through THIS keyboard
	 * @param level
	 * @param x
	 * @param y
	 * @param userName
	 * @param ipAddress
	 * @param port
	 */
	public PlayerMP(Level level, int x, int y, String userName, InetAddress ipAddress, int port) {
		super(level, x, y, null, userName);
		this.ipAddress = ipAddress;
		this.port = port;
	}

	@Override
	public void tick() {
		super.tick();
	}
	
}
