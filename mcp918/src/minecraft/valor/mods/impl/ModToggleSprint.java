package valor.mods.impl;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.Gui;
import valor.gui.hud.ScreenPosition;
import valor.mods.ModDraggable;

public class ModToggleSprint extends ModDraggable {

	private boolean isToggled = false;
	private boolean wasLeftCtrlPressed = false;

	private int offsetX = 7;
	private int offsetY = 5;

	@Override
	public int getWidth() {
		return font.getStringWidth(isToggled ? "Sprinting (toggled)" : "Sprinting (vanilla)");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public int getOffsetX() {
		return offsetX;
	}

	@Override
	public int getOffsetY() {
		return offsetY;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.thePlayer.isSprinting() && isToggled == false) {
			Gui.drawRect(pos.getAbsoluteX() - getOffsetX(), pos.getAbsoluteY() - getOffsetY(),
					pos.getAbsoluteX() + getWidth() + getOffsetX(), pos.getAbsoluteY() + getHeight() + getOffsetY(),
					new Color(0, 0, 0, 120).getRGB());
			font.drawString("Sprinting (vanilla)", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
		}

		if (isLeftCtrlPressed()) {
			isToggled = !isToggled;
		}

		updateLeftCtrlState();

		if (isToggled == true) {
			Gui.drawRect(pos.getAbsoluteX() - getOffsetX(), pos.getAbsoluteY() - getOffsetY(),
					pos.getAbsoluteX() + getWidth() + getOffsetX(), pos.getAbsoluteY() + getHeight() + getOffsetY(),
					new Color(0, 0, 0, 120).getRGB());
			font.drawString("Sprinting (toggled)", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);

			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				mc.thePlayer.setSprinting(true);
			}
		}
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX() - getOffsetX(), pos.getAbsoluteY() - getOffsetY(),
				pos.getAbsoluteX() + font.getStringWidth("Sprinting (toggled)") + getOffsetX(),
				pos.getAbsoluteY() + getHeight() + getOffsetY(), new Color(0, 0, 0, 120).getRGB());
		font.drawString("Sprinting (toggled)", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
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
