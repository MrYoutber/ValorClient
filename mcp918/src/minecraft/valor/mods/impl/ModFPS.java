package valor.mods.impl;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import valor.gui.hud.ScreenPosition;
import valor.mods.ModDraggable;

public class ModFPS extends ModDraggable {

	private int offsetX = 7;
	private int offsetY = 5;

	@Override
	public int getWidth() {
		return font.getStringWidth("000 FPS");
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
		Gui.drawRect(pos.getAbsoluteX() - getOffsetX(), pos.getAbsoluteY() - getOffsetY(),
				pos.getAbsoluteX() + font.getStringWidth(mc.getDebugFPS() + " FPS") + getOffsetX(),
				pos.getAbsoluteY() + getHeight() + getOffsetY(), new Color(0, 0, 0, 120).getRGB());
		font.drawString(mc.getDebugFPS() + " FPS", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}

}
