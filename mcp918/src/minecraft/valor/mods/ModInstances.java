package valor.mods;

import valor.gui.hud.HUDManager;
import valor.mods.impl.ModArmorStatus;
import valor.mods.impl.ModFPS;
import valor.mods.impl.ModHelloWorld;
import valor.mods.impl.ModToggleSprint;

public class ModInstances {
	
	private static ModHelloWorld modHelloWorld;
	
	private static ModArmorStatus modArmorStatus;
	
	private static ModFPS modFPS;
	
	private static ModToggleSprint modToggleSprint;
	
	public static void register(HUDManager api) {
		modHelloWorld = new ModHelloWorld();
		api.register(modHelloWorld);
		
		modArmorStatus = new ModArmorStatus();
		api.register(modArmorStatus);
		
		modFPS = new ModFPS();
		api.register(modFPS);
		
		modToggleSprint = new ModToggleSprint();
		api.register(modToggleSprint);
	}
	
	public static ModHelloWorld getModHelloWorld() {
		return modHelloWorld;
	}

}
