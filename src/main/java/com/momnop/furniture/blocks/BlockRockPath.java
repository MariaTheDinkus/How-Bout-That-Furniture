package com.momnop.furniture.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRockPath extends BlockFurniture {
	
	public static final PropertyInteger RANDOM = PropertyInteger.create("random", 0, 7);

	public BlockRockPath(Material materialIn, float hardness, SoundType type, String unlocalizedName, CreativeTabs tab) {
		super(materialIn, hardness, type, unlocalizedName, tab);
		setDefaultState(this.getDefaultState().withProperty(RANDOM, 0));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(RANDOM);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(RANDOM, meta);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { RANDOM });
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.UP)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void clOnNeighborChanged(IBlockState state, World worldIn, BlockPos pos,
			Block blockIn) {
		if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.UP)) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
    		worldIn.setBlockToAir(pos);
		}
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source,
			BlockPos pos) {
		AxisAlignedBB rock = new AxisAlignedBB(1F / 16F, 0, 1F / 16F, 15F / 16F, 0.5F / 16F, 15F / 16F);
		
		return rock;
	}
	
	@Override
	public IBlockState clGetStateForPlacement(World world, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		return this.getDefaultState().withProperty(RANDOM, new Random().nextInt(7));
	}
	
}
