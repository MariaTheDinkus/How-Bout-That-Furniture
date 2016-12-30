package com.momnop.furniture;

import mcjty.lib.compat.CompatCreativeTabs;
import mcjty.lib.tools.EntityTools;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
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
import net.minecraftforge.fml.relauncher.SideOnly;

import com.momnop.furniture.blocks.FurnitureBlocks;
import com.momnop.furniture.client.render.RenderRegistry;
import com.momnop.furniture.entity.EntitySittableBlock;
import com.momnop.furniture.handlers.FurnitureEventHandler;
import com.momnop.furniture.handlers.RecipeHandler;
import com.momnop.furniture.handlers.SoundHandler;
import com.momnop.furniture.info.ModInfo;
import com.momnop.furniture.network.PacketDispatcher;
import com.momnop.furniture.proxies.CommonProxy;
import com.momnop.furniture.tiles.TileEntityCabinet;
import com.momnop.furniture.tiles.TileEntityChair;
import com.momnop.furniture.tiles.TileEntityFan;
import com.momnop.furniture.tiles.TileEntityRefrigerator;
import com.momnop.furniture.tiles.TileEntityRefrigeratorTop;
import com.momnop.furniture.tiles.TileEntitySofa;

@Mod(name = ModInfo.NAME, modid = ModInfo.MODID, version = ModInfo.VERSION, acceptedMinecraftVersions = "[1.9,1.12)", dependencies = "required-after:compatlayer")
public class Furniture
{
    @SidedProxy(clientSide = "com.momnop.furniture.proxies.ClientProxy", serverSide = "com.momnop.furniture.proxies.CommonProxy")
    public static CommonProxy proxy;
    
    @Instance(value = ModInfo.MODID)
    public static Furniture INSTANCE;
    
    public static CreativeTabs tabGeneral = new CompatCreativeTabs(ModInfo.MODID + "_general") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getItem() {
            return Item.getItemFromBlock(FurnitureBlocks.tableOak);
        }
    };
    
    public static CreativeTabs tabLiving = new CompatCreativeTabs(ModInfo.MODID + "_living") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getItem() {
            return Item.getItemFromBlock(FurnitureBlocks.sofa);
        }
    };
    
    public static CreativeTabs tabKitchen = new CompatCreativeTabs(ModInfo.MODID + "_kitchen") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getItem() {
            return Item.getItemFromBlock(FurnitureBlocks.refrigerator);
        }
    };
    
    public static CreativeTabs tabOutdoors = new CompatCreativeTabs(ModInfo.MODID + "_outdoors") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getItem() {
            return Item.getItemFromBlock(FurnitureBlocks.fancyFence);
        }
    };
    
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
    	GameRegistry.registerTileEntity(TileEntityFan.class, "tileEntityFan");
    	GameRegistry.registerTileEntity(TileEntityRefrigerator.class, "tileEntityRefrigerator");
    	GameRegistry.registerTileEntity(TileEntityRefrigeratorTop.class, "tileEntityRefrigeratorTop");
    	GameRegistry.registerTileEntity(TileEntityCabinet.class, "tileEntityCabinet");
    	if (event.getSide() == Side.CLIENT) {
    		RenderRegistry.registerRenderers();
    	}
    	
    	proxy.initRenders();
    	
    	EntityTools.registerModEntity(new ResourceLocation(ModInfo.MODID, "mountable_block"), EntitySittableBlock.class, "mountable_block", 0, this, 80, 1, false);
    	
    	SoundHandler.registerSounds();
    	
    	PacketDispatcher.registerPackets();
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
