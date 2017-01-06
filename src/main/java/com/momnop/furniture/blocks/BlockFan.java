package com.momnop.furniture.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.momnop.furniture.tiles.TileEntityCeilingFan;

public class BlockFan extends BlockFurniture implements ITileEntityProvider {
	
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	
	public BlockFan(Material materialIn, float hardness, SoundType type, String unlocalizedName, CreativeTabs tab) {
		super(materialIn, hardness, type, unlocalizedName, tab);
		this.setDefaultState(this.getDefaultState().withProperty(POWERED, false));
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCeilingFan();
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source,
			BlockPos pos) {
		return new AxisAlignedBB(0, 12F / 16F, 0, 1, 1, 1);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.clOnNeighborChanged(state, worldIn, pos, worldIn.getBlockState(pos)
				.getBlock());
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos.up()).isSideSolid(worldIn, pos, EnumFacing.DOWN)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void clOnNeighborChanged(IBlockState state, World worldIn, BlockPos pos,
			Block blockIn) {
		if (!worldIn.getBlockState(pos.up()).isSideSolid(worldIn, pos, EnumFacing.DOWN)) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
    		worldIn.setBlockToAir(pos);
		}
		
		if (state.getValue(POWERED) && !worldIn.isBlockPowered(pos)) {
			worldIn.scheduleUpdate(pos, this, 4);
		} else if (!state.getValue(POWERED) && worldIn.isBlockPowered(pos)) {
			worldIn.setBlockState(pos, state.withProperty(POWERED, true), 2);
		}
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state,
			Random rand) {
		if (state.getValue(POWERED) && !worldIn.isBlockPowered(pos)) {
			worldIn.setBlockState(pos, state.withProperty(POWERED, false), 2);
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { POWERED });
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(POWERED) ? 1 : 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean powered = meta == 1 ? true : false;
		return this.getDefaultState().withProperty(POWERED, powered);
	}
}
