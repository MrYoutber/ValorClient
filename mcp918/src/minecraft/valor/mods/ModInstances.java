package valor.mods;

import valor.gui.hud.HUDManager;
import valor.mods.impl.ModArmorStatus;
import valor.mods.impl.ModCPS;
import valor.mods.impl.ModFPS;
import valor.mods.impl.ModKeystrokes;
import valor.mods.impl.ModToggleSprint;
import valor.mods.impl.ModXYZ;

public class ModInstances {

	private static ModArmorStatus modArmorStatus;

	private static ModFPS modFPS;

	private static ModToggleSprint modToggleSprint;

	private static ModCPS modCPS;

	private static ModKeystrokes modKeystrokes;

	private static ModXYZ modXYZ;

	public static void register(HUDManager api) {
		modArmorStatus = new ModArmorStatus();
		api.register(modArmorStatus);

		modFPS = new ModFPS();
		api.register(modFPS);

		modToggleSprint = new ModToggleSprint();
		api.register(modToggleSprint);

		modCPS = new ModCPS();
		api.register(modCPS);

		modKeystrokes = new ModKeystrokes();
		api.register(modKeystrokes);

		modXYZ = new ModXYZ();
		api.register(modXYZ);
	}

}
