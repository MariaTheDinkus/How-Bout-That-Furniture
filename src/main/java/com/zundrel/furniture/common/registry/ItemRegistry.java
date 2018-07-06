package com.zundrel.furniture.common.registry;

import java.util.ArrayList;

import com.zundrel.furniture.Furniture;
import com.zundrel.furniture.client.registry.ModelRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = Furniture.MOD_ID)
public class ItemRegistry {
	public static IForgeRegistry<Item> registry;
	public static ArrayList<ItemBlock> itemBlocks = new ArrayList<ItemBlock>();

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		registry = event.getRegistry();

		itemBlocks.forEach((ib) -> {
			registry.register(ib);
		});
	}

	public static <T extends Item> T register(T i) {
		registry.register(i);
		/*
		 * if (i instanceof ItemBasic) { ((ItemBasic) i).registerModel(i); }
		 */
		ModelRegistry.modelList.add(i);
		return i;
	}
}