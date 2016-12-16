package com.momnop.furniture.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

import com.momnop.furniture.blocks.BlockSofa;
import com.momnop.furniture.network.MessageSofaData;
import com.momnop.furniture.network.PacketDispatcher;

public class TileEntitySofa extends TileEntity implements ITickable {
	private int color = 0;
	
	public void setColor(int color) {
		this.color = 15 - color;
		this.markDirty();
	}
	
	public int getColor() {
		return color;
	}
	
	@Override
	public void update() {
		IBlockState state = this.getWorld().getBlockState(pos);
		
		if (this.getWorld().getBlockState(pos).getBlock() instanceof BlockSofa) {
			if (!this.getWorld().isRemote) {
				PacketDispatcher.sendToAll(new MessageSofaData(this.getPos(), color, state.getValue(BlockSofa.LEFT), state.getValue(BlockSofa.RIGHT)));
			}
			
			if (this.getWorld().getBlockState(pos.add(state.getValue(BlockSofa.FACING).rotateYCCW().getDirectionVec())) != null && this.getWorld().getBlockState(pos.add(state.getValue(BlockSofa.FACING).rotateYCCW().getDirectionVec())).getBlock() instanceof BlockSofa && this.getWorld().getBlockState(pos.add(state.getValue(BlockSofa.FACING).rotateYCCW().getDirectionVec())).getValue(BlockSofa.FACING) == state.getValue(BlockSofa.FACING)) {
				this.getWorld().setBlockState(pos.add(state.getValue(BlockSofa.FACING).rotateYCCW().getDirectionVec()), this.getWorld().getBlockState(pos.add(state.getValue(BlockSofa.FACING).rotateYCCW().getDirectionVec())).withProperty(BlockSofa.RIGHT, true), 2);
			}
			
			if (this.getWorld().getBlockState(pos.add(state.getValue(BlockSofa.FACING).rotateY().getDirectionVec())) != null && this.getWorld().getBlockState(pos.add(state.getValue(BlockSofa.FACING).rotateY().getDirectionVec())).getBlock() instanceof BlockSofa && this.getWorld().getBlockState(pos.add(state.getValue(BlockSofa.FACING).rotateY().getDirectionVec())).getValue(BlockSofa.FACING) == state.getValue(BlockSofa.FACING)) {
				this.getWorld().setBlockState(pos.add(state.getValue(BlockSofa.FACING).rotateY().getDirectionVec()), this.getWorld().getBlockState(pos.add(state.getValue(BlockSofa.FACING).rotateY().getDirectionVec())).withProperty(BlockSofa.LEFT, true), 2);
			}
			
			if (!(this.getWorld().getBlockState(pos.add(state.getValue(BlockSofa.FACING).rotateYCCW().getDirectionVec())).getBlock() instanceof BlockSofa) && !(this.getWorld().getBlockState(pos.add(state.getValue(BlockSofa.FACING).rotateY().getDirectionVec())).getBlock() instanceof BlockSofa) || this.getWorld().isAirBlock(pos.add(state.getValue(BlockSofa.FACING).rotateYCCW().getDirectionVec())) && this.getWorld().isAirBlock(pos.add(state.getValue(BlockSofa.FACING).rotateY().getDirectionVec()))){
				this.getWorld().setBlockState(pos, state.withProperty(BlockSofa.LEFT, false).withProperty(BlockSofa.RIGHT, false), 2);
			} else if (!(this.getWorld().getBlockState(pos.add(state.getValue(BlockSofa.FACING).rotateY().getDirectionVec())).getBlock() instanceof BlockSofa) || this.getWorld().isAirBlock(pos.add(state.getValue(BlockSofa.FACING).rotateY().getDirectionVec()))){
				this.getWorld().setBlockState(pos, state.withProperty(BlockSofa.RIGHT, false), 2);
			} else if (!(this.getWorld().getBlockState(pos.add(state.getValue(BlockSofa.FACING).rotateYCCW().getDirectionVec())).getBlock() instanceof BlockSofa) || this.getWorld().isAirBlock(pos.add(state.getValue(BlockSofa.FACING).rotateYCCW().getDirectionVec()))){
				this.getWorld().setBlockState(pos, state.withProperty(BlockSofa.LEFT, false), 2);
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.color = compound.getInteger("color");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("color", color);
		return compound;
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound compound = new NBTTagCompound();
		this.readFromNBT(compound);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound compound = new NBTTagCompound();
		this.writeToNBT(compound);
		return new SPacketUpdateTileEntity(this.getPos(), this.getBlockMetadata(), compound);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos,
			IBlockState oldState, IBlockState newSate) {
		return oldState.getBlock() != newSate.getBlock();
	}
}
