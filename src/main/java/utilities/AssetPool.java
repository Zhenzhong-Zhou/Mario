package utilities;

import renderer.Shader;
import renderer.Texture;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssetPool {
    private static Map<String, Shader> shaderHashMap = new HashMap<>();
    private static Map<String, Texture> textureHashMap = new HashMap<>();

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
            Texture texture = new Texture(resourceName);
            AssetPool.textureHashMap.put(file.getAbsolutePath(), texture);
            return texture;
        }
    }
}
