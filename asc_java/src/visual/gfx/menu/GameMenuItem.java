/**
 * 
 */
package visual.gfx.menu;

import visual.gfx.Colors;
import visual.gfx.Font;
import visual.gfx.Screen;

/**
 * @author Joseph DeLong
 *
 */
public enum GameMenuItem {
	EXIT(0), NEW(1), SAVE(2), LOAD(3);
	
	private int type;
	private String displayName;
	private boolean selected;
	
	private GameMenuItem(int type) {
		this.type = type;
		switch (type) {
		case 0:
			this.displayName = "EXIT";
			break;
		case 1:
			this.displayName = "NEW";
			break;
		case 2:
			this.displayName = "SAVE";
			break;
		case 3:
			this.displayName = "LOAD";
			break;
		}
	}

	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void execute() {
		switch (type) {
		case 1:
			// do NEW
			break;
		case 2:
			// do SAVE
			break;
		case 3:
			// do LOAD
			break;
		case 4:
			// do EXIT
			System.exit(0);
			break;
		}
	}
	
	public void render(Screen screen, int menuIndex, int scale) {
		if(menuIndex < 0 || menuIndex > 3) {
			// do nothing
			return;
		}
		
		int xOffset = screen.xOffset + (screen.width / 2);
		int yOffset = screen.yOffset + (screen.height / 4) + (menuIndex * 8 * scale); // each char is 8 pixels high (before scale modification)
		
		if(this.isSelected()) {
			Font.render(this.displayName, screen, xOffset, yOffset, Colors.get(-1, -1, -1, 555), scale);
//			screen.render(xOffset, yOffset, 0, Colors.get(000, -1, -1, 555), 0x00, scale);
		} else {
			Font.render(this.displayName, screen, xOffset, yOffset, Colors.get(-1, -1, -1, 444), scale);
//			screen.render(xOffset, yOffset, 0, Colors.get(000, -1, -1, 333), 0x00, scale);
		}
	}
	
}
