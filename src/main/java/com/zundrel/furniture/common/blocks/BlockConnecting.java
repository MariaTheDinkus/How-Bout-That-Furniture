package com.zundrel.furniture.common.blocks;

import com.zundrel.furniture.api.EnumConnection;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockConnecting extends BlockBasic {
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");

    private final EnumConnection[] connections;

    public BlockConnecting(String unlocalizedName, Material material, EnumConnection... connections) {
        super(unlocalizedName, material);

        this.connections = connections;
    }

    public BlockConnecting(String unlocalizedName, Material material, CreativeTabs tab, EnumConnection... connections) {
        super(unlocalizedName, material, tab);

        this.connections = connections;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer.Builder(this).add(NORTH).add(EAST).add(SOUTH).add(WEST).build();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return super.getStateFromMeta(meta);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(NORTH, isAdjacent(worldIn, pos, EnumFacing.NORTH)).withProperty(EAST, isAdjacent(worldIn, pos, EnumFacing.EAST)).withProperty(SOUTH, isAdjacent(worldIn, pos, EnumFacing.SOUTH)).withProperty(WEST, isAdjacent(worldIn, pos, EnumFacing.WEST));
    }

    public boolean isAdjacent(IBlockAccess world, BlockPos position, EnumFacing facing) {
        boolean adjacent = false;

        for (EnumConnection connection : connections) {
            if (connection == EnumConnection.SAME) {
                if (isAdjacentSame(world, position, facing)) {
                    adjacent = true;
                }
            } else if (connection == EnumConnection.TYPE) {
                if (isAdjacentType(world, position, facing)) {
                    adjacent = true;
                }
            } else if (connection == EnumConnection.OPAQUE) {
                if (isAdjacentOpaque(world, position, facing)) {
                    adjacent = true;
                }
            }
        }

        return adjacent;
    }

    public boolean isAdjacentSame(IBlockAccess world, BlockPos position, EnumFacing facing) {
        assert null != world : "world cannot be null";
        assert null != position : "position cannot be null";
        assert null != this : "type cannot be null";

        BlockPos newPosition = position.offset(facing);
        IBlockState blockState = world.getBlockState(newPosition);
        Block block = (null == blockState) ? null : blockState.getBlock();

        return this == block;
    }

    public boolean isAdjacentType(IBlockAccess world, BlockPos position, EnumFacing facing) {
        assert null != world : "world cannot be null";
        assert null != position : "position cannot be null";
        assert null != this : "type cannot be null";

        BlockPos newPosition = position.offset(facing);
        IBlockState blockState = world.getBlockState(newPosition);
        Block block = (null == blockState) ? null : blockState.getBlock();

        return block instanceof BlockConnecting;
    }

    public boolean isAdjacentOpaque(IBlockAccess world, BlockPos position, EnumFacing facing) {

        assert null != world : "world cannot be null";
        assert null != position : "position cannot be null";
        assert null != this : "type cannot be null";

        BlockPos newPosition = position.offset(facing);
        IBlockState blockState = world.getBlockState(newPosition);
        Block block = (null == blockState) ? null : blockState.getBlock();

        return this == block || blockState.isOpaqueCube();
    }
}
