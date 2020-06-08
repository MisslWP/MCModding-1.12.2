package ru.uncledrema.funmedicine.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.uncledrema.funmedicine.items.ItemRegister;
import ru.uncledrema.funmedicine.main.BuffsMain;

public class ClientProxy extends CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {

    	super.preInit(event);
    	
    	ItemRegister.registerRender();
    }

    public void init(FMLInitializationEvent event) {

    	super.init(event);   	
    	
		ModelBakery.registerItemVariants(ItemRegister.healthRegenerationPotion, 
				new ResourceLocation(BuffsMain.MODID, "heal_small"),
				new ResourceLocation(BuffsMain.MODID, "heal_medium"),
				new ResourceLocation(BuffsMain.MODID, "heal_big"));
    }
    
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		
	    return (ctx.side.isClient() ? Minecraft.getMinecraft().player : super.getPlayerEntity(ctx));
	}
}
