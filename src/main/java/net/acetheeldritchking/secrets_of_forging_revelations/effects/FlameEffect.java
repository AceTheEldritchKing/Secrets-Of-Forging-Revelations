package net.acetheeldritchking.secrets_of_forging_revelations.effects;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.IStatGetter;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimal;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static se.mickelus.tetra.gui.stats.StatsHelper.barLength;

public class FlameEffect {
    private static final ItemEffect infernal = ItemEffect.get("secrets_of_forging_revelations:infernal");

    @OnlyIn(Dist.CLIENT)
    public static void init()
    {
        final IStatGetter effectStatGetter = new StatGetterEffectLevel(infernal, 1);
        final GuiStatBar effectBar = new GuiStatBar
                (0, 0, barLength, "secrets_of_forging_revelations.effect.infernal.name", 0, 30, false, effectStatGetter,
                        LabelGetterBasic.decimalLabel, new TooltipGetterDecimal
                        ("secrets_of_forging_revelations.effect.infernal.tooltip", effectStatGetter));

        WorkbenchStatsGui.addBar(effectBar);
        HoloStatsGui.addBar(effectBar);
    }

    @SubscribeEvent
    public void onLivingAttackEvent(LivingDamageEvent event)
    {
        LivingEntity defender = event.getEntity();
        Entity eAttacker = event.getSource().getEntity();

        if (eAttacker instanceof LivingEntity attacker)
        {
            ItemStack heldStack = attacker.getMainHandItem();
            if (heldStack.getItem() instanceof ModularItem item)
            {
                // Gets level
                int level = item.getEffectLevel(heldStack, infernal);

                // Flame on, baby!
                if (level > 0 && !attacker.level().isClientSide)
                {
                    defender.setSecondsOnFire(level);
                }
            }
        }
    }

}
