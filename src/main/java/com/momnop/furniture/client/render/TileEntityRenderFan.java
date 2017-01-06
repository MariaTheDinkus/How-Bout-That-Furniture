package com.momnop.furniture.client.render;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.momnop.furniture.blocks.BlockFan;
import com.momnop.furniture.blocks.FurnitureBlocks;
import com.momnop.furniture.client.models.FanModel;
import com.momnop.furniture.info.ModInfo;
import com.momnop.furniture.tiles.TileEntityCeilingFan;

public class TileEntityRenderFan extends TileEntitySpecialRenderer<TileEntityCeilingFan> {
	private final FanModel model;
	private float spinAmount = 0;
	private static float speed = 1;
	
	public TileEntityRenderFan()
	{
		this.model = new FanModel();
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public void renderTileEntityAt(TileEntityCeilingFan te, double x, double y,
			double z, float partialTicks, int destroyStage) {
		
		float rot = 360 * (te.rotation + (partialTicks)*te.perTick);
		
		if (te.getWorld().getBlockState(te.getPos()).getBlock() instanceof BlockFan) {
			IBlockState fan = te.getWorld().getBlockState(te.getPos());
			
			if (fan.getValue(BlockFan.POWERED)) {
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5, y, z + 0.5);
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ModInfo.MODID + ":" + "textures/models/fan.png"));
				GL11.glRotated(-rot, 0, 1, 0);
				model.renderModel();
				GL11.glPopMatrix();
			}
		}
		
		if (te.getWorld().getBlockState(te.getPos()).getBlock() == FurnitureBlocks.fanLightOn) {
			IBlockState fan = te.getWorld().getBlockState(te.getPos());
			
			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5, y, z + 0.5);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ModInfo.MODID + ":" + "textures/models/fan.png"));
			GL11.glRotated(-rot, 0, 1, 0);
			model.renderModel();
			GL11.glPopMatrix();
		}
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void clientTick(ClientTickEvent event) {
//		spinAmount = spinAmount + 10;
//		if (spinAmount >= 360) {
//			spinAmount = 0;
//		}
	}
	
	public static float lerp(float start, float end, float percent)
	{
	     return (start + percent*(end - start));
	}
}
