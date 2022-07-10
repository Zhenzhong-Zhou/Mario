package jade;

import components.SpriteRenderer;
import org.joml.Vector2f;
import org.joml.Vector4f;
import utilities.AssetPool;

import static org.lwjgl.glfw.GLFW.*;

public class LevelEditorScene extends Scene{
    public LevelEditorScene() {

    }

    @Override
    public void init() {
        this.camera = new Camera(new Vector2f(-250, 0));

        GameObject object = new GameObject("Object 1", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
        object.addComponent(new SpriteRenderer(AssetPool.getTexture("assets/images/testImage.png")));
        this.addGameObjectToScene(object);

        GameObject object1 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)));
        object1.addComponent(new SpriteRenderer(AssetPool.getTexture("assets/images/testImage2.png")));
        this.addGameObjectToScene(object1);

        loadResources();
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");
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
