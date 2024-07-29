package valor.mods.impl;

import valor.gui.hud.ScreenPosition;
import valor.mods.ModDraggable;

public class ModFPS extends ModDraggable {
	
	private ScreenPosition pos;

	@Override
	public int getWidth() {
		return 50;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		font.drawString(mc.getDebugFPS() + " FPS", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}

	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
	}

	@Override
	public ScreenPosition load() {
		return pos;
	}

}
