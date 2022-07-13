package jade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import components.Sprite;
import components.SpriteRenderer;
import components.SpriteSheet;
import imgui.ImGui;
import org.joml.Vector2f;
import org.joml.Vector4f;
import utilities.AssetPool;

public class LevelEditorScene extends Scene{
    SpriteRenderer objSprite;
    public LevelEditorScene() {

    }

    @Override
    public void init() {
        loadResources();
        this.camera = new Camera(new Vector2f(-250, 0));

        SpriteSheet spriteSheet = AssetPool.getSpriteSheet("assets/images/spriteSheet.png");

        GameObject gameObject = new GameObject("Object 1", new Transform(new Vector2f(200, 100), new Vector2f(256, 256)), 2);
        objSprite = new SpriteRenderer();
        gameObject.addComponent(objSprite);
        objSprite.setColor(new Vector4f(1, 0, 0, 1));
        this.addGameObjectToScene(gameObject);
        this.activeGameObject = gameObject;

        GameObject object1 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)), 3);
        SpriteRenderer obj1SpriteRenderer = new SpriteRenderer();
        Sprite obj1Sprite = new Sprite();
        obj1Sprite.setTexture(AssetPool.getTexture("assets/images/blendGreen.png"));
        obj1SpriteRenderer.setSprite(obj1Sprite);
        object1.addComponent(obj1SpriteRenderer);
        this.addGameObjectToScene(object1);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String serialized = gson.toJson(gameObject);
        System.out.println(serialized);
        GameObject object = gson.fromJson(serialized, GameObject.class);
        System.out.println(object);
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpriteSheet("assets/images/spriteSheet.png",
                new SpriteSheet(AssetPool.getTexture("assets/images/spriteSheet.png"),
                        16, 16, 26, 0));
    }

    @Override
    public void update(float dt) {
        for(GameObject gameObject : gameObjects) {
            gameObject.update(dt);
        }
        this.renderer.render();
    }

    @Override
    public void imgui() {
        ImGui.begin("Test Window");
        ImGui.text("Some random text");
        ImGui.end();
    }
}
