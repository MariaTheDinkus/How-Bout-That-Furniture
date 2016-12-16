package com.momnop.furniture.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
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

import com.momnop.furniture.tiles.TileEntityChair;
import com.momnop.furniture.tiles.TileEntityStool;
import com.momnop.furniture.utils.SittableUtil;

public class BlockStool extends BlockFurniture implements ITileEntityProvider {
	
	public static final PropertyInteger COLOR = PropertyInteger.create("color", 0, 15);

	public BlockStool(Material materialIn, float hardness, SoundType type, String unlocalizedName) {
		super(materialIn, hardness, type, unlocalizedName);
		setDefaultState(this.getDefaultState().withProperty(COLOR, 0));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { COLOR });
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityStool stool = (TileEntityStool) worldIn.getTileEntity(pos);
		
		if (playerIn.getHeldItem(hand) != ItemStack.field_190927_a) {
			ItemStack heldItem = playerIn.getHeldItem(hand);
			if (heldItem.getItem() instanceof ItemDye && state.getValue(COLOR) != 15 - heldItem.getItemDamage()) {
				stool.setColor(heldItem.getItemDamage());
				if (!playerIn.capabilities.isCreativeMode) {
					playerIn.getHeldItem(hand).func_190920_e(playerIn.getHeldItem(hand).func_190916_E() - 1);
				}
				return true;
			}
		}
		
		return SittableUtil.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, 0.351);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source,
			BlockPos pos) {
		return new AxisAlignedBB(2F / 16F, 0, 2F / 16F, 14F / 16F, 0.5, 14F / 16F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityStool();
	}
	
}
