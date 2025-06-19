package net.acetheeldritchking.secrets_of_forging_revelations;

import com.mojang.logging.LogUtils;
import net.acetheeldritchking.secrets_of_forging_revelations.effects.BlizzardEffect;
import net.acetheeldritchking.secrets_of_forging_revelations.effects.FlameEffect;
import net.acetheeldritchking.secrets_of_forging_revelations.effects.FreezingEffect;
import net.acetheeldritchking.secrets_of_forging_revelations.effects.potion.PotionEffects;
import net.acetheeldritchking.secrets_of_forging_revelations.item.ModularPolearm;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SecretsOfForgingRevelations.MOD_ID)
public class SecretsOfForgingRevelations
{
    public static final String MOD_ID = "secrets_of_forging_revelations";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SecretsOfForgingRevelations(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Register items
        SoFrRegistry.init(FMLJavaModLoadingContext.get().getModEventBus());

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Potion Effects
        PotionEffects.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::onBuildContents);

        // Freezing
        MinecraftForge.EVENT_BUS.register(new FreezingEffect());
        // Infernal
        MinecraftForge.EVENT_BUS.register(new FlameEffect());
        // Blizzard
        MinecraftForge.EVENT_BUS.register(new BlizzardEffect());
    }

    private void onBuildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModularPolearm.setupPolearm());
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Nothing here.
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
