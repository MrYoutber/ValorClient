package valor.mods.impl;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.biome.BiomeGenBase;
import valor.gui.hud.ScreenPosition;
import valor.mods.ModDraggable;

public class ModXYZ extends ModDraggable {

	private int offsetX = 5, offsetY = 5;
	private int lines = 4;

	@Override
	public int getWidth() {
		return font.getStringWidth("Biome: " + getBiomeName());
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT * lines;
	}

	@Override
	public int getOffsetX() {
		return offsetX;
	}

	@Override
	public int getOffsetY() {
		return offsetY;
	}

	@Override
	public void render(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX() - getOffsetX(), pos.getAbsoluteY() - getOffsetY(),
				pos.getAbsoluteX() + getWidth() + getOffsetX(), pos.getAbsoluteY() + getHeight() + getOffsetY(),
				new Color(0, 0, 0, 120).getRGB());
		font.drawString("X: " + getXCoord().intValue(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
		font.drawString("Y: " + getYCoord().intValue(), pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT, -1);
		font.drawString("Z: " + getZCoord().intValue(), pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT * 2,
				-1);
		font.drawString("Biome: ", pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT * 3, -1);
		font.drawString(getBiomeName(), pos.getAbsoluteX() + font.getStringWidth("Biome: "),
				pos.getAbsoluteY() + font.FONT_HEIGHT * 3, getBiomeColor());
	}

	private Double getZCoord() {
		return Minecraft.getMinecraft().thePlayer.posX;
	}

	private Double getYCoord() {
		return Minecraft.getMinecraft().thePlayer.posY;
	}

	private Double getXCoord() {
		return Minecraft.getMinecraft().thePlayer.posZ;
	}

	private BiomeGenBase getBiome() {
		BiomeGenBase biome = Minecraft.getMinecraft().thePlayer.worldObj
				.getBiomeGenForCoords(Minecraft.getMinecraft().thePlayer.getPosition());
		return biome;
	}

	private String getBiomeName() {
		String name = getBiome().biomeName;
		return name;
	}

	private int getBiomeColor() {
		int color = getBiome().color;
		return color;
	}

}
