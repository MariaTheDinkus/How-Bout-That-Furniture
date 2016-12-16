package com.momnop.furniture.handlers;

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
    }

}