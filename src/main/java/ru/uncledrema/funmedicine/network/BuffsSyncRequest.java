package ru.uncledrema.funmedicine.network;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import ru.uncledrema.funmedicine.entity.BuffsProvider;
import ru.uncledrema.funmedicine.entity.IBuffs;
import ru.uncledrema.funmedicine.main.ActiveBuff;
import ru.uncledrema.funmedicine.network.AbstractMessage.AbstractServerMessage;

public class BuffsSyncRequest extends AbstractServerMessage<BuffsSyncRequest> {
	
	public BuffsSyncRequest() {}

	@Override
	protected void writeData(PacketBuffer buffer) throws IOException {}

	@Override
	protected void readData(PacketBuffer buffer) throws IOException {}

	@Override
	public void performProcess(EntityPlayer player, Side side) {
		
		IBuffs buffs = player.getCapability(BuffsProvider.BUFFS_CAP, null);
		
    	if (buffs.haveActiveBuffs()) {
    		        	
			for (ActiveBuff buff : buffs.activeBuffsCollection()) {
        	    			
    			NetworkHandler.sendTo(new SyncBuff(buff), (EntityPlayerMP) player);
    		}
    	}	
	}
}
