/**
 * 
 */
package visual;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import visual.net.packets.Packet01Disconnect;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=pfl8L-_3fQA
 *
 */
public class WindowHandler implements WindowListener {
	
	private final GameWindow gameWindow;
	
	public WindowHandler(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		this.gameWindow.frame.addWindowListener(this);
	}

	@Override
	public void windowActivated(WindowEvent event) {
		
	}

	@Override
	public void windowClosed(WindowEvent event) {
		
	}

	@Override
	public void windowClosing(WindowEvent event) {
		Packet01Disconnect packet = new Packet01Disconnect(this.gameWindow.player.getUserName());
		packet.writeData(this.gameWindow.socketClient);
	}

	@Override
	public void windowDeactivated(WindowEvent event) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent event) {
		
	}

	@Override
	public void windowIconified(WindowEvent event) {
		
	}

	@Override
	public void windowOpened(WindowEvent event) {
		
	}

	
	
}
