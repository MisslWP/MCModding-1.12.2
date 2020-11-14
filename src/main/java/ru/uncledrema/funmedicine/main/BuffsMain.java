package ru.uncledrema.funmedicine.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.uncledrema.funmedicine.items.ItemRegister;
import ru.uncledrema.funmedicine.proxy.CommonProxy;

@Mod(modid = BuffsMain.MODID, name = BuffsMain.NAME, version = BuffsMain.VERSION)
public class BuffsMain {
	
    public static final String MODID = "funmedicine";
    public static final String NAME = "TarkovCraft Medicine";
    public static final String VERSION = "1.0";
    
    @SidedProxy(clientSide = "ru.uncledrema.funmedicine.proxy.ClientProxy", serverSide = "ru.uncledrema.funmedicine.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static final CreativeTabs MEDICINE = new CreativeTabs("tut") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemRegister.healthRegenerationPotion);
        }
    };
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	proxy.preInit(event);

    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    	proxy.init(event); 	    
    }
}
