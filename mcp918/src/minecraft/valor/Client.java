package valor;

import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import valor.events.Event;
import valor.gui.SplashProgress;
import valor.modules.Module;
import valor.modules.movement.ToggleSprintSneak;
import valor.ui.HUD;

public class Client {
	
	public static String name = "Valor Client", version = "1";
	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	public static HUD hud = new HUD();
	
	public static void startup() {
		System.out.println("Starting " + name + " - v" + version);
		Display.setTitle(name + " v" + version);
		
		modules.add(new ToggleSprintSneak());
		
		SplashProgress.setProgress(1, "Valor - Initializing Discord Rich Presence!");
	}
	
	public static void onEvent(Event e) {
		for(Module m : modules) {
			if (!m.toggled) {
				continue;
			}
			
			m.onEvent(e);
		}
	}
	
	public static void keyPress(int key) {
		for(Module m : modules) {
			if(m.getKey() == key) {
				m.toggle();
			}
		}
	}
	
}
