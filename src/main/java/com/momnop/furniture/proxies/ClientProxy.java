package com.momnop.furniture.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.momnop.furniture.client.render.TileEntityRenderFan;
import com.momnop.furniture.tiles.TileEntityCeilingFan;

public class ClientProxy extends CommonProxy
{
	
	public void preInitRenders() {
		
	}
	
    public void initSounds()
    {
        
    }
    
    public void initRenders()
    {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCeilingFan.class, new TileEntityRenderFan());
    }
    
    public void initKeybinds()
    {
    	
    }
    
    public EntityPlayer getPlayerEntity(MessageContext ctx)
    {
      return ctx.side.isClient() ? Minecraft.getMinecraft().player : super.getPlayerEntity(ctx);
    }
}
