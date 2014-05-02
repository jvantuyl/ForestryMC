/*******************************************************************************
 * Copyright 2011-2014 by SirSengir
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/.
 ******************************************************************************/
package forestry.core.gui;

import net.minecraft.entity.player.InventoryPlayer;

import forestry.core.config.Defaults;
import forestry.core.gadgets.TileAnalyzer;
import forestry.core.gui.widgets.TankWidget;
import forestry.core.utils.EnumTankLevel;
import forestry.core.utils.StringUtil;
import forestry.core.utils.Utils;

public class GuiAnalyzer extends GuiForestry<TileAnalyzer> {

	public GuiAnalyzer(InventoryPlayer inventory, TileAnalyzer tile) {
		super(Defaults.TEXTURE_PATH_GUI + "/alyzer.png", new ContainerAnalyzer(inventory, tile), tile);
		ySize = 176;
		widgetManager.add(new TankWidget(this.widgetManager, 95, 24, 0));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		String title = StringUtil.localize("tile.for." + tile.getInventoryName());
		this.fontRendererObj.drawString(title, getCenteredOffset(title), 6, fontColor.get("gui.title"));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(var1, mouseX, mouseY);
		TileAnalyzer machine = tile;
		drawAnalyzeMeter(guiLeft + 64, guiTop + 30, machine.getProgressScaled(46), Utils.rateTankLevel(machine.getProgressScaled(100)));

	}

	private void drawAnalyzeMeter(int x, int y, int height, EnumTankLevel rated) {
		int i = 176;
		int k = 60;
		switch (rated) {
		case EMPTY:
			break;
		case LOW:
			i += 4;
			break;
		case MEDIUM:
			i += 8;
			break;
		case HIGH:
			i += 12;
			break;
		case MAXIMUM:
			i += 16;
			break;
		}

		drawTexturedModalRect(x, y + 46 - height, i, k + 46 - height, 4, height);
	}
}