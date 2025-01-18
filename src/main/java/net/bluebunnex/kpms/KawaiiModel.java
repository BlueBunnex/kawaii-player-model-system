package net.bluebunnex.kpms;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import org.spongepowered.include.com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;

public class KawaiiModel extends BipedEntityModel {

    public String texture;

    public KawaiiModel() {
        super();

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

                    case "head":
                        this.head = readPart(json);
                        break;

                    case "body":
                        this.body = readPart(json);
                        break;

                    case "rightArm":
                        this.rightArm = readPart(json);
                        break;

                    case "leftArm":
                        this.leftArm = readPart(json);
                        break;

                    case "rightLeg":
                        this.rightLeg = readPart(json);
                        break;

                    case "leftLeg":
                        this.leftLeg = readPart(json);
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

    private static ModelPart readPart(JsonReader json) throws IOException {

        // pivot position = global space
        // cuboid position = local space (relative to pivot)

        ModelPart part = new ModelPart(0, 0);

        json.beginObject();

        while (json.hasNext()) {
            String key = json.nextName();

            switch (key) {
                case "pivotXYZ":
                    json.beginArray();
                    part.setPivot(
                            (float) json.nextDouble(),
                            (float) json.nextDouble(),
                            (float) json.nextDouble()
                    );
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

                case "cuboids":
                    part.addCuboid(-5.0F, 0.0F, -1.0F, 10, 16, 1);
                    json.skipValue();
                    break;

                default:
                    json.skipValue();
            }

            System.out.println(key);
        }

        json.endObject();

        return part;
    }
}
