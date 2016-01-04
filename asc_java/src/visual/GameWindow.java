package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GameWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		/*
		 * For a multi-monitor setup, use this code instead:
		 * GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		 * int width = gd.getDisplayMode().getWidth();
		 * int height = gd.getDisplayMode().getHeight();
		 */
		int frameWidth = 960;
		int frameHeight = 540;
		int startX = (int) ((width / 2) - (frameWidth / 2));
		int startY = (int) ((height / 2) - (frameHeight / 2));
		frame.setBounds(startX, startY, frameWidth, frameHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set up the JFrame Components
		JButton button1 = new JButton("Click this button!");
//		button1.setHorizontalAlignment(AbstractButton.CENTER);
//		button1.setVerticalAlignment(AbstractButton.CENTER);
		button1.setMnemonic(KeyEvent.VK_C);
		JButton button2 = new JButton("No, THIS button!");
		button2.setMnemonic(KeyEvent.VK_N);
		frame.getContentPane().add(button1, BorderLayout.LINE_START);
		frame.getContentPane().add(new TextArea("This is text in a TextArea Component"), BorderLayout.CENTER);
		frame.getContentPane().add(button2, BorderLayout.LINE_END);
		frame.validate();
	}

}
