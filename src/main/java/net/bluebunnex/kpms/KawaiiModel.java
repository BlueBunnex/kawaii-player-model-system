package net.bluebunnex.kpms;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import org.spongepowered.include.com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class KawaiiModel extends BipedEntityModel {

//    {
//        "texture": "texture.png",
//        "head": {
//            "pivotXYZ": [0, 0, 0],
//            "parts": {
//                "size": [10, 10, 10],
//                "uv": [0,0],
//                "uvMirror": false,
//                "localXYZ": [0, 0, 0],
//                "baseRotationPRY": [0, 0, 0]
//            }
//        }
//        ...
//    }

    public KawaiiModel() {
        super();

        // pivot position = global space
        // cuboid position = local space (relative to pivot)

        try {
            // run/.
            System.out.println(new JsonReader(new FileReader("kpms/local_model.json")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAngles(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.setAngles(limbAngle, limbDistance * 2, animationProgress, headYaw, headPitch, scale);
    }
}
