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
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class BlockDoorbell extends BlockFurnitureFacing {
	
	public static final PropertyBool POWERED = PropertyBool.create("powered");

	public BlockDoorbell(Material materialIn, float hardness, SoundType type, String unlocalizedName, CreativeTabs tab) {
		super(materialIn, hardness, type, unlocalizedName, tab);
		setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, false));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, POWERED });
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.setBlockState(pos, state.withProperty(POWERED, false));
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
	public boolean clOnBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			EnumFacing heldItem, float side, float hitX, float hitY) {
		EnumFacing facing = state.getValue(FACING);
		if (!state.getValue(POWERED)) {
			worldIn.setBlockState(pos, state.withProperty(POWERED, true), 2);
		
			worldIn.playSound(null, pos, SoundHandler.doorbell, SoundCategory.BLOCKS, 1, 1F);
			
			worldIn.scheduleUpdate(pos, this, 15);
			return true;
		}
		return false;
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state,
			Random rand) {
		worldIn.setBlockState(pos, state.withProperty(POWERED, false), 2);
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
		IBlockState state = worldIn.getBlockState(pos.offset(side.getOpposite()));
		if (place == true && state.isFullBlock() && state.isOpaqueCube()) {
			return true;
		}
		return false;
	}
    
    @Override
    public void clOnNeighborChanged(IBlockState state, World worldIn, BlockPos pos,
    		Block blockIn) {
    	if (!worldIn.getBlockState(pos.offset(state.getValue(FACING))).isFullBlock() && !worldIn.getBlockState(pos.offset(state.getValue(FACING))).isOpaqueCube()) {
    		this.dropBlockAsItem(worldIn, pos, state, 0);
    		worldIn.setBlockToAir(pos);
    	}
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source,
			BlockPos pos) {
		AxisAlignedBB back = new AxisAlignedBB(6F / 16F, 5F / 16F, 15F / 16F, 10F / 16F, 11F / 16F, 1);
		
		if (state.getValue(POWERED) == false) {
			back = back.expand(0, 0, 1F / 16F);
		}
		
		back = RotationUtils.getRotatedAABB(back, state.getValue(FACING).getOpposite());
		
		return back;
	}
	
	@Override
	public IBlockState clGetStateForPlacement(World world, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING,
				facing.getOpposite());
	}
	
}
