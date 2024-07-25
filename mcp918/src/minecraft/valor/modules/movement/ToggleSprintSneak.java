package valor.modules.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import valor.modules.Module.Category;
import valor.events.Event;
import valor.events.listeners.EventUpdate;
import valor.modules.Module;

public class ToggleSprintSneak extends Module {
	
	public Minecraft mc = Minecraft.getMinecraft();
	
	public ToggleSprintSneak() {
		super("Toggle Sprint", Keyboard.KEY_LCONTROL, Category.MOVEMENT);
	}
	
	public void onEnable() {
	}
	
	public void onDisable() {
		mc.thePlayer.setSprinting(false);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				mc.thePlayer.setSprinting(true);
			}
		}
	}
	
}
