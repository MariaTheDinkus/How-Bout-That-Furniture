package com.momnop.furniture;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import com.momnop.furniture.blocks.FurnitureBlocks;
import com.momnop.furniture.info.ModInfo;

public class FurnitureCreativeTab extends CreativeTabs {

	List list;
	public static FurnitureCreativeTab INSTANCE = new FurnitureCreativeTab();

	public FurnitureCreativeTab() {
		super(ModInfo.MODID);
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(FurnitureBlocks.sofa);
	}

	@Override
	public ItemStack getTabIconItem() {
		return getIconItemStack();
	}

	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> list) {
		this.list = list;
		
		addBlock(FurnitureBlocks.sofa);
		
		addBlock(FurnitureBlocks.chairOak);
		addBlock(FurnitureBlocks.chairSpruce);
		addBlock(FurnitureBlocks.chairBirch);
		addBlock(FurnitureBlocks.chairJungle);
		addBlock(FurnitureBlocks.chairAcacia);
		addBlock(FurnitureBlocks.chairDarkOak);
		
		addBlock(FurnitureBlocks.stoolOak);
		addBlock(FurnitureBlocks.stoolSpruce);
		addBlock(FurnitureBlocks.stoolBirch);
		addBlock(FurnitureBlocks.stoolJungle);
		addBlock(FurnitureBlocks.stoolAcacia);
		addBlock(FurnitureBlocks.stoolDarkOak);
		
		addBlock(FurnitureBlocks.tableOak);
		addBlock(FurnitureBlocks.tableSpruce);
		addBlock(FurnitureBlocks.tableBirch);
		addBlock(FurnitureBlocks.tableJungle);
		addBlock(FurnitureBlocks.tableAcacia);
		addBlock(FurnitureBlocks.tableDarkOak);
		
		addBlock(FurnitureBlocks.coffeeTableOak);
		addBlock(FurnitureBlocks.coffeeTableSpruce);
		addBlock(FurnitureBlocks.coffeeTableBirch);
		addBlock(FurnitureBlocks.coffeeTableJungle);
		addBlock(FurnitureBlocks.coffeeTableAcacia);
		addBlock(FurnitureBlocks.coffeeTableDarkOak);
		
		addBlock(FurnitureBlocks.blinds);
		
		addBlock(FurnitureBlocks.doorbell);
		
		addBlock(FurnitureBlocks.lightSwitch);
		
		addBlock(FurnitureBlocks.fancyFence);
		
		addBlock(FurnitureBlocks.rockPath);
	}

	private void addItem(Item item) {
		item.getSubItems(item, this, (NonNullList<ItemStack>) list);
	}

	private void addBlock(Block block) {
		ItemStack stack = new ItemStack(block);
		block.getSubBlocks(stack.getItem(), this, (NonNullList<ItemStack>) list);
	}

}