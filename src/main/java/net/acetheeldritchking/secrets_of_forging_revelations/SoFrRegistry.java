package net.acetheeldritchking.secrets_of_forging_revelations;

import net.acetheeldritchking.item.ModularPolearm;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import se.mickelus.tetra.TetraRegistries;

public class SoFrRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SecretsOfForgingRevelations.MOD_ID);

    public static final RegistryObject<Item> ModularPolearm = ITEMS.register("modular_polearm", ModularPolearm::new);

}
