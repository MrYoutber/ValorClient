package valor.mods.impl;

import org.lwjgl.input.Keyboard;

import valor.gui.hud.ScreenPosition;
import valor.mods.ModDraggable;

public class ModToggleSprint extends ModDraggable {
	
	private ScreenPosition pos;
	private boolean isToggled = false;
	private boolean wasLeftCtrlPressed = false;

	@Override
	public int getWidth() {
		return font.getStringWidth(isToggled ? "Sprinting (toggled)" : "Sprinting (vanilla)") + 5;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if(mc.thePlayer.isSprinting() && isToggled == false) {
			font.drawString("Sprinting (vanilla)", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
		}
		
		if(isLeftCtrlPressed()) {
			isToggled = !isToggled;
		}
		
		updateLeftCtrlState();
		
		if(isToggled == true) {
			font.drawString("Sprinting (toggled)", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
			
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				mc.thePlayer.setSprinting(true);
			}
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString("Sprinting (toggled)", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}

	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
	}

	@Override
	public ScreenPosition load() {
		return pos;
	}
	
	public boolean isLeftCtrlPressed() {
        boolean isCurrentlyPressed = Keyboard.isKeyDown(Keyboard.KEY_LCONTROL);
        boolean wasPressed = !wasLeftCtrlPressed && isCurrentlyPressed;
        wasLeftCtrlPressed = isCurrentlyPressed;
        return wasPressed;
    }

    private void updateLeftCtrlState() {
        wasLeftCtrlPressed = Keyboard.isKeyDown(Keyboard.KEY_LCONTROL);
    }
	
}
