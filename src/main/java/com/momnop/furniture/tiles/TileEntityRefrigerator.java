package com.momnop.furniture.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TileEntityRefrigerator extends TileEntityChest {
	
	@Override
	public void update() {
		markDirty();
	}
	
	@Override
	public double getMaxRenderDistanceSquared() {
		return 0;
	}
	
	@Override
	public boolean shouldRenderInPass(int pass) {
		return false;
	}
	
	@Override
	public String getName() {
		return "Refrigerator";
	}
	
	@Override
	public boolean hasCustomName() {
		return false;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
		
	}
	
	@Override
	public void closeInventory(EntityPlayer player) {
		
	}
}
