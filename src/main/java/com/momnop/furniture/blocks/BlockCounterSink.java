package com.momnop.furniture.blocks;

import java.util.List;

import javax.annotation.Nullable;

import mcjty.lib.tools.InventoryTools;
import mcjty.lib.tools.ItemStackTools;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCounterSink extends BlockFurnitureConnectingFacing {
	
	public static final PropertyBool WATER = PropertyBool.create("water");

    public BlockCounterSink(Material materialIn, float hardness, SoundType type, String unlocalizedName, CreativeTabs tab) {
		super(materialIn, hardness, type, unlocalizedName, tab);
		setDefaultState(this.getDefaultState().withProperty(WATER, false).withProperty(FACING, EnumFacing.NORTH).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { WATER, FACING, NORTH, EAST, SOUTH, WEST });
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}
	
	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean water = false;
		if (String.valueOf(meta).length() == 2) {
			water = true;
		} else {
			water = false;
		}

		int metaNew = 0;
		if (String.valueOf(meta).length() == 1) {
			metaNew = Integer.parseInt(String.valueOf(("" + meta).charAt(0)));
		} else {
			metaNew = Integer.parseInt(String.valueOf(("" + meta).charAt(1)));
		}

		return this.getDefaultState()
				.withProperty(FACING, EnumFacing.getHorizontal(metaNew))
				.withProperty(WATER, water);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int water = 0;
		if (state.getValue(WATER) == false) {
			water = 0;
		} else {
			water = 1;
		}

		return Integer.parseInt(water + ""
				+ state.getValue(FACING).getIndex());
	}
	
	@Override
	protected boolean clOnBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (playerIn.getHeldItem(hand) != ItemStackTools.getEmptyStack() && playerIn.getHeldItem(hand).getItem() == Items.GLASS_BOTTLE) {
			if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.WATER && worldIn.getBlockState(pos.down()).getBlock().getMetaFromState(worldIn.getBlockState(pos.down())) == 0 || worldIn.getBlockState(pos.down().down()).getBlock() == Blocks.WATER && worldIn.getBlockState(pos.down().down()).getBlock().getMetaFromState(worldIn.getBlockState(pos.down().down())) == 0 || state.getValue(WATER)) {
				ItemStackTools.incStackSize(playerIn.getHeldItem(hand), -1);
				playerIn.inventory.addItemStackToInventory(Items.POTIONITEM.getDefaultInstance());
			}
		}
		
		if (worldIn.getBlockState(pos).getBlock() instanceof BlockCounterSink && !state.getValue(WATER)) {
			if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.WATER && worldIn.getBlockState(pos.down()).getBlock().getMetaFromState(worldIn.getBlockState(pos.down())) == 0 || worldIn.getBlockState(pos.down().down()).getBlock() == Blocks.WATER && worldIn.getBlockState(pos.down().down()).getBlock().getMetaFromState(worldIn.getBlockState(pos.down().down())) == 0) {
				worldIn.setBlockState(pos, state.withProperty(WATER, true));
				return true;
			}
		}
		
		if (state.getValue(WATER)) {
			if (playerIn.getHeldItem(hand) != ItemStackTools.getEmptyStack() && playerIn.getHeldItem(hand).getItem() == Items.BUCKET) {
				ItemStackTools.incStackSize(playerIn.getHeldItem(hand), -1);
				playerIn.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET));
				worldIn.setBlockState(pos, state.withProperty(WATER, false));
				return true;
			}
		}
		
		if (!state.getValue(WATER)) {
			if (playerIn.getHeldItem(hand) != ItemStackTools.getEmptyStack() && playerIn.getHeldItem(hand).getItem() == Items.WATER_BUCKET) {
				if (!playerIn.capabilities.isCreativeMode) {
					ItemStackTools.incStackSize(playerIn.getHeldItem(hand), -1);
					playerIn.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET));
				}
				worldIn.setBlockState(pos, state.withProperty(WATER, true));
				return true;
			}
		}
		
		if (playerIn.isSneaking()) {
			worldIn.setBlockState(pos, state.withProperty(WATER, false));
			return true;
		}
		return false;
	}
	
	public boolean isAdjacentBlockOfMyTypeOrOpaque(IBlockAccess world, BlockPos position, EnumFacing facing) {

        assert null != world : "world cannot be null";
        assert null != position : "position cannot be null";
        assert null != this : "type cannot be null";

        IBlockState state = world.getBlockState(position);
        
        if (state.getValue(FACING) == EnumFacing.NORTH) {
        	
        } else if (state.getValue(FACING) == EnumFacing.EAST) {
        	facing = facing.rotateY();
        } else if (state.getValue(FACING) == EnumFacing.SOUTH) {
        	facing = facing.getOpposite();
        } else if (state.getValue(FACING) == EnumFacing.WEST) {
        	facing = facing.rotateYCCW();
        }
        
        BlockPos newPosition = position.offset(facing);
        IBlockState blockState = world.getBlockState(newPosition);
        Block block = (null == blockState) ? null : blockState.getBlock();
        
        if (this == block) {
        	return true;
        } else if (block instanceof BlockFurnitureConnecting || block instanceof BlockFurnitureConnectingFacing) {
        	return true;
        } else if (blockState.isOpaqueCube()) {
        	return true;
        } else {
        	return false;
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos position) {

        state = state
                .withProperty(EAST, this.isAdjacentBlockOfMyTypeOrOpaque(world, position, EnumFacing.EAST))
                .withProperty(NORTH, this.isAdjacentBlockOfMyTypeOrOpaque(world, position, EnumFacing.NORTH))
                .withProperty(SOUTH, this.isAdjacentBlockOfMyTypeOrOpaque(world, position, EnumFacing.SOUTH))
                .withProperty(WEST, this.isAdjacentBlockOfMyTypeOrOpaque(world, position, EnumFacing.WEST));
        
        return state;
    }
    
    @Override
	protected IBlockState clGetStateForPlacement(World worldIn, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING,
				placer.getHorizontalFacing());
	}
}
