package ru.uncledrema.funmedicine.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.uncledrema.funmedicine.entity.BuffsProvider;
import ru.uncledrema.funmedicine.main.BuffsMain;

public class BuffsCupRegistrationEvent {

	public static final ResourceLocation BUFFS_CAP = new ResourceLocation(BuffsMain.MODID, "Buffs");
	 
    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent event) {
    	
        if (event.getObject() instanceof EntityLivingBase) {
        	
            event.addCapability(BUFFS_CAP, new BuffsProvider());
        }
    }
}
