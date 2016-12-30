package com.momnop.furniture.blocks;

import java.util.List;

import mcjty.lib.compat.CompatBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;

import com.momnop.furniture.Furniture;
import com.momnop.furniture.client.render.RenderRegistry;
import com.momnop.furniture.info.ModInfo;

public class BlockNormal extends CompatBlock {

	public BlockNormal(Material materialIn, float hardness, SoundType type, String unlocalizedName, CreativeTabs tab) {
		super(materialIn);
		setRegistryName(unlocalizedName);
        setUnlocalizedName(getRegistryName().toString().replace(ModInfo.MODID + ":", ""));
        RenderRegistry.registryBlocks.add(this);
        FurnitureBlocks.register(this);
        setCreativeTab(tab);
        setSoundType(type);
        setHardness(hardness);
	}
	
}
