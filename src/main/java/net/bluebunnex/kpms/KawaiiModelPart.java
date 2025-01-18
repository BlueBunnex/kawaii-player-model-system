package net.bluebunnex.kpms;

import net.minecraft.client.model.ModelPart;

public class KawaiiModelPart extends ModelPart {

    public float basePitch, baseRoll, baseYaw;
    public float baseX, baseY, baseZ;
    public String animationType;

    public KawaiiModelPart() {
        super(0, 0);
    }

    @Override
    public void setPivot(float x, float y, float z) {

        this.baseX = x;
        this.baseY = y;
        this.baseZ = z;

        super.setPivot(x, y, z);
    }
}
