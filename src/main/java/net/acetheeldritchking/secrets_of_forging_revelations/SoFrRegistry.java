package net.acetheeldritchking.secrets_of_forging_revelations;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.acetheeldritchking.secrets_of_forging_revelations.item.ModularPolearm;
import se.mickelus.tetra.TetraMod;

public class SoFrRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TetraMod.MOD_ID);

    public static void init(IEventBus bus) {
        ITEMS.register(bus);

        // Modular Polearm
        ITEMS.register(ModularPolearm.identifier, ModularPolearm::new);
    }

}