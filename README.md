mod for StAPI that allows players to use their own custom player models which other players with the mod can see

reads a json file that has model info for each body part, which are then animated as they normally are in game

## Format
```
{
  "texture": <string>,
  "parts": [
    {
      "animation": <string>,
      "pivot": [<double>, <double>, <double>],
      "rotation": [<double>, <double>, <double>],
      "uv": [<int>, <int>],
      "uvMirror": <boolean>,
      "cuboid": [<double>, <double>, <double>, <int>, <int>, <int>]
    }
    ...
  ]
}
```

pivot position = global space
cuboid position = local space (relative to pivot)

- **texture**:
- **parts**:
  - **animation**: Represents _. Accepted values are `head, body, rightArm, leftArm, rightLeg, leftLeg`
  - **pivot**:
  - **rotation**: Rotation before animation.
  - **uv**:
  - **uvMirror**:
  - **cuboid**: