package ru.uncledrema.funmedicine.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import ru.uncledrema.funmedicine.main.BuffsMain;

import java.util.Objects;

public class MedicineItem extends ItemFood {
    private int uses, potionId, duration, amplifier;
    private boolean hasPotionEffect;
    public MedicineItem(int uses, int stackSize, String name) {
        super(0, 0f, false);
        this.setAlwaysEdible();
        this.uses = uses;
        this.setMaxDamage(uses);
        this.setCreativeTab(CreativeTabs.BREWING);
        this.setMaxStackSize(stackSize);
        this.setRegistryName(new ResourceLocation(BuffsMain.MODID, name));
        this.setUnlocalizedName(name);
        hasPotionEffect = false;
    }
    public MedicineItem(int uses, int stackSize, String name, int potionId, int duration, int amplifier)
    {
        this(uses, stackSize, name);
        this.potionId = potionId;
        this.duration = duration;
        this.amplifier = amplifier;
        hasPotionEffect = true;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityLiving;
            if (hasPotionEffect) {
                player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(potionId)), duration, amplifier, true, false));
            }
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));

            if (entityplayer instanceof EntityPlayerMP)
            {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
            }
        }

        System.out.println(stack.getItemDamage());
        stack.damageItem(1, entityLiving);

        if (stack.getItemDamage() == uses) {
            stack.shrink(1);
        }
        System.out.println(stack.getItemDamage());
        return stack;
    }

}
