package jade;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    private String name;
    private List<Component> components;

    public GameObject(String name) {
        this.name = name;
        components = new ArrayList<>();
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        for(Component component : components) {
            if(componentClass.isAssignableFrom(component.getClass())) {
                try {
                    return componentClass.cast(component);
                } catch(ClassCastException classCastException) {
                    classCastException.printStackTrace();
                    assert false : "Error: Casting component.";
                }
            }
        }
        return null;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass) {
        for(int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            if(componentClass.isAssignableFrom(component.getClass())) {
                components.remove(i);
                return;
            }
        }
    }

    public void addComponent(Component component) {
        components.add(component);
        component.gameObject = this;
    }

    public void update(float dt) {
        for(Component component : components) {
            component.update(dt);
        }
    }

    public void start() {
        for(Component component : components) {
            component.start();
        }
    }
}
