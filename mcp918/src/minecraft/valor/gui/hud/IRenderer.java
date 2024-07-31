package valor.gui.hud;

public interface IRenderer extends IRenderConfig {

	int getWidth();

	int getHeight();

	int getOffsetX();

	int getOffsetY();

	void render(ScreenPosition pos);

	default void renderDummy(ScreenPosition pos) {
		render(pos);
	}

	public default boolean isEnabled() {
		return true;
	}

}
