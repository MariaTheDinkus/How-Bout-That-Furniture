package com.momnop.furniture.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * fan_blades by Unknown
 */
@SideOnly(Side.CLIENT)
public class FanModel extends ModelBase {
    public ModelRenderer Blade1;
    public ModelRenderer Blade2;
    public ModelRenderer Blade3;
    public ModelRenderer Blade4;

    public FanModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.Blade1 = new ModelRenderer(this, 0, 0);
        this.Blade1.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.Blade1.addBox(-12.0F, 3.0F, -2.5F, 12, 1, 2);
        this.setRotationAngles(this.Blade1, -0.39269908169872414F, -0.0F, -0.0F);
        this.Blade2 = new ModelRenderer(this, 0, 3);
        this.Blade2.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.Blade2.addBox(0.5F, 3.0F, -12.0F, 2, 1, 12);
        this.setRotationAngles(this.Blade2, -0.0F, -0.0F, -0.39269908169872414F);
        this.Blade3 = new ModelRenderer(this, 0, 0);
        this.Blade3.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.Blade3.addBox(0.0F, 3.0F, 0.5F, 12, 1, 2);
        this.setRotationAngles(this.Blade3, 0.39269908169872414F, -0.0F, -0.0F);
        this.Blade4 = new ModelRenderer(this, 0, 3);
        this.Blade4.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.Blade4.addBox(-2.5F, 3.0F, 0.0F, 2, 1, 12);
        this.setRotationAngles(this.Blade4, -0.0F, -0.0F, 0.39269908169872414F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Blade1.offsetX, this.Blade1.offsetY, this.Blade1.offsetZ);
        GlStateManager.translate(this.Blade1.rotationPointX * scale, this.Blade1.rotationPointY * scale, this.Blade1.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.Blade1.offsetX, -this.Blade1.offsetY, -this.Blade1.offsetZ);
        GlStateManager.translate(-this.Blade1.rotationPointX * scale, -this.Blade1.rotationPointY * scale, -this.Blade1.rotationPointZ * scale);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.Blade1.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Blade2.offsetX, this.Blade2.offsetY, this.Blade2.offsetZ);
        GlStateManager.translate(this.Blade2.rotationPointX * scale, this.Blade2.rotationPointY * scale, this.Blade2.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.Blade2.offsetX, -this.Blade2.offsetY, -this.Blade2.offsetZ);
        GlStateManager.translate(-this.Blade2.rotationPointX * scale, -this.Blade2.rotationPointY * scale, -this.Blade2.rotationPointZ * scale);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.Blade2.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Blade3.offsetX, this.Blade3.offsetY, this.Blade3.offsetZ);
        GlStateManager.translate(this.Blade3.rotationPointX * scale, this.Blade3.rotationPointY * scale, this.Blade3.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.Blade3.offsetX, -this.Blade3.offsetY, -this.Blade3.offsetZ);
        GlStateManager.translate(-this.Blade3.rotationPointX * scale, -this.Blade3.rotationPointY * scale, -this.Blade3.rotationPointZ * scale);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.Blade3.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Blade4.offsetX, this.Blade4.offsetY, this.Blade4.offsetZ);
        GlStateManager.translate(this.Blade4.rotationPointX * scale, this.Blade4.rotationPointY * scale, this.Blade4.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.Blade4.offsetX, -this.Blade4.offsetY, -this.Blade4.offsetZ);
        GlStateManager.translate(-this.Blade4.rotationPointX * scale, -this.Blade4.rotationPointY * scale, -this.Blade4.rotationPointZ * scale);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.Blade4.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    /**
     * Renders the sign model through TileEntitySignRenderer
     */
    public void renderModel()
    {
    	GlStateManager.pushMatrix();
    	GlStateManager.translate(0, -6F / 16F, 0);
    	GlStateManager.pushMatrix();
    	GlStateManager.translate(0, 0.1 / 16, (2.75F / 16F));
        this.Blade1.render(0.0625F);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
    	GlStateManager.translate((-2.75F / 16F), 0.1 / 16, 0);
        this.Blade2.render(0.0625F);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
    	GlStateManager.translate(0, 0.1 / 16, (-2.75F / 16F));
        this.Blade3.render(0.0625F);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
    	GlStateManager.translate((2.75F / 16F), 0.1 / 16, 0);
        this.Blade4.render(0.0625F);
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
    }
}
