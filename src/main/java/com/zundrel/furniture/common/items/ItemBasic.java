package com.zundrel.furniture.common.items;

import com.zundrel.furniture.common.registry.FurnitureCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBasic extends Item {
	public ItemBasic(String unlocalizedName) {
		setRegistryName(unlocalizedName);
		setUnlocalizedName(this.getRegistryName().toString());

		setCreativeTab(FurnitureCreativeTab.INSTANCE);
	}

	public ItemBasic(String unlocalizedName, CreativeTabs tab) {
		setRegistryName(unlocalizedName);
		setUnlocalizedName(this.getRegistryName().toString());

		setCreativeTab(tab);
	}
}