package com.momnop.furniture.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.momnop.furniture.blocks.BlockSofa;

public class MessageSofaData
  implements IMessage
{
  private BlockPos pos;
  private int color;
  
  public MessageSofaData() {}
  
  public MessageSofaData(BlockPos pos, int color)
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
  extends AbstractClientMessageHandler<MessageSofaData>
{
  public IMessage handleClientMessage(EntityPlayer player, MessageSofaData message, MessageContext ctx)
  {
    if ((player != null) && (message != null) && (ctx != null) && player.getEntityWorld().getBlockState(message.pos).getBlock() instanceof BlockSofa)
    {
    	if (!player.getEntityWorld().isAirBlock(message.pos) && player.getEntityWorld().getBlockState(message.pos).getBlock() instanceof BlockSofa) {
    		player.getEntityWorld().setBlockState(message.pos, player.getEntityWorld().getBlockState(message.pos).withProperty(BlockSofa.COLOR, message.color));
    	}
    	
		return null;
    }
    return null;
  }
}
}
