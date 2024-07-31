package valor;

import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import net.minecraft.client.Minecraft;
import valor.events.EventManager;
import valor.events.EventTarget;
import valor.events.impl.ClientTickEvent;
import valor.gui.SplashProgress;
import valor.gui.hud.HUDManager;
import valor.mods.ModInstances;

public class Client {

	public static String name = "Valor Client", version = "1";
	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();

	private static final Client INSTANCE = new Client();

	public static final Client getInstance() {
		return INSTANCE;
	}

	private HUDManager hudManager;

	public void startup() {
		FileManager.init();
		System.out.println("Starting " + name + " - v" + version);
		Display.setTitle(name + " v" + version);

		SplashProgress.setProgress(1, "Valor - Initializing Discord Rich Presence!");
		EventManager.register(this);
	}

	public void start() {
		hudManager = HUDManager.getInstance();
		ModInstances.register(hudManager);
	}

	@EventTarget
	public void onTick(ClientTickEvent e) {
		if (Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_POS.isPressed()) {
			hudManager.openConfigScreen();
		}
	}

}
