package fr.alexandre1156.mushpowers.items.shrooms;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.alexandre1156.mushpowers.particle.ShroomParticle;
import fr.alexandre1156.mushpowers.proxy.CommonProxy.Mushs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class Randomshroom extends ItemMushPowers {

	public Randomshroom() {
		super(1, 0.0f, "randomshroom");
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if(!worldIn.isRemote)
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(this.itemRand.nextInt(27)+1), (this.itemRand.nextInt(30)+1)*20, this.itemRand.nextInt(3), false, true));
		super.onFoodEaten(stack, worldIn, player);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ChatFormatting.WHITE+"Give a random potion effect with random duration and random amplifier.");
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public TextFormatting getColorName() {
		return TextFormatting.YELLOW;	
	}

	@Override
	public boolean onUsedOnLivingEntity(World world, EntityLivingBase entLiv, EntityPlayer player) {
		if(!world.isRemote)
			entLiv.addPotionEffect(new PotionEffect(Potion.getPotionById(this.itemRand.nextInt(27)+1), (this.itemRand.nextInt(30)+1)*20, this.itemRand.nextInt(3), false, true));
		return true;
	}

	@Override
	public ShroomParticle getParticleOnLivingEntity() {
		return null;	
	}

	@Override
	public boolean isEntityLivingCompatible() {
		return true;
	}

	@Override
	protected Mushs getMushType() {
		return Mushs.RANDOM;
	}

}
