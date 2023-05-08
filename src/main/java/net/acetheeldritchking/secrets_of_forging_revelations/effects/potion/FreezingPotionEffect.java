package net.acetheeldritchking.secrets_of_forging_revelations.effects.potion;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Random;

// Stole from Tetrutils with permission from Panda <3
public class FreezingPotionEffect extends MobEffect {
    // Random random = new Random();

    public FreezingPotionEffect()
    {
        super(MobEffectCategory.HARMFUL, 0xeeeeee);

        addAttributeModifier(Attributes.MOVEMENT_SPEED, "c2e930ec-9683-4bd7-bc04-8e6ff6587def", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL);
        addAttributeModifier(Attributes.ATTACK_SPEED, "e9590c57-94c8-468b-a149-a41743015e2c", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL);
        addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "385e50b0-a0c1-4653-9be4-a375ec031d51", 0.1, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level.isClientSide())
        {
            double x = pLivingEntity.getX();
            double y = pLivingEntity.getY();
            double z = pLivingEntity.getZ();

            pLivingEntity.setIsInPowderSnow(true);
            ServerLevel level = (ServerLevel) pLivingEntity.getLevel();
            pAmplifier = Math.min(pAmplifier, 10);

            level.sendParticles(ParticleTypes.SNOWFLAKE, x, y, z, pAmplifier*2, 0.1, 0.1, 0.1, 0.01);
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public double getAttributeModifierValue(int pAmplifier, AttributeModifier pModifier) {
        return pModifier.getAmount();
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
