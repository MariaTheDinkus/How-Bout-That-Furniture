package com.momnop.furniture.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FurnitureBlocks
{
	public static Block sofa;
	public static Block tableOak, tableSpruce, tableBirch, tableJungle, tableAcacia, tableDarkOak;
	public static Block coffeeTableOak, coffeeTableSpruce, coffeeTableBirch, coffeeTableJungle, coffeeTableAcacia, coffeeTableDarkOak;
	public static Block blinds;
	public static Block chairOak, chairSpruce, chairBirch, chairJungle, chairAcacia, chairDarkOak;;
	public static Block stoolOak, stoolSpruce, stoolBirch, stoolJungle, stoolAcacia, stoolDarkOak;
	public static Block doorbell;
	public static Block lightSwitch;
	public static Block fancyFence;
	public static Block rockPath;
	public static Block desk, deskInvisible1, deskInvisible2;
	
	public static void load() {
		loadBlocks();
	}
	
    public static void loadBlocks()
    {
    	sofa = new BlockSofa(Material.CLOTH, 0.8F, SoundType.CLOTH, "sofa");
    	
    	chairOak = new BlockChair(Material.WOOD, 2.0F, SoundType.WOOD, "chair_oak");
    	chairSpruce = new BlockChair(Material.WOOD, 2.0F, SoundType.WOOD, "chair_spruce");
    	chairBirch = new BlockChair(Material.WOOD, 2.0F, SoundType.WOOD, "chair_birch");
    	chairJungle = new BlockChair(Material.WOOD, 2.0F, SoundType.WOOD, "chair_jungle");
    	chairAcacia = new BlockChair(Material.WOOD, 2.0F, SoundType.WOOD, "chair_acacia");
    	chairDarkOak = new BlockChair(Material.WOOD, 2.0F, SoundType.WOOD, "chair_dark_oak");
    	
    	stoolOak = new BlockStool(Material.WOOD, 2.0F, SoundType.WOOD, "stool_oak");
    	stoolSpruce = new BlockStool(Material.WOOD, 2.0F, SoundType.WOOD, "stool_spruce");
    	stoolBirch = new BlockStool(Material.WOOD, 2.0F, SoundType.WOOD, "stool_birch");
    	stoolJungle = new BlockStool(Material.WOOD, 2.0F, SoundType.WOOD, "stool_jungle");
    	stoolAcacia = new BlockStool(Material.WOOD, 2.0F, SoundType.WOOD, "stool_acacia");
    	stoolDarkOak = new BlockStool(Material.WOOD, 2.0F, SoundType.WOOD, "stool_dark_oak");
    	
    	tableOak = new BlockTable(Material.WOOD, 2.0F, SoundType.WOOD, "table_oak");
    	tableSpruce = new BlockTable(Material.WOOD, 2.0F, SoundType.WOOD, "table_spruce");
    	tableBirch = new BlockTable(Material.WOOD, 2.0F, SoundType.WOOD, "table_birch");
    	tableJungle = new BlockTable(Material.WOOD, 2.0F, SoundType.WOOD, "table_jungle");
    	tableAcacia = new BlockTable(Material.WOOD, 2.0F, SoundType.WOOD, "table_acacia");
    	tableDarkOak = new BlockTable(Material.WOOD, 2.0F, SoundType.WOOD, "table_dark_oak");
    	
    	coffeeTableOak = new BlockCoffeeTable(Material.WOOD, 2.0F, SoundType.WOOD, "coffee_table_oak");
    	coffeeTableSpruce = new BlockCoffeeTable(Material.WOOD, 2.0F, SoundType.WOOD, "coffee_table_spruce");
    	coffeeTableBirch = new BlockCoffeeTable(Material.WOOD, 2.0F, SoundType.WOOD, "coffee_table_birch");
    	coffeeTableJungle = new BlockCoffeeTable(Material.WOOD, 2.0F, SoundType.WOOD, "coffee_table_jungle");
    	coffeeTableAcacia = new BlockCoffeeTable(Material.WOOD, 2.0F, SoundType.WOOD, "coffee_table_acacia");
    	coffeeTableDarkOak = new BlockCoffeeTable(Material.WOOD, 2.0F, SoundType.WOOD, "coffee_table_dark_oak");
    	
    	blinds = new BlockBlinds(Material.WOOD, 2.0F, SoundType.WOOD, "blinds");
    	
    	doorbell = new BlockDoorbell(Material.WOOD, 2.0F, SoundType.WOOD, "doorbell");
    	lightSwitch = new BlockLightSwitch(Material.WOOD, 2.0F, SoundType.WOOD, "light_switch");
    	
    	fancyFence = new BlockFurnitureFence(Material.WOOD, 2.0F, SoundType.WOOD, "fancy_fence");
    	
    	rockPath = new BlockRockPath(Material.ROCK, 1.5F, SoundType.STONE, "rock_path");
    	
    	//desk = new BlockDesk(Material.WOOD, 2.0F, SoundType.WOOD, "desk");
    	//deskInvisible1 = new BlockDesk(Material.WOOD, 2.0F, SoundType.WOOD, "desk_invisible_1");
    	//deskInvisible2 = new BlockDesk(Material.WOOD, 2.0F, SoundType.WOOD, "desk_invisible_2");
    }
    
    public static void register(Block b) {
    	ItemBlock ib = new ItemBlock(b);
		GameRegistry.register(b);
		ib.setRegistryName(b.getRegistryName());
		GameRegistry.register(ib);
	}
}