package com.cricketcraft.chisel.client.gui;

import com.cricketcraft.chisel.api.IChiselItem;
import com.cricketcraft.chisel.inventory.ContainerChisel;
import com.cricketcraft.chisel.inventory.InventoryChiselSelection;
import com.cricketcraft.chisel.inventory.slots.SlotChiselInput;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiChisel extends GuiContainer {

	public EntityPlayer player;

	public ContainerChisel container;

	public GuiChisel(InventoryPlayer iinventory, InventoryChiselSelection menu) {
		super(new ContainerChisel(iinventory, menu));
		player = iinventory.player;
		xSize = 252;
		ySize = 202;
		container = (ContainerChisel) inventorySlots;
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		inventorySlots.onContainerClosed(player);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();

		if (showMode()) {
			int x = this.width / 2 - 120;
			int y = this.height / 2 - 6;
			buttonList.add(new GuiButton(0, x, y, 53, 20, ""));
			setButtonText();
		}
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		ItemStack held = player.getCurrentEquippedItem();
		if (held == null || !(held.getItem() instanceof IChiselItem)) {
			mc.displayGuiScreen(null);
		}
	}

	private void setButtonText() {
		((GuiButton) buttonList.get(0)).displayString = I18n.format(container.inventory.getName() + ".mode.");//+ currentMode.name().toLowerCase());
	}

	private boolean showMode() {
		if (container.chisel != null && container.chisel.getItem() instanceof IChiselItem) {
			return ((IChiselItem) container.chisel.getItem()).hasModes(container.chisel);
		}
		return false;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int j, int i) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		String line = I18n.format(StatCollector.translateToLocal("chisel.gui.title"));
		fontRendererObj.drawSplitString(line, 50 - fontRendererObj.getStringWidth(line) / 2, 60, 40, 0x404040);

		if (showMode()) {
			line = I18n.format(StatCollector.translateToLocal("chisel.gui.mode"));
			fontRendererObj.drawString(line, fontRendererObj.getStringWidth(line) / 2 + 6, 85, 0x404040);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mx, int my) {
		drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		int i = width - xSize >> 1;
		int j = height - ySize >> 1;

		String texture = "chisel:textures/chisel2Gui.png";

		drawTexturedModalRect(i, j, 0, 0, xSize, ySize);

		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;

		Slot main = (Slot) container.inventorySlots.get(InventoryChiselSelection.normalSlots);
		if (main.getStack() == null) {
			//GuiAutoChisel.drawSlotOverlay(this, x + 14, y + 14, main, 0, ySize, 60);
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
	}

	@Override
	protected void drawSlot(Slot slot) {
		if (slot instanceof SlotChiselInput) {
			GL11.glPushMatrix();
			GL11.glScalef(2, 2, 2);
			slot.xDisplayPosition -= 16;
			slot.yDisplayPosition -= 16;
			super.drawSlot(slot);
			slot.xDisplayPosition += 16;
			slot.yDisplayPosition += 16;
			GL11.glPopMatrix();
		} else {
			super.drawSlot(slot);
		}
	}
}
