package valor.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.Gui;
import valor.gui.hud.ScreenPosition;
import valor.mods.ModDraggable;

public class ModCPS extends ModDraggable {

	private List<Long> clicks = new ArrayList<Long>();
	private boolean wasPressed;
	private long lastPressed;

	private int offsetX = 7;
	private int offsetY = 5;

	@Override
	public int getWidth() {
		return font.getStringWidth("CPS: " + getCPS());
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

		final boolean pressed = Mouse.isButtonDown(0);

		if (pressed != this.wasPressed) {
			this.lastPressed = System.currentTimeMillis();
			this.wasPressed = pressed;
			if (pressed) {
				this.clicks.add(this.lastPressed);
			}
		}

		Gui.drawRect(pos.getAbsoluteX() - getOffsetX(), pos.getAbsoluteY() - getOffsetY(),
				pos.getAbsoluteX() + font.getStringWidth("CPS: " + getCPS()) + getOffsetX(),
				pos.getAbsoluteY() + getHeight() + getOffsetY(), new Color(0, 0, 0, 120).getRGB());
		font.drawString("CPS: " + getCPS(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);

	}

	private int getCPS() {
		final long time = System.currentTimeMillis();
		this.clicks.removeIf(aLong -> aLong + 1000 < time);
		return this.clicks.size();
	}

}
