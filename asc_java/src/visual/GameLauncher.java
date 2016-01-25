/**
 * 
 */
package visual;

import java.applet.Applet;
import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=l3htgKqs8kU
 *
 */
@SuppressWarnings("serial")
public class GameLauncher extends Applet{
	
	private static GameWindow gameWindow = new GameWindow();
	public static final boolean DEBUG = false;
	
	@Override
	public void init() {
		setLayout(new BorderLayout());
		add(gameWindow, BorderLayout.CENTER);
		setMaximumSize(GameWindow.DIMENSIONS);
		setMinimumSize(GameWindow.DIMENSIONS);
		setPreferredSize(GameWindow.DIMENSIONS);
		gameWindow.debug = DEBUG;
		gameWindow.isApplet = true;
	}
	
	@Override
	public void start() {
		gameWindow.start();
	}
	
	@Override
	public void stop() {
		gameWindow.stop();
	}
	
	public static void main(String[] args) {
		gameWindow.setMinimumSize(GameWindow.DIMENSIONS);
		gameWindow.setMaximumSize(GameWindow.DIMENSIONS);
		gameWindow.setPreferredSize(GameWindow.DIMENSIONS);
		
		gameWindow.frame = new JFrame(GameWindow.NAME);
		
		gameWindow.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.frame.setLayout(new BorderLayout());
		
		gameWindow.frame.add(gameWindow, BorderLayout.CENTER);
		gameWindow.frame.pack();
		
		gameWindow.frame.setResizable(false);
		gameWindow.frame.setLocationRelativeTo(null);
		gameWindow.frame.setVisible(true);
		
		gameWindow.windowHandler = new WindowHandler(gameWindow);
		gameWindow.debug = DEBUG;
		
		gameWindow.requestFocus();
		
		gameWindow.start();
	}

}
