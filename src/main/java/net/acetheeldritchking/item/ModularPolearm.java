package net.acetheeldritchking.item;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;
import se.mickelus.mutil.network.PacketHandler;
import se.mickelus.tetra.ConfigHandler;
import se.mickelus.tetra.TetraMod;
import se.mickelus.tetra.data.DataManager;
import se.mickelus.tetra.gui.GuiModuleOffsets;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.ItemModularHandheld;
import se.mickelus.tetra.module.SchematicRegistry;
import se.mickelus.tetra.module.schematic.RemoveSchematic;
import se.mickelus.tetra.module.schematic.RepairSchematic;
import net.acetheeldritchking.secrets_of_forging_revelations.SecretsOfForgingRevelations;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class ModularPolearm extends ItemModularHandheld {

    public final static String headKey = "polearm/head";
    public final static String handleKey = "polearm/handle";

    public final static String bindingKey = "polearm/binding";

    public static final String identifier = "modular_polearm";

    private static final GuiModuleOffsets majorOffsets = new GuiModuleOffsets(1, -3, -11, 21);
    private static final GuiModuleOffsets minorOffsets = new GuiModuleOffsets(-14, 0);

    @ObjectHolder(TetraMod.MOD_ID + ":" + identifier)
    public static ModularPolearm instance;

    public ModularPolearm() {
        super(new Properties().stacksTo(1).fireResistant());

        entityHitDamage = 1;

        majorModuleKeys = new String[]{headKey, handleKey};
        minorModuleKeys = new String[]{bindingKey};

        requiredModules = new String[]{handleKey, headKey};

        updateConfig(ConfigHandler.honeSingleBase.get(), ConfigHandler.honeSingleIntegrityMultiplier.get());


        SchematicRegistry.instance.registerSchematic(new RepairSchematic(this, identifier));
        RemoveSchematic.registerRemoveSchematics(this, identifier);
    }

    public ModularPolearm(Properties properties) {
        super(properties);
    }

    @Override
    public void commonInit(PacketHandler packetHandler) {
        DataManager.instance.synergyData.onReload(() -> synergies = DataManager.instance.getSynergyData("single/"));
    }

    public void updateConfig(int honeBase, int honeIntegrityMultiplier) {
        this.honeBase = honeBase;
        this.honeIntegrityMultiplier = honeIntegrityMultiplier;
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
    public GuiModuleOffsets getMajorGuiOffsets() {
        return majorOffsets;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GuiModuleOffsets getMinorGuiOffsets() {
        return minorOffsets;
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (allowdedIn(group)) {
            items.add(setupPolearm("iron", "stick"));
        }
    }

    private ItemStack setupPolearm(String head, String handle) {
        ItemStack itemStack = new ItemStack(this);
        IModularItem.putModuleInSlot(itemStack, headKey, "polearm/polearm_head", "polearm/polearm_head_material", "polearm_head/" + head);
        IModularItem.putModuleInSlot(itemStack, handleKey, "polearm/basic_handle", "polearm/basic_handle_material", "basic_handle/" + handle);
        IModularItem.updateIdentifier(itemStack);

        return itemStack;
    }
}
