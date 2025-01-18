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


## Default Player Model JSON
```
{
  "texture": "texture.png",
  "parts": [
    {
      "animation": "head",
      "pivot": [0, 0, 0],
      "rotation": [0, 0, 0],
      "uv": [0, 0],
      "uvMirror": false,
      "cuboid": [-4, -8, -4, 8, 8, 8]
    },
    {
      "animation": "body",
      "pivot": [0, 0, 0],
      "rotation": [0, 0, 0],
      "uv": [16, 16],
      "uvMirror": false,
      "cuboid": [-4, 0, -2, 8, 12, 4]
    },
    {
      "animation": "rightArm",
      "pivot": [-5, 2, 0],
      "rotation": [0, 0, 0],
      "uv": [40, 16],
      "uvMirror": false,
      "cuboid": [-3, -2, -2, 4, 12, 4]
    },
    {
      "animation": "leftArm",
      "pivot": [5, 2, 0],
      "rotation": [0, 0, 0],
      "uv": [40, 16],
      "uvMirror": true,
      "cuboid": [-1, -2, -2, 4, 12, 4]
    },
    {
      "animation": "rightLeg",
      "pivot": [-2, 12, 0],
      "rotation": [0, 0, 0],
      "uv": [0, 16],
      "uvMirror": false,
      "cuboid": [-2, 0, -2, 4, 12, 4]
    },
    {
      "animation": "leftLeg",
      "pivot": [2, 12, 0],
      "rotation": [0, 0, 0],
      "uv": [0, 16],
      "uvMirror": true,
      "cuboid": [-2, 0, -2, 4, 12, 4]
    }
  ]
}
```