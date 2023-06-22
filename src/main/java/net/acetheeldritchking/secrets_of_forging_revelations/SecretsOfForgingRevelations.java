package net.acetheeldritchking.secrets_of_forging_revelations;

import com.mojang.logging.LogUtils;
import net.acetheeldritchking.secrets_of_forging_revelations.effects.FlameEffect;
import net.acetheeldritchking.secrets_of_forging_revelations.effects.FreezingEffect;
import net.acetheeldritchking.secrets_of_forging_revelations.effects.potion.PotionEffects;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SecretsOfForgingRevelations.MOD_ID)
public class SecretsOfForgingRevelations
{
    public static final String MOD_ID = "secrets_of_forging_revelations";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public SecretsOfForgingRevelations()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        SoFrRegistry.init(FMLJavaModLoadingContext.get().getModEventBus());

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

        // Potion Effects
        PotionEffects.register(eventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        // Freezing
        MinecraftForge.EVENT_BUS.register(new FreezingEffect());
        // Infernal
        MinecraftForge.EVENT_BUS.register(new FlameEffect());
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // nothing here
    }
}
