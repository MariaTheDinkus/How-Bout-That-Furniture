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
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.momnop.furniture.tiles.TileEntityCeilingFan;

public class BlockFanLight extends BlockFurniture implements ITileEntityProvider {
	
	private final boolean isOn;
	
	public BlockFanLight(Material materialIn, float hardness, SoundType type, String unlocalizedName, boolean isOn, CreativeTabs tab) {
		super(materialIn, hardness, type, unlocalizedName, tab);
		this.isOn = isOn;

		if (isOn) {
			this.setLightLevel(1.0F);
		}
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
	
	/**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
	@Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            if (this.isOn && !worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, FurnitureBlocks.fanLightOff.getDefaultState(), 2);
            }
            else if (!this.isOn && worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, FurnitureBlocks.fanLightOn.getDefaultState(), 2);
            }
        }
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
	@Override
    public void clOnNeighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
    {
    	if (!worldIn.getBlockState(pos.up()).isSideSolid(worldIn, pos, EnumFacing.DOWN)) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
    		worldIn.setBlockToAir(pos);
		}
    	
        if (!worldIn.isRemote)
        {
            if (this.isOn && !worldIn.isBlockPowered(pos))
            {
                worldIn.scheduleUpdate(pos, this, 4);
            }
            else if (!this.isOn && worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, FurnitureBlocks.fanLightOn.getDefaultState(), 2);
            }
        }
    }
    
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if (this.isOn && !worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, FurnitureBlocks.fanLightOff.getDefaultState(), 2);
            }
        }
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
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(FurnitureBlocks.fanLightOff);
	}
}
