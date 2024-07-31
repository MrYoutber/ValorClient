package valor.mods.impl;

import net.minecraft.client.settings.KeyBinding;
import valor.gui.hud.ScreenPosition;
import valor.mods.ModDraggable;

public class ModKeystrokes extends ModDraggable {

	private static class Key {

		private final String name;
		private final KeyBinding keyBind;
		private final int x;
		private final int y;
		private final int width;
		private final int height;

		public Key(String name, KeyBinding keyBind, int x, int y, int width, int height) {
			this.name = name;
			this.keyBind = keyBind;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		public boolean isDown() {
			return keyBind.getIsKeyPressed();
		}

		public String getName() {
			return name;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void render(ScreenPosition pos) {
		// TODO Auto-generated method stub

	}

}
