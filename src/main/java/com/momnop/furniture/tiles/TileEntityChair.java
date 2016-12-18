package com.momnop.furniture.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.momnop.furniture.blocks.BlockChair;
import com.momnop.furniture.blocks.BlockSofa;
import com.momnop.furniture.network.MessageChairData;
import com.momnop.furniture.network.PacketDispatcher;

public class TileEntityChair extends TileEntity implements ITickable {
	private int color = 0;
	
	public void setColor(int color) {
		this.color = 15 - color;
		this.markDirty();
	}
	
	public int getColor() {
		return color;
	}
	
	public void update() {
		if (this.getWorld().getBlockState(pos).getBlock() instanceof BlockChair) {
			if (!this.getWorld().isRemote) {
				PacketDispatcher.sendToAll(new MessageChairData(pos, getColor()));
			}
			BlockChair chair = (BlockChair) this.getWorld().getBlockState(pos).getBlock();
			this.getWorld().setBlockState(pos, chair.getActualState(this.getWorld().getBlockState(pos), this.getWorld(), pos));
		}
	};
	
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
