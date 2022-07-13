package jade;

import components.Component;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    private static int ID_COUNTER = 0;
    private int uid = -1;

    private final String name;
    private final List<Component> components;
    public Transform transform;
    private final int zIndex;

    public GameObject(String name, Transform transform, int zIndex) {
        this.name = name;
        this.zIndex = zIndex;
        this.components = new ArrayList<>();
        this.transform = transform;

        this.uid = ID_COUNTER++;
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
        component.generateId();
        this.components.add(component);
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

    public void imgui() {
       for(Component component : components) {
           component.imgui();
       }
    }

    public int zIndex() {
        return this.zIndex;
    }

    public static void init(int maxId) {
        ID_COUNTER = maxId;
    }

    public int getUid() {
        return this.uid;
    }

    public List<Component> getAllComponents() {
        return this.components;
    }
}
