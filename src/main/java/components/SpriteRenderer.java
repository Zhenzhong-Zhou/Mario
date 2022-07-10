package components;

import jade.Component;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Texture;

public class SpriteRenderer extends Component {
    private final Vector4f color;
    private Vector2f[] textureCoords;
    private Texture texture;

    public SpriteRenderer(Vector4f color) {
        this.color = color;
        this.texture = null;
    }

    public SpriteRenderer(Texture texture) {
        this.texture = texture;
        this.color = new Vector4f(1, 1, 1, 1);
    }

    @Override
    public void start() {

    }
    @Override
    public void update(float dt) {

    }

    public Vector4f getColor() {
        return color;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public Vector2f[] getTextureCoords() {
            Vector2f[] textureCoords = {
                    new Vector2f(1, 1),
                    new Vector2f(1 , 0),
                    new Vector2f(0 , 0),
                    new Vector2f(0 , 1),
            };
            return textureCoords;
    }
}
