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
import com.momnop.furniture.blocks.FurnitureBlocks;
import com.momnop.furniture.network.MessageChairData;
import com.momnop.furniture.network.MessageSofaData;
import com.momnop.furniture.network.PacketDispatcher;

public class TileEntityCeilingFan extends TileEntity implements ITickable {
	public float prevRotation=0;
	public float rotation=0;
	public float turnSpeed=0;
	public float perTick = 0;
	
	public void update() {
		turnSpeed = 1;
		
		double mod = 0.075;
		
		prevRotation = (float) (turnSpeed*mod);
		rotation += turnSpeed*mod;
		rotation %= 1;
		perTick = (float) (turnSpeed*mod);
	};
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.rotation = compound.getInteger("rotation");
		this.turnSpeed = compound.getInteger("turnSpeed");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setFloat("rotation", rotation);
		compound.setFloat("turnSpeed", turnSpeed);
		return compound;
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound compound = new NBTTagCompound();
		this.writeToNBT(compound);
		return new SPacketUpdateTileEntity(this.getPos(), this.getBlockMetadata(), compound);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound compound = pkt.getNbtCompound();
		this.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos,
			IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}
}
