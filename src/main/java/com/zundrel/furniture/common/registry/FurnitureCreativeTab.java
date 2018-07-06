package com.zundrel.furniture.common.registry;

import javax.annotation.Nonnull;

import com.zundrel.furniture.Furniture;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class FurnitureCreativeTab extends CreativeTabs {
	public static FurnitureCreativeTab INSTANCE = new FurnitureCreativeTab();
	NonNullList<ItemStack> list;

	private FurnitureCreativeTab() {
		super(Furniture.MOD_ID);
	}

	@Override
	@Nonnull
	public ItemStack getTabIconItem() {
		return new ItemStack(BlockRegistry.table_oak);
	}

	@Override
	public void displayAllRelevantItems(@Nonnull NonNullList<ItemStack> list) {
		this.list = list;

		this.addBlock(BlockRegistry.table_oak);
	}

	private void addItem(Item item) {
		item.getSubItems(this, list);
	}

	private void addBlock(Block block) {
		block.getSubBlocks(this, list);
	}
}