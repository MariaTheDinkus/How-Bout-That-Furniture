package com.momnop.furniture.blocks;

import mcjty.lib.tools.WorldTools;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.momnop.furniture.handlers.SoundHandler;
import com.momnop.furniture.utils.RotationUtils;

public class BlockLightSwitch extends BlockFurnitureFacing {
	
	public static final PropertyBool POWERED = PropertyBool.create("powered");

	public BlockLightSwitch(Material materialIn, float hardness, SoundType type, String unlocalizedName, CreativeTabs tab) {
		super(materialIn, hardness, type, unlocalizedName, tab);
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
		IBlockState state = worldIn.getBlockState(pos.offset(side.getOpposite()));
		if (place == true && state.isFullBlock() && state.isOpaqueCube()) {
			return true;
		}
		return false;
	}
    
    @Override
    protected void clOnNeighborChanged(IBlockState state, World worldIn,
    		BlockPos pos, Block blockIn) {
    	if (!worldIn.getBlockState(pos.offset(state.getValue(FACING))).isFullBlock() && !worldIn.getBlockState(pos.offset(state.getValue(FACING))).isOpaqueCube()) {
    		this.dropBlockAsItem(worldIn, pos, state, 0);
    		worldIn.setBlockToAir(pos);
    	}
    }
    
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    	WorldTools.notifyNeighborsOfStateChange(worldIn, pos, this);
        WorldTools.notifyNeighborsOfStateChange(worldIn, pos.offset(state.getValue(FACING)), this);
    }
	
	@Override
	public boolean clOnBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			EnumFacing heldItem, float side, float hitX, float hitY) {
		worldIn.setBlockState(pos, state.withProperty(POWERED, !state.getValue(POWERED)));
		
		if (state.getValue(POWERED)) {
			worldIn.playSound(null, pos, SoundHandler.lightSwitchOff, SoundCategory.BLOCKS, 1, 1);
		} else {
			worldIn.playSound(null, pos, SoundHandler.lightSwitchOn, SoundCategory.BLOCKS, 1, 1);
		}
		
		WorldTools.notifyNeighborsOfStateChange(worldIn, pos, this);
        WorldTools.notifyNeighborsOfStateChange(worldIn, pos.offset(state.getValue(FACING)), this);
			
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
	public IBlockState clGetStateForPlacement(World world, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING,
				facing.getOpposite());
	}
	
}
