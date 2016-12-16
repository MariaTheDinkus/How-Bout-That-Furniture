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
  private boolean left;
  private boolean right;
  
  public MessageSofaData() {}
  
  public MessageSofaData(BlockPos pos, int color, boolean left, boolean right)
  {
	this.pos = pos;
    this.color = color;
    this.left = left;
    this.right = right;
  }
  
  @Override
  public void fromBytes(ByteBuf buf)
  {
	this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    this.color = buf.readInt();
    this.left = buf.readBoolean();
    this.right = buf.readBoolean();
  }
  
  @Override
  public void toBytes(ByteBuf buf)
  {
	buf.writeInt(pos.getX());
	buf.writeInt(pos.getY());
	buf.writeInt(pos.getZ());
	buf.writeInt(color);
	buf.writeBoolean(left);
	buf.writeBoolean(right);
  }
  
  public static class Handler
  extends AbstractClientMessageHandler<MessageSofaData>
{
  public IMessage handleClientMessage(EntityPlayer player, MessageSofaData message, MessageContext ctx)
  {
    if ((player != null) && (message != null) && (ctx != null) && player.worldObj.getBlockState(message.pos).getBlock() instanceof BlockSofa)
    {
    	if (!player.worldObj.isAirBlock(message.pos) && player.worldObj.getBlockState(message.pos).getBlock() instanceof BlockSofa) {
    		player.worldObj.setBlockState(message.pos, player.worldObj.getBlockState(message.pos).withProperty(BlockSofa.COLOR, message.color).withProperty(BlockSofa.LEFT, message.left).withProperty(BlockSofa.RIGHT, message.right));
    	}
    	
		return null;
    }
    return null;
  }
}
}
