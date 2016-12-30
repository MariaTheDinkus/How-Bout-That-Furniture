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

public class BlockFurnitureFacingColliding extends BlockFurnitureFacing {

	public BlockFurnitureFacingColliding(Material materialIn, float hardness, SoundType type, String unlocalizedName, CreativeTabs tab) {
		super(materialIn, hardness, type, unlocalizedName, tab);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	public void addCollisionBox(AxisAlignedBB box, BlockPos pos,
			List collidingBoxes, AxisAlignedBB entityBox) {
		if (box != null && entityBox.intersectsWith(box.offset(pos))) {
			boolean add = true;
			collidingBoxes.add(box.offset(pos));
		}
	}

}
