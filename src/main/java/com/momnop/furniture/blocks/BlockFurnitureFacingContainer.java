package com.momnop.furniture.blocks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.momnop.furniture.Furniture;
import com.momnop.furniture.client.render.RenderRegistry;
import com.momnop.furniture.info.ModInfo;

public class BlockFurnitureFacingContainer extends BlockFurniture {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockFurnitureFacingContainer(Material materialIn, float hardness, SoundType type, String unlocalizedName, CreativeTabs tab) {
		super(materialIn, hardness, type, unlocalizedName, tab);
		setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
		setRegistryName(unlocalizedName);
        setUnlocalizedName(getRegistryName().toString().replace(ModInfo.MODID + ":", ""));
        RenderRegistry.registryBlocks.add(this);
        FurnitureBlocks.register(this);
        setCreativeTab(tab);
        setSoundType(type);
        setHardness(hardness);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.HORIZONTALS[meta]);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState blockState) {
		return false;
	}
	
	public void addCollisionBox(AxisAlignedBB box, List collidingBoxes, AxisAlignedBB entityBox)
	{
        if (box != null && entityBox.intersectsWith(box))
        {
            collidingBoxes.add(box);
        }
	}
	
	@Override
	protected IBlockState clGetStateForPlacement(World worldIn, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		if (placer.isSneaking()) {
			return this.getDefaultState().withProperty(FACING,
					placer.getHorizontalFacing());
		} else {
			return this.getDefaultState().withProperty(FACING,
					placer.getHorizontalFacing().getOpposite());
		}
	}
	
}
