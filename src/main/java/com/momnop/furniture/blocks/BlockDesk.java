package com.momnop.furniture.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDesk extends BlockFurnitureFacing {
	
	public BlockDesk(Material material, float hardness, SoundType type, String unlocalizedName) {
		super(material, hardness, type, unlocalizedName);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState blockState) {
		return false;
	}
	
	/**
     * Convert the given metadata into a BlockState for this Block
     */
	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}
	
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer, EnumHand hand) {
		if (placer.getHorizontalFacing() == EnumFacing.NORTH || placer.getHorizontalFacing() == EnumFacing.SOUTH) {
			worldIn.setBlockState(pos.add(-1, 0, 0), FurnitureBlocks.deskInvisible1.getDefaultState().withProperty(BlockInvisible.FACING, placer.getHorizontalFacing().getOpposite()));
			worldIn.setBlockState(pos.add(1, 0, 0), FurnitureBlocks.deskInvisible2.getDefaultState().withProperty(BlockInvisible.FACING, placer.getHorizontalFacing().getOpposite()));
		} else {
			worldIn.setBlockState(pos.add(0, 0, -1), FurnitureBlocks.deskInvisible1.getDefaultState().withProperty(BlockInvisible.FACING, placer.getHorizontalFacing().getOpposite()));
			worldIn.setBlockState(pos.add(0, 0, 1), FurnitureBlocks.deskInvisible2.getDefaultState().withProperty(BlockInvisible.FACING, placer.getHorizontalFacing().getOpposite()));
		}
		
		if (placer.isSneaking()) {
			return this.getDefaultState().withProperty(FACING,
					placer.getHorizontalFacing());
		}
		return this.getDefaultState().withProperty(FACING,
				placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (state.getValue(FACING) == EnumFacing.NORTH || state.getValue(FACING) == EnumFacing.SOUTH) {
			worldIn.setBlockToAir(pos.add(-1, 0, 0));
			worldIn.setBlockToAir(pos.add(1, 0, 0));
		} else {
			worldIn.setBlockToAir(pos.add(0, 0, -1));
			worldIn.setBlockToAir(pos.add(0, 0, 1));
		}
	}
}
