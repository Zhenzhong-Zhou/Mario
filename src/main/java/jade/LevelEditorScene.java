package jade;

import components.SpriteRenderer;
import components.SpriteSheet;
import org.joml.Vector2f;
import utilities.AssetPool;

import static org.lwjgl.glfw.GLFW.*;

public class LevelEditorScene extends Scene{
    private GameObject gameObject;
    private SpriteSheet sprites;
    public LevelEditorScene() {

    }

    @Override
    public void init() {
        loadResources();
        this.camera = new Camera(new Vector2f(-250, 0));

        sprites = AssetPool.getSpriteSheet("assets/images/spritesheet.png");

        gameObject = new GameObject("Object 1", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
        gameObject.addComponent(new SpriteRenderer(sprites.getSprite(0)));
        this.addGameObjectToScene(gameObject);

        GameObject object1 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)));
        object1.addComponent(new SpriteRenderer(sprites.getSprite(10)));
        this.addGameObjectToScene(object1);
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpriteSheet("assets/images/spritesheet.png",
                new SpriteSheet(AssetPool.getTexture("assets/images/spritesheet.png"),
                        16, 16, 26, 0));
    }

    private int spriteIndex = 0;
    private float spriteFlipTime = 0.2f;
    private float spriteFlipTimeLeft = 0.0f;

    @Override
    public void update(float dt) {
        if(KeyListener.isKeyPressed(GLFW_KEY_RIGHT)) {
            camera.position.x += 100.0f * dt;
        } else if(KeyListener.isKeyPressed(GLFW_KEY_LEFT)) {
            camera.position.x -= 100.f * dt;
        } else if(KeyListener.isKeyPressed(GLFW_KEY_DOWN)) {
            camera.position.y -= 100.0f * dt;
        }

        gameObject.transform.position.x += 10 * dt;
        spriteFlipTimeLeft -= dt;
        if(spriteFlipTimeLeft <= 0) {
            spriteFlipTimeLeft = spriteFlipTime;
            spriteIndex++;
            if(spriteIndex > 4) {
                spriteIndex = 0;
            }
            gameObject.getComponent(SpriteRenderer.class).setSprite(sprites.getSprite(spriteIndex));
        }

        for(GameObject gameObject : gameObjects) {
            gameObject.update(dt);
        }
        this.renderer.render();
    }
}
