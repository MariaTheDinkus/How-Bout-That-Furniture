package com.momnop.furniture.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy
{    
	public void preInitRenders() {
		
	}
	
	public void initTiles() 
	{
        
	}
	
    public void initSounds()
    {
        
    }
    
    public void initRenders()
    {
        
    }
    
    public void initKeybinds()
    {
        
    }
    
    public EntityPlayer getPlayerEntity(MessageContext ctx)
    {
      return ctx.getServerHandler().playerEntity;
    }
}
