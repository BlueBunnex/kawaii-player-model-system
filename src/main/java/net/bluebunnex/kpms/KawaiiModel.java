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

    public KawaiiModel() throws IOException {
        super();

        this.parts = new ArrayList<>();

        JsonReader json = new JsonReader(new FileReader("kpms/local_model.json")); // located in "run/."

        json.beginObject();

        while (json.hasNext()) {

            String key = json.nextName();

            if (key.equals("texture")) {

                this.texture = json.nextString();

            } else if (key.equals("parts")) {

                json.beginArray();

                while (json.hasNext())
                    this.parts.add(readPart(json));

                json.endArray();

            } else {

                json.skipValue();
            }
        }

        json.endObject();
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
                    part.basePitch = (float) json.nextDouble();
                    part.baseRoll  = (float) json.nextDouble();
                    part.baseYaw   = (float) json.nextDouble();
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
        }

        json.endObject();

        return part;
    }

    @Override
    public void render(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        this.setAngles(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);

        for (KawaiiModelPart part : parts)
            part.render(scale);
    }

    @Override
    public void setAngles(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {

        for (KawaiiModelPart part : parts) {

            part.pitch = part.basePitch;
            part.roll = part.baseRoll;
            part.yaw = part.baseYaw;

            part.pivotX = part.baseX;
            part.pivotY = part.baseY;
            part.pivotZ = part.baseZ;

            switch (part.animationType) {

                case "head":
                    part.yaw += headYaw / 57.295776F;
                    part.pitch += headPitch / 57.295776F;

                    if (this.sneaking)
                        part.pivotY += 1.0F;

                    break;

                case "body":
                    if (this.sneaking)
                        part.pitch += 0.5F;
                    break;

                case "rightArm":
                    part.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 2.0F * limbDistance * 0.5F;

                    part.roll += MathHelper.cos(animationProgress * 0.09F) * 0.05F + 0.05F;
                    part.pitch += MathHelper.sin(animationProgress * 0.067F) * 0.05F;

                    if (this.rightArmPose)
                        part.pitch = part.pitch * 0.5F - 0.31415927F;

                    if (this.riding)
                        part.pitch += -0.62831855F;

                    if (this.sneaking)
                        part.pitch += 0.4F;

                    break;

                case "leftArm":
                    part.pitch = MathHelper.cos(limbAngle * 0.6662F) * 2.0F * limbDistance * 0.5F;

                    part.roll -= MathHelper.cos(animationProgress * 0.09F) * 0.05F + 0.05F;
                    part.pitch -= MathHelper.sin(animationProgress * 0.067F) * 0.05F;

                    if (this.leftArmPose)
                        part.pitch = part.pitch * 0.5F - 0.31415927F;

                    if (this.riding)
                        part.pitch += -0.62831855F;

                    if (this.sneaking)
                        part.pitch += 0.4F;

                    break;

                case "rightLeg":
                    part.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;

                    if (this.riding) {
                        part.pitch = -1.2566371F;
                        part.yaw = 0.31415927F;
                    }

                    if (this.sneaking) {
                        part.pivotZ += 4.0F;
                        part.pivotY -= 3F;
                    }

                    break;

                case "leftLeg":
                    part.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;

                    if (this.riding) {
                        part.pitch = -1.2566371F;
                        part.yaw = -0.31415927F;
                    }

                    if (this.sneaking) {
                        part.pivotZ += 4.0F;
                        part.pivotY -= 3F;
                    }

                    break;
            }
        }


        //
        if (this.handSwingProgress > -9990.0F) {

            ModelPart var10000;

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

        super.setAngles(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
    }

    @Override
    public void renderEars(float scale) {}

    @Override
    public void renderCape(float scale) {}
}
