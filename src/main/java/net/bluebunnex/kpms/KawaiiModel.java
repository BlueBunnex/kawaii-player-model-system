package net.bluebunnex.kpms;

import net.minecraft.client.render.entity.model.BipedEntityModel;

public class KawaiiModel extends BipedEntityModel {

    public void setAngles(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.setAngles(limbAngle, limbDistance * 2, animationProgress, headYaw, headPitch, scale);
    }
}
