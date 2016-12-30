package com.momnop.furniture.blocks;

import java.util.Random;

import mcjty.lib.tools.ItemStackTools;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.momnop.furniture.tiles.TileEntityRefrigeratorTop;

public class BlockRefrigeratorInvisible extends BlockFurnitureFacing implements ITileEntityProvider {
	
	private ItemStack droppedItem = null;
	
	public BlockRefrigeratorInvisible(Material material, float hardness, SoundType type, String unlocalizedName, ItemStack droppedItem, CreativeTabs tab) {
		super(material, hardness, type, unlocalizedName, tab);
		this.droppedItem = droppedItem;
		
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.setBlockToAir(pos.down());
		
		TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target,
			World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(FurnitureBlocks.refrigerator, 1);
	}
	
	@Override
	protected void clOnNeighborChanged(IBlockState state, World worldIn,
			BlockPos pos, Block blockIn) {
		if (worldIn.isAirBlock(pos.down())) {
			worldIn.setBlockToAir(pos);
		}
	}

	@Override
	public boolean isOpaqueCube(IBlockState blockState) {
		return false;
	}
	
	@Override
	protected IBlockState clGetStateForPlacement(World world, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING,
				placer.getHorizontalFacing());
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source,
			BlockPos pos) {
		if (droppedItem.getItem() instanceof ItemBlock) {
			Block block = Block.getBlockFromItem(droppedItem.getItem());
			if (block instanceof BlockRefrigerator) {
				return block.getBoundingBox(state, source, pos).offset(0, -1, 0);
			}
		}
		return super.getBoundingBox(state, source, pos);
	}
	
	@Override
	public boolean clOnBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.getTileEntity(pos) instanceof TileEntityRefrigeratorTop) {
			TileEntityRefrigeratorTop fridge = (TileEntityRefrigeratorTop) worldIn.getTileEntity(pos);
			playerIn.displayGUIChest((IInventory) fridge);
			return true;
		}
		return false;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return droppedItem.getItem();
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return ItemStackTools.getStackSize(droppedItem);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityRefrigeratorTop();
	}
}
