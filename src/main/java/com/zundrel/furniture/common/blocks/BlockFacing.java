package com.zundrel.furniture.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFacing extends BlockBasic {
	public BlockFacing(String unlocalizedName, Material material) {
		super(unlocalizedName, material);
	}

	public BlockFacing(String unlocalizedName, Material material, CreativeTabs tab) {
		super(unlocalizedName, material, tab);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer.Builder(this).add(FACING).build();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (!placer.isSneaking()) {
			return getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
		} else {
			return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
		}
	}
}