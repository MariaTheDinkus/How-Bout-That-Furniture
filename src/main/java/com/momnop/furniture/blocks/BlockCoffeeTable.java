package com.momnop.furniture.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.momnop.furniture.tiles.TileEntitySofa;
import com.momnop.furniture.utils.RotationUtils;
import com.momnop.furniture.utils.SittableUtil;

public class BlockCoffeeTable extends BlockFurniture {
	
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");

	public BlockCoffeeTable(Material materialIn, float hardness, SoundType type, String unlocalizedName, CreativeTabs tab) {
		super(materialIn, hardness, type, unlocalizedName, tab);
		setDefaultState(this.getDefaultState().withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source,
			BlockPos pos) {
		return new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { NORTH, EAST, SOUTH, WEST });
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState();
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
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos position) {

        state = state
                .withProperty(EAST, this.isAdjacentBlockOfMyType(world, position, EnumFacing.EAST))
                .withProperty(NORTH, this.isAdjacentBlockOfMyType(world, position, EnumFacing.NORTH))
                .withProperty(SOUTH, this.isAdjacentBlockOfMyType(world, position, EnumFacing.SOUTH))
                .withProperty(WEST, this.isAdjacentBlockOfMyType(world, position, EnumFacing.WEST));
        
        return state;
    }
	
}
