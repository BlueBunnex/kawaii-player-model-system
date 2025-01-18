package net.bluebunnex.kpms.mixin;

import net.bluebunnex.kpms.IModelPart;
import net.minecraft.client.model.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ModelPart.class)
public class ModelPartMixin implements IModelPart {

    @Shadow
    private int u;

    @Shadow
    private int v;

    @Unique
    public void kpms$setUV(int u, int v) {
        this.u = u;
        this.v = v;
    }
}
