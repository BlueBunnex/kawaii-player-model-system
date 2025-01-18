package net.bluebunnex.kpms.mixin;

import net.bluebunnex.kpms.KawaiiModel;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Shadow
    private BipedEntityModel bipedModel;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(CallbackInfo ci) {

        try {

            this.bipedModel = new KawaiiModel();

            ((LivingEntityRendererAccessor) this).setModel(this.bipedModel);

            // probably just gonna have to sacrifice armor idk
            // this.armor1 = new BipedEntityModel(1.0F);
            // this.armor2 = new BipedEntityModel(0.5F);

        } catch (IOException e) {

            for (int i=0; i<100; i++)
                System.err.println("Could not load KawaiiModel!");
        }
    }
}
