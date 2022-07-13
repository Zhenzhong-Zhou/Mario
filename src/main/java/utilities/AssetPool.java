package utilities;

import components.SpriteSheet;
import renderer.Shader;
import renderer.Texture;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssetPool {
    private static final Map<String, Shader> shaderHashMap = new HashMap<>();
    private static final Map<String, Texture> textureHashMap = new HashMap<>();
    private static final Map<String, SpriteSheet> spriteSheetHashMap = new HashMap<>();

    public static Shader getShader(String resourceName) {
        File file = new File(resourceName);
        if(AssetPool.shaderHashMap.containsKey(file.getAbsolutePath())) {
            return AssetPool.shaderHashMap.get(file.getAbsolutePath());
        } else {
            Shader shader = new Shader(resourceName);
            shader.compile();
            AssetPool.shaderHashMap.put(file.getAbsolutePath(), shader);
            return shader;
        }
    }

    public static Texture getTexture(String resourceName) {
        File file = new File(resourceName);
        if(AssetPool.textureHashMap.containsKey(file.getAbsolutePath())) {
            return AssetPool.textureHashMap.get(file.getAbsolutePath());
        } else {
            Texture texture = new Texture();
            texture.init(resourceName);
            AssetPool.textureHashMap.put(file.getAbsolutePath(), texture);
            return texture;
        }
    }

    public static void addSpriteSheet(String resourceName, SpriteSheet spriteSheet) {
        File file = new File(resourceName);
        if(!AssetPool.spriteSheetHashMap.containsKey(file.getAbsolutePath())) {
            AssetPool.spriteSheetHashMap.put(file.getAbsolutePath(), spriteSheet);
        }
    }

    public static SpriteSheet getSpriteSheet(String resourceName) {
        File file = new File(resourceName);
        assert AssetPool.spriteSheetHashMap.containsKey(file.getAbsolutePath())
                : "Error: Tried to access sprite sheet '" + resourceName + "' and it has not been added to asset pool.";
        return AssetPool.spriteSheetHashMap.getOrDefault(file.getAbsolutePath(), null);
    }
}
