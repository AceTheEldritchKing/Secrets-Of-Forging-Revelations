package net.acetheeldritchking.secrets_of_forging_revelations;


import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.acetheeldritchking.secrets_of_forging_revelations.item.ModularPolearm;
import se.mickelus.tetra.TetraRegistries;

public class SoFrRegistry extends TetraRegistries {
    //public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SecretsOfForgingRevelations.MOD_ID);

    //public static final RegistryObject<Item> ModularPolearm = ITEMS.register("modular_polearm", ModularPolearm::new);
    public static void init(IEventBus bus) {
        bus.register(SoFrRegistry.class);

        items.register(bus);

        // Modular Polearm
        items.register(ModularPolearm.identifier, ModularPolearm::new);
    }


}
