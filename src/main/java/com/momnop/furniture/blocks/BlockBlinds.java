package com.momnop.furniture.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.momnop.furniture.tiles.TileEntitySofa;
import com.momnop.furniture.utils.RotationUtils;
import com.momnop.furniture.utils.SittableUtil;

public class BlockBlinds extends BlockFurnitureFacing {
	
	public static final PropertyBool UP = PropertyBool.create("up");
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool OPEN = PropertyBool.create("open");

	public BlockBlinds(Material materialIn, float hardness, SoundType type, String unlocalizedName) {
		super(materialIn, hardness, type, unlocalizedName);
		setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(UP, false).withProperty(LEFT, false).withProperty(OPEN, true));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, UP, LEFT, OPEN });
	}
	
	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean open = false;
		if (String.valueOf(meta).length() == 2) {
			open = true;
		} else {
			open = false;
		}

		int metaNew = 0;
		if (String.valueOf(meta).length() == 1) {
			metaNew = Integer.parseInt(String.valueOf(("" + meta).charAt(0)));
		} else {
			metaNew = Integer.parseInt(String.valueOf(("" + meta).charAt(1)));
		}

		return this.getDefaultState()
				.withProperty(FACING, EnumFacing.getHorizontal(metaNew))
				.withProperty(OPEN, open);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int powered = 0;
		if (state.getValue(OPEN) == false) {
			powered = 0;
		} else {
			powered = 1;
		}

		return Integer.parseInt(powered + ""
				+ state.getValue(FACING).getIndex());
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			EnumFacing heldItem, float side, float hitX, float hitY) {
		EnumFacing facing = state.getValue(FACING);
		
		if (state.getValue(OPEN)) {
			worldIn.playSound(null, pos, SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundCategory.BLOCKS, 1, 1);
		} else if (!state.getValue(OPEN)) {
			worldIn.playSound(null, pos, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, SoundCategory.BLOCKS, 1, 1);
		}
		
		worldIn.setBlockState(pos, state.withProperty(OPEN, !state.getValue(OPEN).booleanValue()));
		return true;
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos,
			Block blockIn, BlockPos p_189540_5_) {
		IBlockState neighbor = worldIn.getBlockState(p_189540_5_);
		if (neighbor.getBlock() instanceof BlockBlinds && neighbor.getValue(FACING) == state.getValue(FACING)) {
			if (neighbor.getValue(OPEN) != state.getValue(OPEN)) {
				worldIn.setBlockState(pos, state.withProperty(OPEN, neighbor.getValue(OPEN)));
			}
		}
	}
	
	public boolean isAdjacentBlockOfMyType(IBlockAccess world, BlockPos position, EnumFacing facing) {

        assert null != world : "world cannot be null";
        assert null != position : "position cannot be null";
        assert null != this : "type cannot be null";

        BlockPos newPosition = position.offset(facing);
        IBlockState blockState = world.getBlockState(newPosition);
        Block block = (null == blockState) ? null : blockState.getBlock();
        
        return this == block;
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source,
			BlockPos pos) {
		AxisAlignedBB back = new AxisAlignedBB(0, 0F, 15F / 16F, 1, 1, 1);
		
		back = RotationUtils.getRotatedAABB(back, state.getValue(FACING).getOpposite());
		
		return back;
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING,
				placer.getHorizontalFacing());
	}
	
	

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos position) {

        state = state
                .withProperty(UP, this.isAdjacentBlockOfMyType(world, position, EnumFacing.UP))
                .withProperty(LEFT, this.isAdjacentBlockOfMyType(world, position, state.getValue(FACING).rotateYCCW()));
        
        return state;
    }
	
}
