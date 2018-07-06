package com.zundrel.furniture.common.blocks;

import com.zundrel.furniture.api.EnumConnection;
import com.zundrel.furniture.common.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockConnectingPillars extends BlockBasic {
    public static final PropertyBool NE = PropertyBool.create("ne");
    public static final PropertyBool SE = PropertyBool.create("se");
    public static final PropertyBool SW = PropertyBool.create("sw");
    public static final PropertyBool NW = PropertyBool.create("nw");

    private final EnumConnection[] connections;

    public BlockConnectingPillars(String unlocalizedName, Material material, EnumConnection... connections) {
        super(unlocalizedName, material);

        this.connections = connections;
    }

    public BlockConnectingPillars(String unlocalizedName, Material material, CreativeTabs tab, EnumConnection... connections) {
        super(unlocalizedName, material, tab);

        this.connections = connections;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer.Builder(this).add(NE).add(SW).add(SE).add(NW).build();
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
        boolean[] connected = getConnections(worldIn, pos);
        for (Corner corner : Corner.values()) {
            state = state.withProperty(corner.prop, connected[corner.ordinal()]);
        }
        return state;
    }

    private boolean[] getConnections(IBlockAccess world, BlockPos pos) {
        boolean[] res = new boolean[4];

        boolean frameXPos = isAdjacent(world, pos, EnumFacing.EAST);
        boolean frameXNeg = isAdjacent(world, pos, EnumFacing.WEST);
        boolean frameZPos = isAdjacent(world, pos, EnumFacing.SOUTH);
        boolean frameZNeg = isAdjacent(world, pos, EnumFacing.NORTH);

        res[Corner.SE.ordinal()]  = frameXPos || frameZPos;
        res[Corner.NE.ordinal()]  = frameXPos || frameZNeg;
        res[Corner.SW.ordinal()]  = frameXNeg || frameZPos;
        res[Corner.NW.ordinal()]  = frameXNeg || frameZNeg;

        return res;
    }

    @Override
    @Nonnull
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
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

        return block instanceof BlockConnectingPillars;
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

    /**
     * @author TeamPnuematic
     * Thank you to TeamPneumatic for their code from the elevator frame.
     */
    private enum Corner {
        NE(1, -1, BlockConnectingPillars.NE, new AxisAlignedBB(14f / 16f, 0, 0, 1, 1, 2f/16f)),
        SE(1, 1, BlockConnectingPillars.SE, new AxisAlignedBB(14f / 16f, 0, 14f / 16f, 1, 1, 1)),
        SW(-1, 1, BlockConnectingPillars.SW, new AxisAlignedBB(0, 0, 14f / 16f, 2f / 16f, 1, 1)),
        NW(-1,-1, BlockConnectingPillars.NW, new AxisAlignedBB(0, 0, 0, 2f/16f, 1, 2f/16f));

        final int x;
        final int z;
        final PropertyBool prop;
        final AxisAlignedBB aabb;

        Corner(int x, int z, PropertyBool prop, AxisAlignedBB aabb) {
            this.x = x; this.z = z;
            this.prop = prop;
            this.aabb = aabb;
        }
    }
}
