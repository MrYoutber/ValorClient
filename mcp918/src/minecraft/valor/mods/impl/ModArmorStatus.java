package valor.mods.impl;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import valor.gui.hud.ScreenPosition;
import valor.mods.ModDraggable;

public class ModArmorStatus extends ModDraggable {

	@Override
	public int getWidth() {
		return 16;
	}

	@Override
	public int getHeight() {
		return 80;
	}

	@Override
	public void render(ScreenPosition pos) {
		for (int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
			ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
			renderItemStack(pos, i, itemStack);
		}

		ItemStack handItem = mc.thePlayer.getHeldItem();
		if (handItem == null)
			return;
		int itemAmount = handItem.stackSize;

		// Render the item first
		renderItemStack(pos, -1, handItem);

		// Now render the text above the item (in terms of rendering layers)
		if (itemAmount != 1) {
			int itemX = pos.getAbsoluteX() - 1;
			int itemY = pos.getAbsoluteY() + 80 - 7;

			// Disable depth testing and enable blending to ensure the text is rendered
			// above the item
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			font.drawString(String.valueOf(itemAmount), itemX + 7, itemY, -1);

			// Re-enable depth testing
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}

	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
		renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
		renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
		renderItemStack(pos, 0, new ItemStack(Items.diamond_boots));
		renderItemStack(pos, -1, new ItemStack(Items.diamond_sword));
	}

	private void renderItemStack(ScreenPosition pos, int i, ItemStack is) {

		if (is == null) {
			return;
		}

		GL11.glPushMatrix();

		int yAdd = (-16 * (i + 1)) + 64;

		RenderHelper.enableGUIStandardItemLighting();
		mc.getRenderItem().func_180450_b(is, pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd); // renderItemAndEffectIntoGUI();

		if (is.getItem().isDamageable()) {
			if (is.getItemDamage() != 0) {
				int damage = is.getMaxDamage() - is.getItemDamage();
				int maxDamage = is.getMaxDamage();
				float damageRatio = (float) damage / maxDamage;
				int barWidth = (int) Math.round(damageRatio * 13.0);
				int barColor = Math.round(damageRatio * 255.0F);
				int redAmount = 255 - barColor;

				GL11.glDisable(GL11.GL_DEPTH_TEST);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				GL11.glDisable(GL11.GL_BLEND);

				// Draw the background bar (black)
				GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
				drawTexturedModalRect((float) pos.getAbsoluteX() + 1.5f, pos.getAbsoluteY() + yAdd + 15, 13, 2);

				// Draw the foreground bar (interpolated color)
				GL11.glColor4f(redAmount / 255.0F, barColor / 255.0F, 0.0F, 1.0F);
				drawTexturedModalRect((float) pos.getAbsoluteX() + 1.5f, pos.getAbsoluteY() + yAdd + 15, barWidth, 1);

				GL11.glEnable(GL11.GL_BLEND);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
			}
		}

		GL11.glPopMatrix();
	}

	private void drawTexturedModalRect(float x, int y, int width, int height) {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x, y + height);
		GL11.glVertex2f(x + width, y + height);
		GL11.glVertex2f(x + width, y);
		GL11.glEnd();
	}

	@Override
	public int getOffsetX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOffsetY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
