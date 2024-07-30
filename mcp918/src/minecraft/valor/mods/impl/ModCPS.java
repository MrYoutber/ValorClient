package valor.mods.impl;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import valor.gui.hud.ScreenPosition;
import valor.mods.ModDraggable;

public class ModCPS extends ModDraggable {
	
	private ScreenPosition pos;
	private List<Long> clicks = new ArrayList<Long>();
	private boolean wasPressed;
	private long lastPressed;

	@Override
	public int getWidth() {
		return font.getStringWidth("CPS: 00 ");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {

		final boolean pressed = Mouse.isButtonDown(0);
		
		if(pressed != this.wasPressed) {
			this.lastPressed = System.currentTimeMillis();
			this.wasPressed = pressed;
			if(pressed) {
				this.clicks.add(this.lastPressed);
			}
		}
		
		font.drawString("CPS: " + getCPS(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
		
	}
	
	private int getCPS() {
		final long time = System.currentTimeMillis();
		this.clicks.removeIf(aLong -> aLong + 1000 < time);
		return this.clicks.size();
	}

	@Override
	public void save(ScreenPosition pos) {
		System.out.println("save pos");
		this.pos = pos;
	}

	@Override
	public ScreenPosition load() {
		System.out.println(pos);
		return pos;
	}

}