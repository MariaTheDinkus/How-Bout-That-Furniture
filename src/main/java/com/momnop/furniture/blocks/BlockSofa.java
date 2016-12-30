package com.momnop.furniture.blocks;

import java.util.List;

import mcjty.lib.tools.ItemStackTools;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
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

import com.momnop.furniture.network.MessageSofaData;
import com.momnop.furniture.network.PacketDispatcher;
import com.momnop.furniture.tiles.TileEntitySofa;
import com.momnop.furniture.utils.RotationUtils;
import com.momnop.furniture.utils.SittableUtil;

public class BlockSofa extends BlockFurnitureFacingColliding implements ITileEntityProvider {
	
	public static final PropertyInteger COLOR = PropertyInteger.create("color", 0, 15);
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");
	public static final PropertyBool CORNER = PropertyBool.create("corner");

	public BlockSofa(Material materialIn, float hardness, SoundType type, String unlocalizedName, CreativeTabs tab) {
		super(materialIn, hardness, type, unlocalizedName, tab);
		setDefaultState(this.getDefaultState().withProperty(COLOR, 0).withProperty(LEFT, false).withProperty(RIGHT, false).withProperty(CORNER, false));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, COLOR, LEFT, RIGHT, CORNER });
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean clOnBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntitySofa sofa = (TileEntitySofa) worldIn.getTileEntity(pos);
		
		if (playerIn.getHeldItem(hand) != ItemStackTools.getEmptyStack()) {
			ItemStack heldItem = playerIn.getHeldItem(hand);
			if (heldItem.getItem() instanceof ItemDye && sofa.getColor() != 15 - heldItem.getItemDamage()) {
				sofa.setColor(heldItem.getItemDamage());
				if (!playerIn.capabilities.isCreativeMode) {
					ItemStackTools.incStackSize(playerIn.getHeldItem(hand), -1);
				}
				if (worldIn.isRemote) {
					worldIn.setBlockState(pos, state.withProperty(COLOR, sofa.getColor()));
				}
				return true;
			}
		}
		
		return SittableUtil.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, 0.351);
	}
	
	public boolean isAdjacentBlockOfMyType(IBlockAccess world, BlockPos position, EnumFacing facing) {

        assert null != world : "world cannot be null";
        assert null != position : "position cannot be null";
        assert null != this : "type cannot be null";

        BlockPos newPosition = position.offset(facing);
        IBlockState blockState = world.getBlockState(newPosition);
        Block block = (null == blockState) ? null : blockState.getBlock();
        
        return this == block;
    }
	
	@Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos position) {
		state = state
				.withProperty(COLOR, state.getValue(COLOR))
                .withProperty(RIGHT, this.isAdjacentBlockOfMyType(world, position, state.getValue(FACING).rotateY()))
                .withProperty(LEFT, this.isAdjacentBlockOfMyType(world, position, state.getValue(FACING).rotateYCCW()));
        
        return state;
    }
	
	@Override
	public void addCollisionBoxToList(IBlockState state,
			World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		AxisAlignedBB bottom = new AxisAlignedBB(0, 0, 0, 1, 0.5F, 1);
		AxisAlignedBB top = new AxisAlignedBB(0, 0.5F, 13F / 16F, 1, 17F / 16F, 1);
		AxisAlignedBB side1 = new AxisAlignedBB(0 - (3F / 16F), 7F / 16F, 0, 1F / 16F, 12F / 16F, 1);
		AxisAlignedBB side2 = new AxisAlignedBB(15F / 16F, 7F / 16F, 0, 1 + (3F / 16F), 12F / 16F, 1);
		
		top = RotationUtils.getRotatedAABB(top, state.getValue(FACING));
		
		addCollisionBox(bottom, pos, collidingBoxes, entityBox);
		addCollisionBox(top, pos, collidingBoxes, entityBox);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySofa();
	}
	
}
