package net.acetheeldritchking.secrets_of_forging_revelations.mixins;

import net.acetheeldritchking.secrets_of_forging_revelations.gui.CustomHoloItemGui;
import net.acetheeldritchking.secrets_of_forging_revelations.item.ModularPolearm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.mickelus.mutil.gui.GuiAttachment;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloItemsGui;

@Mixin(HoloItemsGui.class)
public class HoloItemsGuiMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(int x, int y, int width, int height,
                        java.util.function.BiConsumer onItemSelect,
                        java.util.function.Consumer onSlotSelect,
                        Runnable onMaterialsClick, CallbackInfo ci) {
        HoloItemsGui self = (HoloItemsGui) (Object) this;
        self.addChild((new CustomHoloItemGui(-119, 0, ModularPolearm.instance, 0, () -> onItemSelect.accept(ModularPolearm.instance, ModularPolearm.instance.getDefaultStack()), onSlotSelect)).setAttachment(GuiAttachment.topCenter));
    }
}
