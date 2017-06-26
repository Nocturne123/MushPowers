package fr.alexandre1156.mushpowers.events;

import fr.alexandre1156.mushpowers.MushPowers;
import fr.alexandre1156.mushpowers.capabilities.IPlayerMush;
import fr.alexandre1156.mushpowers.capabilities.PlayerMushProvider;
import fr.alexandre1156.mushpowers.config.MushConfig;
import fr.alexandre1156.mushpowers.particle.ShroomParticle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentTranslation;

public class ShieldshroomEvent extends ShroomEvent {

	@Override
	protected float onLivingHurt(EntityLivingBase entLiv, DamageSource source, float amount) {
		if(entLiv instanceof EntityPlayer){
			EntityPlayer p = (EntityPlayer) entLiv;
			IPlayerMush mush = entLiv.getCapability(PlayerMushProvider.MUSH_CAP, null);
			float shieldTaken = amount / (100 / MushConfig.damageAbsordPercentShieldshroom);
			if(mush.getShieldDamage()-shieldTaken < 0){
				shieldTaken = mush.getShieldDamage();
				mush.setShieldDamageAbsorb(0f);
			} else
				mush.setShieldDamageAbsorb(mush.getShieldDamage()-shieldTaken);
			p.sendStatusMessage(new TextComponentTranslation("shieldshroom.damagetaken.message", shieldTaken), true);
			return (amount - shieldTaken);
		} else {
			IPlayerMush mush = entLiv.getCapability(PlayerMushProvider.MUSH_CAP, null);
			float shieldTaken = amount / (100 / MushConfig.damageAbsordPercentShieldshroom);
			if(mush.getShieldDamage()-shieldTaken < 0){
				shieldTaken = mush.getShieldDamage();
				mush.setShieldDamageAbsorb(0f);
			} else
				mush.setShieldDamageAbsorb(mush.getShieldDamage()-shieldTaken);
			return (amount - shieldTaken);
		}
		//return super.onLivingHurt(entLiv, source, amount);
	}
	
	@Override
	protected void onLivingUpdate(Entity ent, EntityLivingBase entLiv) {
		if(!(entLiv instanceof EntityPlayer))
			MushPowers.proxy.spawnShroomParticle(entLiv, ShroomParticle.SHIELD);
	}

	@Override
	protected void onPlayerCloned(EntityPlayer p, EntityPlayer pOriginal, boolean death) {
		if(!death){
			IPlayerMush mush = p.getCapability(PlayerMushProvider.MUSH_CAP, null);
			IPlayerMush mush2 = pOriginal.getCapability(PlayerMushProvider.MUSH_CAP, null);
			mush.setShieldDamageAbsorb(mush2.getShieldDamage());
		}
	}
	
}
