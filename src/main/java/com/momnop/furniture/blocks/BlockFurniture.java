package com.momnop.furniture.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;

import com.momnop.furniture.FurnitureCreativeTab;
import com.momnop.furniture.client.render.RenderRegistry;
import com.momnop.furniture.info.ModInfo;

public class BlockFurniture extends Block {

	public BlockFurniture(Material materialIn, float hardness, SoundType type, String unlocalizedName) {
		super(materialIn);
		setRegistryName(unlocalizedName);
        setUnlocalizedName(getRegistryName().toString().replace(ModInfo.MODID + ":", ""));
        RenderRegistry.registryBlocks.add(this);
        FurnitureBlocks.register(this);
        setCreativeTab(FurnitureCreativeTab.INSTANCE);
        setSoundType(type);
        setHardness(hardness);
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
	
}
