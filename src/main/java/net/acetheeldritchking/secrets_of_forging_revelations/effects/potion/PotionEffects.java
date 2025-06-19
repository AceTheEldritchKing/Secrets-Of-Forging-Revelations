package net.acetheeldritchking.secrets_of_forging_revelations.effects.potion;

import net.acetheeldritchking.secrets_of_forging_revelations.SecretsOfForgingRevelations;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class PotionEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,
                    SecretsOfForgingRevelations.MOD_ID);

    // Stole from Tetrutils with permission from Panda <3
    // Freezing Potion Effect
    public static final RegistryObject<MobEffect> FREEZING =
            MOB_EFFECTS.register("freezing", FreezingPotionEffect::new);

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

    public static MobEffectInstance getPotionEffect(ArrayList<MobEffectInstance> effects,
                                                    MobEffect effectType) {
        for (var effect : effects) {
            if (effect.getEffect().equals(effectType)) {
                return effect;
            }
        }
        return null;
    }
}
