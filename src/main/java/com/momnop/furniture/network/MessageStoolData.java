package com.momnop.furniture.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.momnop.furniture.blocks.BlockStool;

public class MessageStoolData
  implements IMessage
{
  private BlockPos pos;
  private int color;
  
  public MessageStoolData() {}
  
  public MessageStoolData(BlockPos pos, int color)
  {
	this.pos = pos;
    this.color = color;
  }
  
  @Override
  public void fromBytes(ByteBuf buf)
  {
	this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    this.color = buf.readInt();
  }
  
  @Override
  public void toBytes(ByteBuf buf)
  {
	buf.writeInt(pos.getX());
	buf.writeInt(pos.getY());
	buf.writeInt(pos.getZ());
	buf.writeInt(color);
  }
  
  public static class Handler
  extends AbstractClientMessageHandler<MessageStoolData>
{
  public IMessage handleClientMessage(EntityPlayer player, MessageStoolData message, MessageContext ctx)
  {
    if ((player != null) && (message != null) && (ctx != null) && player.worldObj.getBlockState(message.pos).getBlock() instanceof BlockStool)
    {
    	if (!player.worldObj.isAirBlock(message.pos) && player.worldObj.getBlockState(message.pos).getBlock() instanceof BlockStool) {
    		player.worldObj.setBlockState(message.pos, player.worldObj.getBlockState(message.pos).withProperty(BlockStool.COLOR, message.color));
    	}
    	
		return null;
    }
    return null;
  }
}
}
