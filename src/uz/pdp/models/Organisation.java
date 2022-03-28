package uz.pdp.models;

import java.util.ArrayList;

import static uz.pdp.utils.BaseUtil.generateUniqueID;

/**
 * @author Doston Bokhodirov, Wed 4:20 PM. 11/10/2021
 */
public class Organisation {
    private String name;
    private String id;
    private boolean isBlocked;
    public static ArrayList<Park> parks=new ArrayList<>();

    public Organisation() {
        this.id=generateUniqueID();
    }

    public Organisation(String name, String id, ArrayList<Park> parks) {
        this();
        this.name = name;
        this.parks = parks;
        this.isBlocked = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Park> getParks() {
        return parks;
    }

    public void setParks(ArrayList<Park> parks) {
        this.parks = parks;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}

