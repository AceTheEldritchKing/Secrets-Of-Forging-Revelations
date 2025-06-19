package net.acetheeldritchking.secrets_of_forging_revelations.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.acetheeldritchking.secrets_of_forging_revelations.item.ModularPolearm;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.mickelus.tetra.items.modular.ThrownModularItemEntity;
import se.mickelus.tetra.items.modular.ThrownModularItemRenderer;

@Mixin(ThrownModularItemRenderer.class)
public abstract class ThrownModularItemRendererMixin {
    @Inject(
            method = "render(Lse/mickelus/tetra/items/modular/ThrownModularItemEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V   ",
            at=@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;renderStatic(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;IILcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/level/Level;I)V"))
    private void onRender(ThrownModularItemEntity entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource renderTypeBuffer, int packedLightIn, CallbackInfo ci) {
        Item item = ((ThrownModularItemEntityInvoker) entity).getStack().getItem();
        if (item instanceof ModularPolearm) {
            this.transformPolearm(entity, partialTicks, matrixStack);
        }
    }


    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Unique
    private void transformPolearm(ThrownModularItemEntity entity, float partialTicks, PoseStack matrixStack) {
        matrixStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.getYRot(), entity.getYRot()) - 90.0F));
        matrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.getXRot(), entity.getXRot()) + 135.0F));
        matrixStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        matrixStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        matrixStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        matrixStack.translate(0.3, -0.5, 0.0);
    }

}
