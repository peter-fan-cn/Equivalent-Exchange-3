package com.pahimar.ee3.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Equivalent-Exchange-3
 * <p/>
 * SlotCalcinator
 *
 * @author pahimar
 */
public class SlotCalcinator extends Slot
{
    public SlotCalcinator(IInventory inventory, int x, int y, int z)
    {
        super(inventory, x, y, z);
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }
}
