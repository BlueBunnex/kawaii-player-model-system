package net.bluebunnex.kpms;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import org.spongepowered.include.com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;

public class KawaiiModel extends BipedEntityModel {

    public KawaiiModel() {
        super();

        // pivot position = global space
        // cuboid position = local space (relative to pivot)

        try {
            // located in "run/."
            JsonReader reader = new JsonReader(new FileReader("kpms/local_model.json"));

            reader.beginObject();

            while (reader.hasNext()) {
                String key = reader.nextName();

                reader.skipValue();
//                switch (key) {
//                    case "texture":
//                }
                System.out.println(key);
            }

            reader.endObject();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAngles(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.setAngles(limbAngle, limbDistance * 2, animationProgress, headYaw, headPitch, scale);
    }
}
