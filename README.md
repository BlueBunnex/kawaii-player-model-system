mod for StAPI that allows players to use their own custom player models which other players with the mod can see

reads a json file that has model info for each body part, which are then animated as they normally are in game

## Format
```
{
  "texture": "texture.png",
  "head": {
    "pivotXYZ": [0, 0, 0],
    "uv": [0, 0],
    "uvMirror": false,
    "cuboids": [
      {
        "size": [10, 10, 10],
        "localXYZ": [0, 0, 0]
      }
    ]
  }
  [OTHER KEYS HERE]
}
```

Accepted keys are `head, body, rightArm, leftArm, rightLeg, leftLeg`. They can be omitted.

## Maybe Format

In order for this better format to be used, I'd have to change how ModelParts work.

```
{
    "texture": "texture.png",
    "head": {
        "pivotXYZ": [0, 0, 0],
        "cuboids": [
            {
                "size": [10, 10, 10],
                "localXYZ": [0, 0, 0],
                "uv": [0, 0],
                "uvMirror": false,
                "baseRotationPRY": [0, 0, 0]
            }
        ]
    }
    [OTHER KEYS HERE]
}
```