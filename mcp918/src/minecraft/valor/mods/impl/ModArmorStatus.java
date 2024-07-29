package valor.mods.impl;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.client.Minecraft;
import valor.gui.hud.ScreenPosition;
import valor.mods.ModDraggable;

public class ModArmorStatus extends ModDraggable {
    
    private ScreenPosition pos = ScreenPosition.fromRelativePosition(0.5, 0.5);

    @Override
    public int getWidth() {
        return 64;
    }

    @Override
    public int getHeight() {
        return 64;
    }

    @Override
    public void render(ScreenPosition pos) {
        for(int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
            ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
            renderItemStack(pos, i, itemStack);
        }
    }
    
    @Override
    public void renderDummy(ScreenPosition pos) {
        renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
        renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
        renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
        renderItemStack(pos, 0, new ItemStack(Items.diamond_boots));
    }

    private void renderItemStack(ScreenPosition pos, int i, ItemStack is) {

        if (is == null) {
            return;
        }
        
        GL11.glPushMatrix();
        
        int yAdd = (-16 * i) + 48;

        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().func_180450_b(is, pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd); // renderItemAndEffectIntoGUI();
        
        if (is.getItem().isDamageable()) {
        	if(is.getItemDamage() != 0) {
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
                drawTexturedModalRect((float)pos.getAbsoluteX() + 1.5f, pos.getAbsoluteY() + yAdd + 15, 13, 2);
                
                // Draw the foreground bar (interpolated color)
                GL11.glColor4f(redAmount / 255.0F, barColor / 255.0F, 0.0F, 1.0F);
                drawTexturedModalRect((float)pos.getAbsoluteX() + 1.5f, pos.getAbsoluteY() + yAdd + 15, barWidth, 1);
                
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
    public void save(ScreenPosition pos) {
        this.pos = pos;
    }

    @Override
    public ScreenPosition load() {
        return pos;
    }
}