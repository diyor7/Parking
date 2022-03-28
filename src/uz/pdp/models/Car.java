package uz.pdp.models;

import static uz.pdp.utils.BaseUtil.generateUniqueID;

/**
 * @author Doston Bokhodirov, Wed 5:01 PM. 11/10/2021
 */
public class Car {
    private String id;
    private String name;
    private int inAt;
    private int outAt;

    public Car() {
        this.id = generateUniqueID();
    }

    public Car(String name, int inAt) {
        this();
        this.name = name;
        this.inAt = inAt;
    }

    public Car(String name, int inAt, int outAt) {
        this();
        this.name = name;
        this.inAt = inAt;
        this.outAt = outAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInAt() {
        return inAt;
    }

    public void setInAt(int inAt) {
        this.inAt = inAt;
    }

    public int getOutAt() {
        return outAt;
    }

    public void setOutAt(int outAt) {
        this.outAt = outAt;
    }
}
