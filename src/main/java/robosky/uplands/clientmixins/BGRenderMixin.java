package robosky.uplands.clientmixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import robosky.uplands.world.WorldRegistry;

@Mixin(BackgroundRenderer.class)
public class BGRenderMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @ModifyVariable(
            method = "renderBackground",
            at = @At(value = "STORE", ordinal = 0),
            ordinal = 0
    )
    private double modifyVoidColor(double scale) {
        if (client.world.dimension.getType() == WorldRegistry.UPLANDS_DIMENSION()) {
            scale = 1.0;
        }
        return scale;
    }

    @Inject(at = @At("HEAD"), method = "renderBackground")
    private void heckLoom(Camera unused1, float unused2, CallbackInfo info) {

    }
}
