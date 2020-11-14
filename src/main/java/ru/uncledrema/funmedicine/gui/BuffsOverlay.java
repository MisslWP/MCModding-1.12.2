package ru.uncledrema.funmedicine.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import ru.uncledrema.funmedicine.entity.BuffsProvider;
import ru.uncledrema.funmedicine.entity.IBuffs;
import ru.uncledrema.funmedicine.main.ActiveBuff;
import ru.uncledrema.funmedicine.main.Buff;
import ru.uncledrema.funmedicine.main.BuffsMain;

public class BuffsOverlay {
	
	private static BuffsOverlay instance = new BuffsOverlay();

	private static Minecraft mc = Minecraft.getMinecraft();
	
    private static final ResourceLocation buffIcons = new ResourceLocation(BuffsMain.MODID + ":textures/gui/bufficons.png");
	
    private BuffsOverlay() {}
    
	public static BuffsOverlay getInstance() {
		
		return instance;
	}
	
	public void renderBuffs() {
    	
		if (mc.inGameHasFocus) {

			EntityPlayer player = mc.player;
    	
			IBuffs buffs = player.getCapability(BuffsProvider.BUFFS_CAP, null);
    	
			ScaledResolution scaledResolution = new ScaledResolution(mc);
        
			int
			i = scaledResolution.getScaledWidth() / 2 + 240,
			j = scaledResolution.getScaledHeight() - 30,
			counter = 0,
			index = 0;
    	
			if (buffs.haveActiveBuffs()) {
				
				//for (ActiveBuff buff : buffs.activeBuffsCollection()) {
				for (int iterator = 0; iterator < buffs.activeBuffsCollection().size(); iterator ++) {
					ActiveBuff buff = (ActiveBuff) buffs.activeBuffsCollection().toArray()[iterator];
					index = Buff.of(buff.getId()).getIconIndex();
            	
					counter++;    			
            	
			        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);					
					GL11.glEnable(GL11.GL_BLEND);
    			            	            	
					GL11.glPushMatrix();
            	
					GL11.glTranslatef(i + 5, j + 25 - 24 * counter, 0.0F);
					GL11.glScalef(0.5F, 0.5F, 0.5F);
            	
					mc.getTextureManager().bindTexture(buffIcons);
            		drawModalRectWithCustomSizedTexture(0, 0, index % 8 * 32, index / 8 * 32, 32, 32, 160, 32);
            	
            		GL11.glPopMatrix();      
            	
            		GL11.glPushMatrix();
            	
            		GL11.glTranslatef(i + 15, j + 42 - 24 * counter, 0.0F);          	            	
            		GL11.glScalef(0.7F, 0.7F, 0.7F);
            		
            		int durLength = Buff.of(buff.getId()).getDurationForDisplay(buff).length();
            	            	
            		mc.fontRenderer.drawStringWithShadow(Buff.of(buff.getId()).getDurationForDisplay(buff), - durLength * 3, 0, 8421504);
            	
            		String tier = "";
            	
            		if (buff.getTier() == 0) {
            		
            			tier = "I";
            		}
            	
            		else if (buff.getTier() == 1) {
            		
            			tier = "II";
            		} 
            	
            		else if (buff.getTier() == 2) {
            		
            			tier = "III";
            		}          	
            	
            		mc.fontRenderer.drawStringWithShadow(tier, 7 - tier.length() * 3, - 23, 8421504);
            		
            		GL11.glPopMatrix();
            	
            		GL11.glDisable(GL11.GL_BLEND);           	            	            	
				}
			}    
		}
    }
	
    public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
    	
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        
        Tessellator tessellator = Tessellator.getInstance();
        
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        
        bufferbuilder.pos(x, y + height, 0.0D).tex(u * f, (v + (float)height) * f1).endVertex();
        bufferbuilder.pos(x + width, y + height, 0.0D).tex((u + (float)width) * f, (v + (float)height) * f1).endVertex();
        bufferbuilder.pos(x + width, y, 0.0D).tex((u + (float)width) * f, v * f1).endVertex();
        bufferbuilder.pos(x, y, 0.0D).tex(u * f, v * f1).endVertex();
        
        tessellator.draw();
    }
}

