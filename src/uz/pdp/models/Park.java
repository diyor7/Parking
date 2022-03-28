package uz.pdp.models;

import java.util.ArrayList;

import static uz.pdp.utils.BaseUtil.generateUniqueID;

/**
 * @author Doston Bokhodirov, Wed 5:00 PM. 11/10/2021
 */
public class Park {
    private String id;
    private String organisationID;
    private String name;
    private int floorCount;
    private int rowCount;
    private int cellCount;
    private boolean isBlocked;
    ArrayList<Floor> floors = new ArrayList<>();

    public Park() {
        this.id = generateUniqueID();
    }

    public Park(String organisationID, String name, int floorCount, int rowCount, int cellCount, ArrayList<Floor> floors) {
        this();
        this.organisationID = organisationID;
        this.name = name;
        this.floorCount = floorCount;
        this.rowCount = rowCount;
        this.cellCount = cellCount;
        this.floors = floors;
        this.isBlocked = false;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganisationID() {
        return organisationID;
    }

    public void setOrganisationID(String organisationID) {
        this.organisationID = organisationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getCellCount() {
        return cellCount;
    }

    public void setCellCount(int cellCount) {
        this.cellCount = cellCount;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }
}
