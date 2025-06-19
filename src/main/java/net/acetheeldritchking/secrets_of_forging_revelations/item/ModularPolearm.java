package net.acetheeldritchking.secrets_of_forging_revelations.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;
import org.jetbrains.annotations.Nullable;
import se.mickelus.mutil.network.PacketHandler;
import se.mickelus.tetra.ConfigHandler;
import se.mickelus.tetra.data.DataManager;
import se.mickelus.tetra.gui.GuiModuleOffsets;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.ItemModularHandheld;
import se.mickelus.tetra.module.SchematicRegistry;
import se.mickelus.tetra.module.schematic.RepairSchematic;

public class ModularPolearm extends ItemModularHandheld {
    // Keys for the different parts of the polearm
    public final static String headKey = "polearm/head";
    public final static String handleKey = "polearm/handle";
    public final static String bindingKey = "polearm/binding";
    public static final String identifier = "modular_polearm";

    private static final GuiModuleOffsets majorOffsets = new GuiModuleOffsets(1, -3, -11, 21);
    private static final GuiModuleOffsets minorOffsets = new GuiModuleOffsets(-14, 0);

    @ObjectHolder(registryName = "item", value = "tetra:modular_polearm")
    public static ModularPolearm instance;

    public ModularPolearm() {
        super(new Item.Properties().stacksTo(1).fireResistant());
        entityHitDamage = 1;

        majorModuleKeys = new String[] { headKey, handleKey };
        minorModuleKeys = new String[] { bindingKey };

        requiredModules = new String[] { headKey, handleKey };

        updateConfig(ConfigHandler.honeSingleBase.get(), ConfigHandler.honeSingleIntegrityMultiplier.get());

        SchematicRegistry.instance.registerSchematic(new RepairSchematic(this, identifier));
    }

    public void updateConfig(int honeBase, int honeIntegrityMultiplier) {
        this.honeBase = honeBase;
        this.honeIntegrityMultiplier = honeIntegrityMultiplier;
    }

    @Override
    public void commonInit(PacketHandler packetHandler) {
        DataManager.instance.synergyData.onReload(() -> synergies = DataManager.instance.synergyData.getOrdered("polearm/"));
    }

    @Override
    public String getModelCacheKey(ItemStack itemStack, LivingEntity entity) {
        if (isThrowing(itemStack, entity)) {
            return super.getModelCacheKey(itemStack, entity) + ":throwing";

        }
        return super.getModelCacheKey(itemStack, entity);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public String getTransformVariant(ItemStack itemStack, @Nullable LivingEntity entity) {
        if (isThrowing(itemStack, entity)) {
            return "throwing";
        }
        return null;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GuiModuleOffsets getMajorGuiOffsets(ItemStack itemStack) {
        return majorOffsets;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GuiModuleOffsets getMinorGuiOffsets(ItemStack itemStack) {
        return minorOffsets;
    }

    public static ItemStack setupPolearm() {
        ItemStack itemStack = new ItemStack(instance);
        IModularItem.putModuleInSlot(itemStack, headKey, "polearm/spearhead", "polearm/spearhead_material", "spearhead/iron");
        IModularItem.putModuleInSlot(itemStack, handleKey, "polearm/basic_handle", "polearm/basic_handle_material", "basic_handle/oak");
        IModularItem.updateIdentifier(itemStack);

        return itemStack;
    }
}
