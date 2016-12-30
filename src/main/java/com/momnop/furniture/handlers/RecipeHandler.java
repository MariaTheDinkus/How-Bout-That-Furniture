package com.momnop.furniture.handlers;

import net.minecraft.block.BlockOldLog;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.momnop.furniture.blocks.FurnitureBlocks;

/**
 * Handles the recipes
 */
public class RecipeHandler
{
	public int woolMeta[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

    /**
     * Registers the mod's recipes
     */
    public static void doRecipes()
    {		
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.sofa, 1), "WWW", "WWW", 'W', new ItemStack(Blocks.WOOL, 1, 0));
    	
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.chairOak, 1), "Wc ", "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 0), 'c', new ItemStack(Blocks.CARPET, 1, 0));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.chairSpruce, 1), "Wc ", "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 1), 'c', new ItemStack(Blocks.CARPET, 1, 0));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.chairBirch, 1), "Wc ", "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 2), 'c', new ItemStack(Blocks.CARPET, 1, 0));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.chairJungle, 1), "Wc ", "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 3), 'c', new ItemStack(Blocks.CARPET, 1, 0));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.chairAcacia, 1), "Wc ", "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 4), 'c', new ItemStack(Blocks.CARPET, 1, 0));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.chairDarkOak, 1), "Wc ", "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 5), 'c', new ItemStack(Blocks.CARPET, 1, 0));
    	
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.stoolOak, 1), " c ", "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 0), 'c', new ItemStack(Blocks.CARPET, 1, 0));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.stoolSpruce, 1), " c ", "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 1), 'c', new ItemStack(Blocks.CARPET, 1, 0));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.stoolBirch, 1), " c ", "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 2), 'c', new ItemStack(Blocks.CARPET, 1, 0));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.stoolJungle, 1), " c ", "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 3), 'c', new ItemStack(Blocks.CARPET, 1, 0));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.stoolAcacia, 1), " c ", "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 4), 'c', new ItemStack(Blocks.CARPET, 1, 0));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.stoolDarkOak, 1), " c ", "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 5), 'c', new ItemStack(Blocks.CARPET, 1, 0));
    	
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.coffeeTableOak, 1), "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 0));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.coffeeTableSpruce, 1), "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 1));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.coffeeTableBirch, 1), "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 2));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.coffeeTableJungle, 1), "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 3));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.coffeeTableAcacia, 1), "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 4));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.coffeeTableDarkOak, 1), "WWW", "W W", 'W', new ItemStack(Blocks.PLANKS, 1, 5));
    	
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.tableOak, 1), "WWW", " W ", " W ", 'W', new ItemStack(Blocks.PLANKS, 1, 0));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.tableSpruce, 1), "WWW", " W ", " W ", 'W', new ItemStack(Blocks.PLANKS, 1, 1));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.tableBirch, 1), "WWW", " W ", " W ", 'W', new ItemStack(Blocks.PLANKS, 1, 2));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.tableJungle, 1), "WWW", " W ", " W ", 'W', new ItemStack(Blocks.PLANKS, 1, 3));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.tableAcacia, 1), "WWW", " W ", " W ", 'W', new ItemStack(Blocks.PLANKS, 1, 4));
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.tableDarkOak, 1), "WWW", " W ", " W ", 'W', new ItemStack(Blocks.PLANKS, 1, 5));
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(FurnitureBlocks.doorbell), new Object[] { Blocks.NOTEBLOCK, Blocks.STONE_BUTTON });
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(FurnitureBlocks.fancyFence, 2), new Object[] { Blocks.OAK_FENCE, new ItemStack(Items.DYE, 1, 15) });
    	
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.rockPath, 8), new Object[] { "SS", 'S', Blocks.COBBLESTONE });
    	
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.blinds, 2), "WWW", "WWW", "WWW", 'W', Items.STICK);
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(FurnitureBlocks.lightSwitch, 1), new Object[] { Blocks.LEVER, new ItemStack(Items.DYE, 1, 15) });
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(FurnitureBlocks.darkOakOakPlankLog, 2), new Object[] { new ItemStack(Blocks.LOG, 1, 0), new ItemStack(Blocks.PLANKS, 1, 5) });
    	
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.counterStone, 8), "SSS", "QQQ", "QQQ", 'S', new ItemStack(Blocks.STONE, 1, 0), 'Q', FurnitureBlocks.darkOakOakPlankLog);
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.counterGranite, 8), "SSS", "QQQ", "QQQ", 'S', new ItemStack(Blocks.STONE, 1, 1), 'Q', FurnitureBlocks.darkOakOakPlankLog);
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.counterDiorite, 8), "SSS", "QQQ", "QQQ", 'S', new ItemStack(Blocks.STONE, 1, 3), 'Q', FurnitureBlocks.darkOakOakPlankLog);
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.counterAndesite, 8), "SSS", "QQQ", "QQQ", 'S', new ItemStack(Blocks.STONE, 1, 5), 'Q', FurnitureBlocks.darkOakOakPlankLog);
    	
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.counterStoneQuartz, 8), "SSS", "QQQ", "QQQ", 'S', new ItemStack(Blocks.STONE, 1, 0), 'Q', Blocks.QUARTZ_BLOCK);
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.counterGraniteQuartz, 8), "SSS", "QQQ", "QQQ", 'S', new ItemStack(Blocks.STONE, 1, 1), 'Q', Blocks.QUARTZ_BLOCK);
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.counterDioriteQuartz, 8), "SSS", "QQQ", "QQQ", 'S', new ItemStack(Blocks.STONE, 1, 3), 'Q', Blocks.QUARTZ_BLOCK);
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.counterAndesiteQuartz, 8), "SSS", "QQQ", "QQQ", 'S', new ItemStack(Blocks.STONE, 1, 5), 'Q', Blocks.QUARTZ_BLOCK);
    	
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.fan, 1), " S ", "SWS", " S ", 'S', Items.STICK, 'W', new ItemStack(Blocks.PLANKS, 1, 0));
    	
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.refrigerator, 1), " I ", "iCi", 'I', Blocks.IRON_BLOCK, 'i', Items.IRON_INGOT, 'C', Blocks.CHEST);
    	
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.sink, 2), "bib", "QQQ", " Q ", 'b', Blocks.STONE_BUTTON, 'i', Items.IRON_INGOT, 'Q', Blocks.QUARTZ_BLOCK);
    	
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.lightBulbOff, 2), "W", "R", "G", 'W', FurnitureBlocks.darkOakOakPlankLog, 'R', Items.REDSTONE, 'G', Blocks.GLOWSTONE);
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(FurnitureBlocks.fanLightOff, 1), new Object[] { FurnitureBlocks.lightBulbOff, FurnitureBlocks.fan });
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(FurnitureBlocks.counterSinkStone, 1), new Object[] { FurnitureBlocks.counterStone, FurnitureBlocks.sink });
    	GameRegistry.addShapelessRecipe(new ItemStack(FurnitureBlocks.counterSinkGranite, 1), new Object[] { FurnitureBlocks.counterGranite, FurnitureBlocks.sink });
    	GameRegistry.addShapelessRecipe(new ItemStack(FurnitureBlocks.counterSinkDiorite, 1), new Object[] { FurnitureBlocks.counterDiorite, FurnitureBlocks.sink });
    	GameRegistry.addShapelessRecipe(new ItemStack(FurnitureBlocks.counterSinkAndesite, 1), new Object[] { FurnitureBlocks.counterAndesite, FurnitureBlocks.sink });
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(FurnitureBlocks.counterSinkStoneQuartz, 1), new Object[] { FurnitureBlocks.counterStoneQuartz, FurnitureBlocks.sink });
    	GameRegistry.addShapelessRecipe(new ItemStack(FurnitureBlocks.counterSinkGraniteQuartz, 1), new Object[] { FurnitureBlocks.counterGraniteQuartz, FurnitureBlocks.sink });
    	GameRegistry.addShapelessRecipe(new ItemStack(FurnitureBlocks.counterSinkDioriteQuartz, 1), new Object[] { FurnitureBlocks.counterDioriteQuartz, FurnitureBlocks.sink });
    	GameRegistry.addShapelessRecipe(new ItemStack(FurnitureBlocks.counterSinkAndesiteQuartz, 1), new Object[] { FurnitureBlocks.counterAndesiteQuartz, FurnitureBlocks.sink });
    
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.cabinetOak, 3), new Object[] { "WwW", "wCw", "WwW", 'W', new ItemStack(Blocks.LOG, 1), 'w', FurnitureBlocks.darkOakOakPlankLog, 'C', Blocks.CHEST });
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.cabinetSpruce, 3), new Object[] { "WwW", "wCw", "WwW", 'W', new ItemStack(Blocks.LOG, 1, 1), 'w', FurnitureBlocks.darkOakOakPlankLog, 'C', Blocks.CHEST });
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.cabinetBirch, 3), new Object[] { "WwW", "wCw", "WwW", 'W', new ItemStack(Blocks.LOG, 1, 2), 'w', FurnitureBlocks.darkOakOakPlankLog, 'C', Blocks.CHEST });
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.cabinetJungle, 3), new Object[] { "WwW", "wCw", "WwW", 'W', new ItemStack(Blocks.LOG, 1, 3), 'w', FurnitureBlocks.darkOakOakPlankLog, 'C', Blocks.CHEST });
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.cabinetAcacia, 3), new Object[] { "WwW", "wCw", "WwW", 'W', new ItemStack(Blocks.LOG2, 1), 'w', FurnitureBlocks.darkOakOakPlankLog, 'C', Blocks.CHEST });
    	GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.cabinetDarkOak, 3), new Object[] { "WwW", "wCw", "WwW", 'W', new ItemStack(Blocks.LOG2, 1, 1), 'w', FurnitureBlocks.darkOakOakPlankLog, 'C', Blocks.CHEST });
    }

}