package com.momnop.furniture.handlers;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.momnop.furniture.blocks.BlockChair;
import com.momnop.furniture.blocks.BlockSofa;
import com.momnop.furniture.blocks.BlockStool;

public class FurnitureEventHandler {
	@SubscribeEvent
	public void onBlockBreak(HarvestDropsEvent event) {
		if (event.getState().getBlock() instanceof BlockSofa) {
			IBlockState sofa = event.getState();
			
			if (sofa.getValue(BlockSofa.COLOR) != 0) {
				event.getDrops().add(new ItemStack(Items.DYE, 1, 15 - sofa.getValue(BlockSofa.COLOR)));
			}
		}
		
		if (event.getState().getBlock() instanceof BlockChair) {
			IBlockState sofa = event.getState();
			
			if (sofa.getValue(BlockChair.COLOR) != 0) {
				event.getDrops().add(new ItemStack(Items.DYE, 1, 15 - sofa.getValue(BlockChair.COLOR)));
			}
		}
		
		if (event.getState().getBlock() instanceof BlockStool) {
			IBlockState sofa = event.getState();
			
			if (sofa.getValue(BlockStool.COLOR) != 0) {
				event.getDrops().add(new ItemStack(Items.DYE, 1, 15 - sofa.getValue(BlockStool.COLOR)));
			}
		}
	}
	
	@SubscribeEvent
	public void playerLeftClick(PlayerInteractEvent.LeftClickBlock event) {
		if (!event.getEntityPlayer().capabilities.isCreativeMode && event.getWorld().getBlockState(event.getPos()).getBlock() instanceof BlockDoor && event.getWorld().getBlockState(event.getPos()).getMaterial() == Material.WOOD) {
			event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1, 1);
			event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1, 1);
			event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1, 1);
			event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1, 1);
		}
		
		if (!event.getEntityPlayer().capabilities.isCreativeMode && event.getWorld().getBlockState(event.getPos()).getBlock() instanceof BlockDoor && event.getWorld().getBlockState(event.getPos()).getMaterial() == Material.IRON) {
			event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1, 1);
			event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1, 1);
			event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1, 1);
			event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1, 1);
			event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1, 1);
			event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1, 1);
		}
	}
}