package jade;

import components.SpriteRenderer;
import components.SpriteSheet;
import org.joml.Vector2f;
import utilities.AssetPool;

import static org.lwjgl.glfw.GLFW.*;

public class LevelEditorScene extends Scene{
    public LevelEditorScene() {

    }

    @Override
    public void init() {
        loadResources();
        this.camera = new Camera(new Vector2f(-250, 0));

        SpriteSheet spriteSheet = AssetPool.getSpriteSheet("assets/images/spritesheet.png");

        GameObject object = new GameObject("Object 1", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
        object.addComponent(new SpriteRenderer(spriteSheet.getSprite(0)));
        this.addGameObjectToScene(object);

        GameObject object1 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)));
        object1.addComponent(new SpriteRenderer(spriteSheet.getSprite(10)));
        this.addGameObjectToScene(object1);
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpriteSheet("assets/images/spritesheet.png",
                new SpriteSheet(AssetPool.getTexture("assets/images/spritesheet.png"),
                        16, 16, 26, 0));
    }

    @Override
    public void update(float dt) {
        if(KeyListener.isKeyPressed(GLFW_KEY_RIGHT)) {
            camera.position.x += 100.0f * dt;
        } else if(KeyListener.isKeyPressed(GLFW_KEY_LEFT)) {
            camera.position.x -= 100.f * dt;
        } else if(KeyListener.isKeyPressed(GLFW_KEY_DOWN)) {
            camera.position.y -= 100.0f * dt;
        }

        for(GameObject gameObject : gameObjects) {
            gameObject.update(dt);
        }
        this.renderer.render();
    }
}
