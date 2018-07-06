package com.zundrel.furniture.common.registry;

import com.zundrel.furniture.Furniture;
import com.zundrel.furniture.api.EnumConnection;
import com.zundrel.furniture.client.registry.ModelRegistry;
import com.zundrel.furniture.common.blocks.BlockBasic;
import com.zundrel.furniture.common.blocks.BlockConnectingPillars;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = Furniture.MOD_ID)
public class BlockRegistry {
	public static IForgeRegistry<Block> registry;

	public static Block table_oak;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		registry = event.getRegistry();

		register(table_oak = new BlockConnectingPillars("table_oak", Material.ROCK, EnumConnection.SAME));
	}

	public static <T extends Block> T register(T b, ItemBlock ib) {
		registry.register(b);
		ib.setRegistryName(b.getRegistryName());
		ItemRegistry.itemBlocks.add(ib);
		ModelRegistry.modelList.add(ib);
		return b;
	}

	public static <T extends Block> T register(T b) {
		ItemBlock ib = new ItemBlock(b);
		return register(b, ib);
	}
}