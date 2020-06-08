package ru.uncledrema.funmedicine.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.uncledrema.funmedicine.entity.Buffs;
import ru.uncledrema.funmedicine.entity.BuffsStorage;
import ru.uncledrema.funmedicine.entity.IBuffs;
import ru.uncledrema.funmedicine.events.BuffsCupRegistrationEvent;
import ru.uncledrema.funmedicine.events.BuffsEvents;
import ru.uncledrema.funmedicine.items.ItemRegister;
import ru.uncledrema.funmedicine.network.NetworkHandler;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
    	
    	NetworkHandler.registerPackets();
    	
    	ItemRegister.register(); 	
    }

    public void init(FMLInitializationEvent event) {   	
    	
    	CapabilityManager.INSTANCE.register(IBuffs.class, new BuffsStorage(), Buffs.class);
    	
    	MinecraftForge.EVENT_BUS.register(new BuffsCupRegistrationEvent());
    	MinecraftForge.EVENT_BUS.register(new BuffsEvents());
    }
    
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		
		return ctx.getServerHandler().player;
	}
}
