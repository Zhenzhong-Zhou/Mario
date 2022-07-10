package components;

import jade.Component;

public class FontRender extends Component {
    @Override
    public void start() {
        if(gameObject.getComponent(SpriteRender.class) != null) {
            System.out.println("Found Font Render!");
        }
    }
    @Override
    public void update(float dt) {

    }
}
