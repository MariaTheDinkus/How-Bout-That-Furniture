package com.momnop.furniture.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.momnop.furniture.handlers.SoundHandler;
import com.momnop.furniture.tiles.TileEntitySofa;
import com.momnop.furniture.utils.RotationUtils;
import com.momnop.furniture.utils.SittableUtil;

public class BlockRockPath extends BlockFurniture {
	
	public static final PropertyInteger RANDOM = PropertyInteger.create("random", 0, 7);

	public BlockRockPath(Material materialIn, float hardness, SoundType type, String unlocalizedName) {
		super(materialIn, hardness, type, unlocalizedName);
		setDefaultState(this.getDefaultState().withProperty(RANDOM, 0));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(RANDOM);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(RANDOM, meta);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { RANDOM });
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source,
			BlockPos pos) {
		AxisAlignedBB rock = new AxisAlignedBB(1F / 16F, 0, 1F / 16F, 15F / 16F, 0.5F / 16F, 15F / 16F);
		
		return rock;
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(RANDOM, new Random().nextInt(7));
	}
	
}
