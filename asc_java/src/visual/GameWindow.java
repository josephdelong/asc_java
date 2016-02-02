package visual;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import visual.entities.Player;
import visual.entities.PlayerMP;
import visual.gfx.Screen;
import visual.gfx.SpriteSheet;
import visual.gfx.menu.GameMenu;
import visual.level.Level;
import visual.net.GameClient;
import visual.net.GameServer;
import visual.net.packets.Packet00Login;

public class GameWindow extends Canvas implements Runnable {
	public GameWindow() {
	}

	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 3;
	public static final String NAME = "2D Game Demo";
	public static final Dimension DIMENSIONS = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	public JFrame frame;
	
	private Thread thread;
	
	public boolean running = false;
	public int tickCount = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_BGR);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colors = new int[6 * 6 * 6]; // limiting color palette to 6 shades for each RGB channel
	
	private Screen screen;
	public InputHandler input;
	public WindowHandler windowHandler;
	public Level level;
	public Player player;
	public GameMenu menu;
	
	public GameClient socketClient;
	public GameServer socketServer;
	
	public boolean debug = true;
	
	public boolean isApplet = false;
	
	public void init() {
		int index = 0;
		// set up colors
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);
					//populate colors: 255 will be TRANSPARENT
					colors[index++] = (rr << 16) | (gg << 8) | (bb);
				}
			}
		}
		
		screen = new Screen(WIDTH,HEIGHT,new SpriteSheet("/pixel_sheet.png"));
		input = new InputHandler(this);
		level = new Level("/levels/terrain_test_level.png");
		player = new PlayerMP(level, 100, 100, input, JOptionPane.showInputDialog(this, "Please enter a User Name"), null, -1); // start at (100, 100) for now
		level.addEntity(player);
		menu = new GameMenu(screen, input);
		if(!isApplet) {
			Packet00Login loginPacket = new Packet00Login(player.getUserName());
			if(socketServer != null) {
				socketServer.addConnection((PlayerMP)player, loginPacket);
			}
//			socketClient.sendData("ping".getBytes());
//			Packet00Login loginPacket = new Packet00Login(JOptionPane.showInputDialog(this, "Please enter a user name"));
			loginPacket.writeData(socketClient);
		}
	}

	public synchronized void start() {
		running = true;
		
		thread = new Thread(this, NAME + "_main");
		thread.start();
		
		if(!isApplet) {
			if(JOptionPane.showConfirmDialog(this, "Do you want to run the server?") == 0) {
				socketServer = new GameServer(this);
				socketServer.start();
			}
			
			socketClient = new GameClient(this, "localHost");
			socketClient.start();
		}
	}

	public synchronized void stop() {
		running = false;
		
		try {
			thread.join(); // close the thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0; // how many nanoseconds have elapsed so far
		
		init();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			
			while(delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(shouldRender) {
				frames++;
				render();
			}
			
			if(System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				debug(DebugLevel.INFO,ticks + " ticks, " + frames + " frames");
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	/**
	 * Update all internal variables & logic of the running game.
	 */
	public void tick() {
		tickCount++;
		
		if(input.escape.isPressed()) {
			menu.toggleMenu();
		}
		
		if(!input.inMenu) {
			level.tick();
		}
		if(input.inMenu) {
			menu.tick();
		}
	}
	
	/**
	 * Display updated logic from tick()
	 */
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3); // n-buffering helps reduce tearing...3 = triple buffering
			return;
		}
		
		int xOffset = player.x - (screen.width / 2);
		int yOffset = player.y - (screen.height / 2);
		level.renderTiles(screen, xOffset, yOffset);
		level.renderEntities(screen);
		
		if(input.inMenu) {
			menu.render(screen);
		}
		
		// copied from Screen
		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colorCode = screen.pixels[x + y * screen.width];
				if(colorCode < 255) { // if color != transparent
					pixels[x + y * WIDTH] = colors[colorCode];
				}
			}
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.dispose(); // free-up memory
		bs.show(); // show buffer contents
	}
	
	public void debug(DebugLevel level, String message) {
		switch (level) {
		default:
		case INFO:
			if(this.debug) {
				System.out.println("[" + NAME + "] " + message);
			}
			break;
		case WARNING:
			System.out.println("[" + NAME + "][WARNING] " + message);
			break;
		case SEVERE:
			System.out.println("[" + NAME + "][SEVERE] " + message);
			this.stop();
			break;
		}
	}
	
	public static enum DebugLevel{
		INFO,WARNING,SEVERE;
	}
	
}
