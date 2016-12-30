package com.momnop.furniture.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
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
import net.minecraftforge.common.MinecraftForge;

import com.momnop.furniture.tiles.TileEntityCabinet;
import com.momnop.furniture.utils.RotationUtils;

public class BlockCabinet extends BlockFurnitureFacing implements ITileEntityProvider {

	public BlockCabinet(Material materialIn, float hardness, SoundType type, String unlocalizedName, CreativeTabs tab) {
		super(materialIn, hardness, type, unlocalizedName, tab);
		setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
	
	@Override
	public boolean clOnBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			EnumFacing heldItem, float side, float hitX, float hitY) {
		if (worldIn.getTileEntity(pos) instanceof TileEntityCabinet) {
			TileEntityCabinet cabinet = (TileEntityCabinet) worldIn.getTileEntity(pos);
			playerIn.displayGUIChest((IInventory) cabinet);
			return true;
		}
		return false;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
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
		AxisAlignedBB back = new AxisAlignedBB(0, 0F, 11.5F / 16F, 1, 1, 1);
		
		back = RotationUtils.getRotatedAABB(back, state.getValue(FACING).getOpposite());
		
		return back;
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
		return new TileEntityCabinet();
	}
	
}
