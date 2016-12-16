package com.momnop.furniture.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLever;
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

import com.momnop.furniture.handlers.SoundHandler;
import com.momnop.furniture.tiles.TileEntitySofa;
import com.momnop.furniture.utils.RotationUtils;
import com.momnop.furniture.utils.SittableUtil;

public class BlockLightSwitch extends BlockFurnitureFacing {
	
	public static final PropertyBool POWERED = PropertyBool.create("powered");

	public BlockLightSwitch(Material materialIn, float hardness, SoundType type, String unlocalizedName) {
		super(materialIn, hardness, type, unlocalizedName);
		setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, false));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, POWERED });
	}
	
	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean powered = false;
		if (String.valueOf(meta).length() == 2) {
			powered = true;
		} else {
			powered = false;
		}

		int metaNew = 0;
		if (String.valueOf(meta).length() == 1) {
			metaNew = Integer.parseInt(String.valueOf(("" + meta).charAt(0)));
		} else {
			metaNew = Integer.parseInt(String.valueOf(("" + meta).charAt(1)));
		}

		return this.getDefaultState()
				.withProperty(FACING, EnumFacing.getHorizontal(metaNew))
				.withProperty(POWERED, powered);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int powered = 0;
		if (state.getValue(POWERED) == false) {
			powered = 0;
		} else {
			powered = 1;
		}

		return Integer.parseInt(powered + ""
				+ state.getValue(FACING).getIndex());
	}
	
	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return ((Boolean)blockState.getValue(POWERED)).booleanValue() ? 15 : 0;
    }

	@Override
    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return !((Boolean)blockState.getValue(POWERED)).booleanValue() ? 0 : (blockState.getValue(FACING).getOpposite() == side ? 15 : 0);
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    @Override
    public boolean canProvidePower(IBlockState state)
    {
        return true;
    }
    
    @Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos,
			EnumFacing side) {
    	boolean place = false;
		for (int i = 0; i < EnumFacing.HORIZONTALS.length; i++) {
			if (EnumFacing.HORIZONTALS[i] == side) {
				place = true;
				break;
			}
		}
		System.out.println(worldIn.getBlockState(pos.offset(side.getOpposite())).getBlock().getLocalizedName());
		IBlockState state = worldIn.getBlockState(pos.offset(side.getOpposite()));
		if (place == true && state.isFullBlock() && state.isOpaqueCube()) {
			return true;
		}
		return false;
	}
    
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos,
    		Block blockIn, BlockPos p_189540_5_) {
    	if (!worldIn.getBlockState(pos.offset(state.getValue(FACING))).isFullBlock() && !worldIn.getBlockState(pos.offset(state.getValue(FACING))).isOpaqueCube()) {
    		this.dropBlockAsItem(worldIn, pos, state, 0);
    		worldIn.setBlockToAir(pos);
    	}
    }
    
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    	worldIn.notifyNeighborsOfStateChange(pos, this, false);
        worldIn.notifyNeighborsOfStateChange(pos.offset(state.getValue(FACING)), this, false);
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			EnumFacing heldItem, float side, float hitX, float hitY) {
		worldIn.setBlockState(pos, state.withProperty(POWERED, !state.getValue(POWERED)));
		
		if (state.getValue(POWERED)) {
			worldIn.playSound(null, pos, SoundHandler.lightSwitchOff, SoundCategory.BLOCKS, 1, 1);
		} else {
			worldIn.playSound(null, pos, SoundHandler.lightSwitchOn, SoundCategory.BLOCKS, 1, 1);
		}
		
		worldIn.notifyNeighborsOfStateChange(pos, this, false);
        worldIn.notifyNeighborsOfStateChange(pos.offset(state.getValue(FACING)), this, false);
			
		return true;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source,
			BlockPos pos) {
		AxisAlignedBB back = new AxisAlignedBB(6F / 16F, 5F / 16F, 15F / 16F, 10F / 16F, 11F / 16F, 1);
		
		back = RotationUtils.getRotatedAABB(back, state.getValue(FACING).getOpposite());
		
		return back;
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING,
				facing.getOpposite());
	}
	
}
