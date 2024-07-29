package valor.mods;

import valor.gui.hud.HUDManager;
import valor.mods.impl.ModArmorStatus;
import valor.mods.impl.ModHelloWorld;

public class ModInstances {
	
	private static ModHelloWorld modHelloWorld;
	
	private static ModArmorStatus modArmorStatus;
	
	public static void register(HUDManager api) {
		modHelloWorld = new ModHelloWorld();
		api.register(modHelloWorld);
		
		modArmorStatus = new ModArmorStatus();
		api.register(modArmorStatus);
	}
	
	public static ModHelloWorld getModHelloWorld() {
		return modHelloWorld;
	}

}
