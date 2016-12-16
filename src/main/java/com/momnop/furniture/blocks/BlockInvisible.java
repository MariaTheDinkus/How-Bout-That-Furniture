package com.momnop.furniture.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockInvisible extends BlockFurnitureFacing {
	
	private ItemStack droppedItem = null;
	private int height;
	
	public BlockInvisible(Material material, float hardness, SoundType type, String unlocalizedName, ItemStack droppedItem, int height) {
		super(Material.WOOD, hardness, type, unlocalizedName);
		this.droppedItem = droppedItem;
		this.height = height;
		
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState blockState) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source,
			BlockPos pos) {
		return new AxisAlignedBB(0F, 0F, 0F, 1F, height / 16F, 1F);
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
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (this.getUnlocalizedName().substring(5).equals("desk_invisible_1")) {
			if (state.getValue(FACING) == EnumFacing.NORTH || state.getValue(FACING) == EnumFacing.SOUTH) {
				worldIn.setBlockToAir(pos.add(1, 0, 0));
				worldIn.setBlockToAir(pos.add(2, 0, 0));
			} else if (state.getValue(FACING) == EnumFacing.EAST || state.getValue(FACING) == EnumFacing.WEST) {
				worldIn.setBlockToAir(pos.add(0, 0, 1));
				worldIn.setBlockToAir(pos.add(0, 0, 2));
			}
		} else if (this.getUnlocalizedName().substring(5).equals("desk_invisible_2")) {
			if (state.getValue(FACING) == EnumFacing.NORTH || state.getValue(FACING) == EnumFacing.SOUTH) {
				worldIn.setBlockToAir(pos.add(-1, 0, 0));
				worldIn.setBlockToAir(pos.add(-2, 0, 0));
			} else if (state.getValue(FACING) == EnumFacing.EAST || state.getValue(FACING) == EnumFacing.WEST) {
				worldIn.setBlockToAir(pos.add(0, 0, -1));
				worldIn.setBlockToAir(pos.add(0, 0, -2));
			}
		}
	}
	
	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to
	 * allow for adjustments to the IBlockstate
	 */
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING,
				placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return droppedItem.getItem();
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return droppedItem.func_190916_E();
	}
}
