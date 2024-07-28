package valor;

import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import valor.events.Event;
import valor.events.EventManager;
import valor.gui.SplashProgress;

public class Client {
	
	public static String name = "Valor Client", version = "1";
	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	
	public static void startup() {
		System.out.println("Starting " + name + " - v" + version);
		Display.setTitle(name + " v" + version);
		
		SplashProgress.setProgress(1, "Valor - Initializing Discord Rich Presence!");
	}
	
}
