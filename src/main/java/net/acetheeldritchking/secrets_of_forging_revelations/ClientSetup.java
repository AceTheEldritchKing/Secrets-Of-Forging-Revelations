package net.acetheeldritchking.secrets_of_forging_revelations;

import net.acetheeldritchking.secrets_of_forging_revelations.effects.FreezingEffect;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "secrets_of_forging_revelations",
value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    @SubscribeEvent
    public static void ClientSetup(FMLClientSetupEvent event)
    {
        FreezingEffect.init();
    }
}
