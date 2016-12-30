package com.momnop.furniture.blocks;

import com.momnop.furniture.tiles.TileEntityRefrigerator;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRefrigerator extends BlockFurnitureFacing implements ITileEntityProvider {

	public BlockRefrigerator(Material materialIn, float hardness, SoundType type, String unlocalizedName, CreativeTabs tab) {
		super(materialIn, hardness, type, unlocalizedName, tab);
		setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.setBlockState(pos.up(), FurnitureBlocks.fridgeInvis.getDefaultState().withProperty(BlockRefrigeratorInvisible.FACING, state.getValue(FACING)));
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		if (worldIn.isAirBlock(pos.up())) {
			return true;
		}
		return false;
	}
	
	@Override
	public void clOnNeighborChanged(IBlockState state, World worldIn, BlockPos pos,
			Block blockIn) {
		if (worldIn.isAirBlock(pos.up())) {
			worldIn.setBlockToAir(pos);
		}
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.setBlockToAir(pos.up());
		
		TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source,
			BlockPos pos) {
		return new AxisAlignedBB(0, 0, 0, 1, 2, 1);
	}
	
	@Override
	public boolean clOnBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.getTileEntity(pos) instanceof TileEntityRefrigerator) {
			TileEntityRefrigerator fridge = (TileEntityRefrigerator) worldIn.getTileEntity(pos);
			playerIn.displayGUIChest((IInventory) fridge);
			return true;
		}
		return false;
	}
	
	@Override
	public IBlockState clGetStateForPlacement(World world, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING,
				placer.getHorizontalFacing());
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityRefrigerator();
	}
	
}
