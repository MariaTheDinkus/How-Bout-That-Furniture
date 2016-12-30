package com.momnop.furniture.blocks;

import com.momnop.furniture.Furniture;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
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
	public static Block fan;
	public static Block counterStone, counterGranite, counterDiorite, counterAndesite;
	public static Block counterStoneQuartz, counterGraniteQuartz, counterDioriteQuartz, counterAndesiteQuartz;
	public static Block counterSinkStone, counterSinkGranite, counterSinkDiorite, counterSinkAndesite;
	public static Block counterSinkStoneQuartz, counterSinkGraniteQuartz, counterSinkDioriteQuartz, counterSinkAndesiteQuartz;
	public static Block sink;
	public static Block refrigerator, fridgeInvis;
	public static Block darkOakOakPlankLog;
	public static Block lightBulbOff, lightBulbOn;
	public static Block fanLightOff, fanLightOn;
	public static Block cabinetOak, cabinetSpruce, cabinetBirch, cabinetJungle, cabinetAcacia, cabinetDarkOak;
	
	public static final CreativeTabs general = Furniture.tabGeneral;
	public static final CreativeTabs living = Furniture.tabLiving;
	public static final CreativeTabs kitchen = Furniture.tabKitchen;
	public static final CreativeTabs outdoors = Furniture.tabOutdoors;
	
	public static void load() {
		loadBlocks();
	}
	
    public static void loadBlocks()
    {
    	sofa = new BlockSofa(Material.CLOTH, 0.8F, SoundType.CLOTH, "sofa", living);
    	
    	chairOak = new BlockChair(Material.WOOD, 2.0F, SoundType.WOOD, "chair_oak", general);
    	chairSpruce = new BlockChair(Material.WOOD, 2.0F, SoundType.WOOD, "chair_spruce", general);
    	chairBirch = new BlockChair(Material.WOOD, 2.0F, SoundType.WOOD, "chair_birch", general);
    	chairJungle = new BlockChair(Material.WOOD, 2.0F, SoundType.WOOD, "chair_jungle", general);
    	chairAcacia = new BlockChair(Material.WOOD, 2.0F, SoundType.WOOD, "chair_acacia", general);
    	chairDarkOak = new BlockChair(Material.WOOD, 2.0F, SoundType.WOOD, "chair_dark_oak", general);
    	
    	stoolOak = new BlockStool(Material.WOOD, 2.0F, SoundType.WOOD, "stool_oak", general);
    	stoolSpruce = new BlockStool(Material.WOOD, 2.0F, SoundType.WOOD, "stool_spruce", general);
    	stoolBirch = new BlockStool(Material.WOOD, 2.0F, SoundType.WOOD, "stool_birch", general);
    	stoolJungle = new BlockStool(Material.WOOD, 2.0F, SoundType.WOOD, "stool_jungle", general);
    	stoolAcacia = new BlockStool(Material.WOOD, 2.0F, SoundType.WOOD, "stool_acacia", general);
    	stoolDarkOak = new BlockStool(Material.WOOD, 2.0F, SoundType.WOOD, "stool_dark_oak", general);
    	
    	tableOak = new BlockTable(Material.WOOD, 2.0F, SoundType.WOOD, "table_oak", general);
    	tableSpruce = new BlockTable(Material.WOOD, 2.0F, SoundType.WOOD, "table_spruce", general);
    	tableBirch = new BlockTable(Material.WOOD, 2.0F, SoundType.WOOD, "table_birch", general);
    	tableJungle = new BlockTable(Material.WOOD, 2.0F, SoundType.WOOD, "table_jungle", general);
    	tableAcacia = new BlockTable(Material.WOOD, 2.0F, SoundType.WOOD, "table_acacia", general);
    	tableDarkOak = new BlockTable(Material.WOOD, 2.0F, SoundType.WOOD, "table_dark_oak", general);
    	
    	coffeeTableOak = new BlockCoffeeTable(Material.WOOD, 2.0F, SoundType.WOOD, "coffee_table_oak", living);
    	coffeeTableSpruce = new BlockCoffeeTable(Material.WOOD, 2.0F, SoundType.WOOD, "coffee_table_spruce", living);
    	coffeeTableBirch = new BlockCoffeeTable(Material.WOOD, 2.0F, SoundType.WOOD, "coffee_table_birch", living);
    	coffeeTableJungle = new BlockCoffeeTable(Material.WOOD, 2.0F, SoundType.WOOD, "coffee_table_jungle", living);
    	coffeeTableAcacia = new BlockCoffeeTable(Material.WOOD, 2.0F, SoundType.WOOD, "coffee_table_acacia", living);
    	coffeeTableDarkOak = new BlockCoffeeTable(Material.WOOD, 2.0F, SoundType.WOOD, "coffee_table_dark_oak", living);
    	
    	blinds = new BlockBlinds(Material.WOOD, 2.0F, SoundType.WOOD, "blinds", general);
    	
    	doorbell = new BlockDoorbell(Material.WOOD, 2.0F, SoundType.WOOD, "doorbell", general);
    	lightSwitch = new BlockLightSwitch(Material.WOOD, 2.0F, SoundType.WOOD, "light_switch", general);
    	
    	fancyFence = new BlockFurnitureFence(Material.WOOD, 2.0F, SoundType.WOOD, "fancy_fence", outdoors);
    	
    	rockPath = new BlockRockPath(Material.ROCK, 1.5F, SoundType.STONE, "rock_path", outdoors);
    	
    	counterStone = new BlockFurnitureConnecting(Material.WOOD, 2.0F, SoundType.WOOD, "counter_stone", kitchen);
    	counterGranite = new BlockFurnitureConnecting(Material.WOOD, 2.0F, SoundType.WOOD, "counter_granite", kitchen);
    	counterDiorite = new BlockFurnitureConnecting(Material.WOOD, 2.0F, SoundType.WOOD, "counter_diorite", kitchen);
    	counterAndesite = new BlockFurnitureConnecting(Material.WOOD, 2.0F, SoundType.WOOD, "counter_andesite", kitchen);
    	
    	counterStoneQuartz = new BlockFurnitureConnecting(Material.ROCK, 1.5F, SoundType.STONE, "counter_stone_quartz", kitchen);
    	counterGraniteQuartz = new BlockFurnitureConnecting(Material.ROCK, 1.5F, SoundType.STONE, "counter_granite_quartz", kitchen);
    	counterDioriteQuartz = new BlockFurnitureConnecting(Material.ROCK, 1.5F, SoundType.STONE, "counter_diorite_quartz", kitchen);
    	counterAndesiteQuartz = new BlockFurnitureConnecting(Material.ROCK, 1.5F, SoundType.STONE, "counter_andesite_quartz", kitchen);
    	
    	counterSinkStone = new BlockCounterSink(Material.WOOD, 2.0F, SoundType.WOOD, "counter_sink_stone", kitchen);
    	counterSinkGranite = new BlockCounterSink(Material.WOOD, 2.0F, SoundType.WOOD, "counter_sink_granite", kitchen);
    	counterSinkDiorite = new BlockCounterSink(Material.WOOD, 2.0F, SoundType.WOOD, "counter_sink_diorite", kitchen);
    	counterSinkAndesite = new BlockCounterSink(Material.WOOD, 2.0F, SoundType.WOOD, "counter_sink_andesite", kitchen);
    	
    	counterSinkStoneQuartz = new BlockCounterSink(Material.ROCK, 1.5F, SoundType.STONE, "counter_sink_stone_quartz", kitchen);
    	counterSinkGraniteQuartz = new BlockCounterSink(Material.ROCK, 1.5F, SoundType.STONE, "counter_sink_granite_quartz", kitchen);
    	counterSinkDioriteQuartz = new BlockCounterSink(Material.ROCK, 1.5F, SoundType.STONE, "counter_sink_diorite_quartz", kitchen);
    	counterSinkAndesiteQuartz = new BlockCounterSink(Material.ROCK, 1.5F, SoundType.STONE, "counter_sink_andesite_quartz", kitchen);
    	
    	fan = new BlockFan(Material.WOOD, 2.0F, SoundType.WOOD, "fan", general);
    	
    	refrigerator = new BlockRefrigerator(Material.ROCK, 1.5F, SoundType.STONE, "refrigerator", kitchen);
    	fridgeInvis = new BlockRefrigeratorInvisible(Material.ROCK, 1.5F, SoundType.STONE, "refrigerator_invisible", new ItemStack(refrigerator), null);
    	
    	darkOakOakPlankLog = new BlockNormal(Material.WOOD, 2.0F, SoundType.WOOD, "dark_oak_oak_plank_log", general);
    	
    	sink = new BlockSink(Material.ROCK, 1.5F, SoundType.STONE, "sink", kitchen);
    	
    	lightBulbOff = new BlockLight(Material.WOOD, 2.0F, SoundType.WOOD, "lightbulb_off", false, general);
    	lightBulbOn = new BlockLight(Material.WOOD, 2.0F, SoundType.WOOD, "lightbulb_on", true, null);
    	
    	fanLightOff = new BlockFanLight(Material.WOOD, 2.0F, SoundType.WOOD, "fan_light_off", false, general);
    	fanLightOn = new BlockFanLight(Material.WOOD, 2.0F, SoundType.WOOD, "fan_light_on", true, null);
    	
    	cabinetOak = new BlockCabinet(Material.WOOD, 2.0F, SoundType.WOOD, "cabinet_oak", kitchen);
    	cabinetSpruce = new BlockCabinet(Material.WOOD, 2.0F, SoundType.WOOD, "cabinet_spruce", kitchen);
    	cabinetBirch = new BlockCabinet(Material.WOOD, 2.0F, SoundType.WOOD, "cabinet_birch", kitchen);
    	cabinetJungle = new BlockCabinet(Material.WOOD, 2.0F, SoundType.WOOD, "cabinet_jungle", kitchen);
    	cabinetAcacia = new BlockCabinet(Material.WOOD, 2.0F, SoundType.WOOD, "cabinet_acacia", kitchen);
    	cabinetDarkOak = new BlockCabinet(Material.WOOD, 2.0F, SoundType.WOOD, "cabinet_dark_oak", kitchen);
    }
    
    public static void register(Block b) {
    	ItemBlock ib = new ItemBlock(b);
		GameRegistry.register(b);
		ib.setRegistryName(b.getRegistryName());
		GameRegistry.register(ib);
	}
}