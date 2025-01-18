package net.bluebunnex.kpms;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.include.com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KawaiiModel extends BipedEntityModel {

    public String texture;
    public ArrayList<KawaiiModelPart> parts;

    public KawaiiModel() {
        super();

        this.parts = new ArrayList<KawaiiModelPart>();

        try {
            // located in "run/."
            JsonReader json = new JsonReader(new FileReader("kpms/local_model.json"));

            json.beginObject();

            while (json.hasNext()) {
                String key = json.nextName();

                switch (key) {
                    case "texture":
                        this.texture = json.nextString();
                        break;

                    case "parts":
                        json.beginArray();

                        while (json.hasNext())
                            this.parts.add(readPart(json));

                        json.endArray();
                        break;

                    default:
                        json.skipValue();
                }

                System.out.println(key);
            }

            json.endObject();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static KawaiiModelPart readPart(JsonReader json) throws IOException {

        KawaiiModelPart part = new KawaiiModelPart();

        json.beginObject();

        while (json.hasNext()) {
            String key = json.nextName();

            switch (key) {
                case "animation":
                    part.animationType = json.nextString();
                    break;

                case "pivot":
                    json.beginArray();
                    part.setPivot(
                            (float) json.nextDouble(),
                            (float) json.nextDouble(),
                            (float) json.nextDouble()
                    );
                    json.endArray();
                    break;

                case "rotation":
                    json.beginArray();
                    part.pitch = (float) json.nextDouble();
                    part.roll  = (float) json.nextDouble();
                    part.yaw   = (float) json.nextDouble();
                    json.endArray();
                    break;

                case "uv":
                    json.beginArray();
                    ((IModelPart) part).kpms$setUV(json.nextInt(), json.nextInt());
                    json.endArray();
                    break;

                case "uvMirror":
                    part.mirror = json.nextBoolean();
                    break;

                case "cuboid":
                    json.beginArray();
                    part.addCuboid(
                            (float) json.nextDouble(),
                            (float) json.nextDouble(),
                            (float) json.nextDouble(),
                            json.nextInt(),
                            json.nextInt(),
                            json.nextInt()
                    );
                    json.endArray();
                    break;

                default:
                    json.skipValue();
            }

            System.out.println(key);
        }

        json.endObject();

        return part;
    }

    @Override
    public void render(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        //this.setAngles(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);

        for (KawaiiModelPart part : parts)
            part.render(scale);
    }

    @Override
    public void setAngles(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        this.head.yaw = headYaw / 57.295776F;
        this.head.pitch = headPitch / 57.295776F;
        this.hat.yaw = this.head.yaw;
        this.hat.pitch = this.head.pitch;
        this.rightArm.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 2.0F * limbDistance * 0.5F;
        this.leftArm.pitch = MathHelper.cos(limbAngle * 0.6662F) * 2.0F * limbDistance * 0.5F;
        this.rightArm.roll = 0.0F;
        this.leftArm.roll = 0.0F;
        this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.rightLeg.yaw = 0.0F;
        this.leftLeg.yaw = 0.0F;
        ModelPart var10000;
        if (this.riding) {
            var10000 = this.rightArm;
            var10000.pitch += -0.62831855F;
            var10000 = this.leftArm;
            var10000.pitch += -0.62831855F;
            this.rightLeg.pitch = -1.2566371F;
            this.leftLeg.pitch = -1.2566371F;
            this.rightLeg.yaw = 0.31415927F;
            this.leftLeg.yaw = -0.31415927F;
        }

        if (this.leftArmPose) {
            this.leftArm.pitch = this.leftArm.pitch * 0.5F - 0.31415927F;
        }

        if (this.rightArmPose) {
            this.rightArm.pitch = this.rightArm.pitch * 0.5F - 0.31415927F;
        }

        this.rightArm.yaw = 0.0F;
        this.leftArm.yaw = 0.0F;
        if (this.handSwingProgress > -9990.0F) {
            float var7 = this.handSwingProgress;
            this.body.yaw = MathHelper.sin(MathHelper.sqrt(var7) * 3.1415927F * 2.0F) * 0.2F;
            this.rightArm.pivotZ = MathHelper.sin(this.body.yaw) * 5.0F;
            this.rightArm.pivotX = -MathHelper.cos(this.body.yaw) * 5.0F;
            this.leftArm.pivotZ = -MathHelper.sin(this.body.yaw) * 5.0F;
            this.leftArm.pivotX = MathHelper.cos(this.body.yaw) * 5.0F;
            var10000 = this.rightArm;
            var10000.yaw += this.body.yaw;
            var10000 = this.leftArm;
            var10000.yaw += this.body.yaw;
            var10000 = this.leftArm;
            var10000.pitch += this.body.yaw;
            var7 = 1.0F - this.handSwingProgress;
            var7 *= var7;
            var7 *= var7;
            var7 = 1.0F - var7;
            float var8 = MathHelper.sin(var7 * 3.1415927F);
            float var9 = MathHelper.sin(this.handSwingProgress * 3.1415927F) * -(this.head.pitch - 0.7F) * 0.75F;
            var10000 = this.rightArm;
            var10000.pitch = (float)((double)var10000.pitch - ((double)var8 * 1.2 + (double)var9));
            var10000 = this.rightArm;
            var10000.yaw += this.body.yaw * 2.0F;
            this.rightArm.roll = MathHelper.sin(this.handSwingProgress * 3.1415927F) * -0.4F;
        }

        if (this.sneaking) {
            this.body.pitch = 0.5F;
            var10000 = this.rightLeg;
            var10000.pitch -= 0.0F;
            var10000 = this.leftLeg;
            var10000.pitch -= 0.0F;
            var10000 = this.rightArm;
            var10000.pitch += 0.4F;
            var10000 = this.leftArm;
            var10000.pitch += 0.4F;
            this.rightLeg.pivotZ = 4.0F;
            this.leftLeg.pivotZ = 4.0F;
            this.rightLeg.pivotY = 9.0F;
            this.leftLeg.pivotY = 9.0F;
            this.head.pivotY = 1.0F;
        } else {
            this.body.pitch = 0.0F;
            this.rightLeg.pivotZ = 0.0F;
            this.leftLeg.pivotZ = 0.0F;
            this.rightLeg.pivotY = 12.0F;
            this.leftLeg.pivotY = 12.0F;
            this.head.pivotY = 0.0F;
        }

        var10000 = this.rightArm;
        var10000.roll += MathHelper.cos(animationProgress * 0.09F) * 0.05F + 0.05F;
        var10000 = this.leftArm;
        var10000.roll -= MathHelper.cos(animationProgress * 0.09F) * 0.05F + 0.05F;
        var10000 = this.rightArm;
        var10000.pitch += MathHelper.sin(animationProgress * 0.067F) * 0.05F;
        var10000 = this.leftArm;
        var10000.pitch -= MathHelper.sin(animationProgress * 0.067F) * 0.05F;
    }

    @Override
    public void renderEars(float scale) {}

    @Override
    public void renderCape(float scale) {}
}
