package com.momnop.furniture;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import com.momnop.furniture.blocks.FurnitureBlocks;
import com.momnop.furniture.client.render.RenderRegistry;
import com.momnop.furniture.handlers.FurnitureEventHandler;
import com.momnop.furniture.handlers.RecipeHandler;
import com.momnop.furniture.handlers.SoundHandler;
import com.momnop.furniture.info.ModInfo;
import com.momnop.furniture.network.PacketDispatcher;
import com.momnop.furniture.proxies.CommonProxy;
import com.momnop.furniture.tiles.TileEntityChair;
import com.momnop.furniture.tiles.TileEntitySofa;
import com.momnop.furniture.tiles.TileEntityStool;

@Mod(name = ModInfo.NAME, modid = ModInfo.MODID, version = ModInfo.VERSION, acceptedMinecraftVersions = "[1.11]")
public class Furniture
{
    @SidedProxy(clientSide = "com.momnop.furniture.proxies.ClientProxy", serverSide = "com.momnop.furniture.proxies.CommonProxy")
    public static CommonProxy proxy;
    
    @Instance(value = ModInfo.MODID)
    public static Furniture INSTANCE;
    
    @EventHandler
	public void onMissingMapping(FMLMissingMappingsEvent event) {
		
	}

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	FurnitureBlocks.load();
    	RecipeHandler.doRecipes();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new FurnitureEventHandler());
    	GameRegistry.registerTileEntity(TileEntitySofa.class, "tileEntitySofa");
    	GameRegistry.registerTileEntity(TileEntityChair.class, "tileEntityChair");
    	GameRegistry.registerTileEntity(TileEntityStool.class, "tileEntityStool");
    	if (event.getSide() == Side.CLIENT) {
    		RenderRegistry.registerRenderers();
    	}
    	
    	PacketDispatcher.registerPackets();
    	
    	SoundHandler.registerSounds();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        
    }
    
    /**
     * @param event
     *        The event that triggered the method
     */
    @EventHandler
    public static void serverLoad(FMLServerStartingEvent event)
    {
    	
    }
}
