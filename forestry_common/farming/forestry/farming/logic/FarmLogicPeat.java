/*******************************************************************************
 * Copyright 2011-2014 by SirSengir
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/.
 ******************************************************************************/
package forestry.farming.logic;

import java.util.Collection;
import java.util.Stack;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.util.ForgeDirection;

import forestry.api.farming.ICrop;
import forestry.api.farming.IFarmHousing;
import forestry.core.config.ForestryBlock;
import forestry.core.config.ForestryItem;
import forestry.core.utils.StackUtils;
import forestry.core.utils.Vect;

public class FarmLogicPeat extends FarmLogicWatered {

	public FarmLogicPeat(IFarmHousing housing) {
		super(housing, new ItemStack[] { new ItemStack(ForestryBlock.soil, 1, 1) },
				new ItemStack[] { new ItemStack(ForestryBlock.soil, 1, 1) }, new ItemStack[] { new ItemStack(Blocks.dirt), new ItemStack(Blocks.grass) });
	}

	@Override
	public int getFertilizerConsumption() {
		return 2;
	}

	@Override
	public String getName() {
		if (isManual)
			return "Manual Peat Bog";
		else
			return "Managed Peat Bog";
	}

	@Override
	public boolean isAcceptedGermling(ItemStack itemstack) {
		return false;
	}

	@Override
	public Collection<ICrop> harvest(int x, int y, int z, ForgeDirection direction, int extent) {

		world = housing.getWorld();

		Stack<ICrop> crops = new Stack<ICrop>();
		for (int i = 0; i < extent; i++) {
			Vect position = translateWithOffset(x, y, z, direction, i);
			ItemStack occupant = getAsItemStack(position);

			if (StackUtils.getBlock(occupant) != ForestryBlock.soil)
				continue;
			int type = occupant.getItemDamage() & 0x03;
			int maturity = occupant.getItemDamage() >> 2;

		if (type != 1)
			continue;

		if (maturity >= 3)
			crops.push(new CropPeat(world, position));

		}
		return crops;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon() {
		return ForestryItem.peat.item().getIconFromDamage(0);
	}

}