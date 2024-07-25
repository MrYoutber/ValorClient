package valor.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import valor.Client;

public class HUD {
	
	public Minecraft mc = Minecraft.getMinecraft();
	
	public void draw() {
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		
		mc.fontRendererObj.drawString(Client.name + " v" + Client.version, 4, 4, -1);
	}

}
