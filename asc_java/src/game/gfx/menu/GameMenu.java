/**
 * 
 */
package game.gfx.menu;

import game.InputHandler;
import game.gfx.Colors;
import game.gfx.Screen;
import game.gfx.menu.GameMenuItem;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * @author Joseph DeLong
 *
 */
public class GameMenu extends JComponent {
	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	private InputHandler input;
	private List<GameMenuItem> menuItems;
	private int currentMenuItem = 0;
	private boolean inMenu = false;
	
	// Key binding set up
	private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
	private static final String MOVE_UP = "UP";
	private static final String MOVE_DOWN = "DOWN";
	private static final String MOVE_LEFT = "LEFT";
	private static final String MOVE_RIGHT = "RIGHT";
	private static final String INTERACT = "INTERACT";
	private static final String MENU = "MENU";
	
	public GameMenu(Screen screen, InputHandler input) {
		this.input = input;
		menuItems = new ArrayList<GameMenuItem>();
		menuItems.add(GameMenuItem.NEW);
		menuItems.add(GameMenuItem.SAVE);
		menuItems.add(GameMenuItem.LOAD);
		menuItems.add(GameMenuItem.EXIT);
		
		this.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
		this.getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_DOWN);
		this.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), MOVE_LEFT);
		this.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), MOVE_RIGHT);
		this.getInputMap(IFW).put(KeyStroke.getKeyStroke("E"), INTERACT);
		this.getInputMap(IFW).put(KeyStroke.getKeyStroke("ESCAPE"), MENU);
		
		this.getActionMap().put(MOVE_UP, new MoveAction(MOVE_UP));
		this.getActionMap().put(MOVE_DOWN, new MoveAction(MOVE_DOWN));
		this.getActionMap().put(MOVE_LEFT, new MoveAction(MOVE_LEFT));
		this.getActionMap().put(MOVE_RIGHT, new MoveAction(MOVE_RIGHT));
		this.getActionMap().put(INTERACT, new InteractAction(INTERACT));
		this.getActionMap().put(MENU, new InteractAction(MENU));
	}
	
	private class MoveAction extends AbstractAction {
		/**
		 * Default Serial Version ID
		 */
		private static final long serialVersionUID = 1L;
		
		private int moveDir;
		
		public MoveAction(String movement) {
			switch (movement) {
			case MOVE_UP:
				setMoveDir(0);
				break;
			case MOVE_DOWN:
				setMoveDir(1);
				break;
			case MOVE_LEFT:
				setMoveDir(2);
				break;
			case MOVE_RIGHT:
				setMoveDir(3);
				break;
			default:
				setMoveDir(-1);
				break;
			}
		}
		
		public void actionPerformed(ActionEvent event) {
			// TODO: implement MOVEMENT code for game menu here
			if(event != null) {
				System.out.println(event);
				System.out.println(event.getSource());
			}
			// use event.getSource() to return the object which invokes this event
		}

		/**
		 * @return the moveDir
		 */
		@SuppressWarnings("unused")
		public int getMoveDir() {
			return moveDir;
		}

		/**
		 * @param moveDir the moveDir to set
		 */
		public void setMoveDir(int moveDir) {
			this.moveDir = moveDir;
		}
	}
	
	private class InteractAction extends AbstractAction {
		/**
		 * Default Serial Version ID
		 */
		private static final long serialVersionUID = 1L;
		
		private String actionName;
		private String actionTargetName;
		
		public InteractAction(String action) {
			this.setActionName(action);
			this.setActionTargetName("");
		}
		
		public void actionPerformed(ActionEvent event) {
			// TODO: implement INTERACTION code for game menu here
			// use event.getSource() to return the object which invokes this event
		}

		/**
		 * @return the actionName
		 */
		@SuppressWarnings("unused")
		public String getActionName() {
			return actionName;
		}

		/**
		 * @return the actionTargetName
		 */
		@SuppressWarnings("unused")
		public String getActionTargetName() {
			return actionTargetName;
		}

		/**
		 * @param actionName the actionName to set
		 */
		public void setActionName(String actionName) {
			this.actionName = actionName;
		}

		/**
		 * @param actionTargetName the actionTargetName to set
		 */
		public void setActionTargetName(String actionTargetName) {
			this.actionTargetName = actionTargetName;
		}
	}
	
	public void toggleMenu() {
		inMenu = !inMenu;
		
		if(inMenu) { // if we're displaying the Game Menu
			
		}
	}

	public void tick() {
		if(input != null) {
			if(input.up.isPressed()) {
				if(currentMenuItem < 0 || currentMenuItem >= menuItems.size()) {
					// do nothing
				} else {
					menuItems.get(currentMenuItem).setSelected(true); // select (visually) this menu item
					currentMenuItem = currentMenuItem % menuItems.size() - 1; // go UP one menu item
					for (int i = 0; i < menuItems.size(); i++) {
						if(i == currentMenuItem) {
							menuItems.get(i).setSelected(true);
						} else {
							menuItems.get(i).setSelected(false);
						}
					}
				}
			}
			if(input.down.isPressed()) {
				if(currentMenuItem < 0 || currentMenuItem >= menuItems.size()) {
					// do nothing
				} else {
					menuItems.get(currentMenuItem).setSelected(true); // select (visually) this menu item
					currentMenuItem = currentMenuItem % menuItems.size() + 1; // go DOWN one menu item
				}
			}
			if(input.enter.isPressed()) {
				if(currentMenuItem < 0 || currentMenuItem >= menuItems.size()) {
					// do nothing
				} else {
					menuItems.get(currentMenuItem).execute(); // execute code related to this menu item
				}
			}
			if(input.escape.isPressed()) {
				toggleMenu();
			}
		}
	}
	
	public void render(Screen screen) {
		screen.render(Screen.TILE_SIZE_FONT_MEDIUM, screen.width / 4 + screen.xOffset, screen.height / 8 + screen.yOffset, 0, Colors.get(000, -1, -1, -1), 0x00, Integer.max(screen.height / 8 * 6 / 8, screen.width / 8 * 6 / 8));
		int index = 0;
		for(GameMenuItem i: menuItems) {
			i.render(screen, index, 1);
			index++;
		}
	}

}
