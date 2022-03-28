package uz.pdp.models;

import java.util.ArrayList;

/**
 * @author Doston Bokhodirov, Wed 5:01 PM. 11/10/2021
 */
public class Floor {
    private int floor;
    private ArrayList<Row> rows = new ArrayList<>();

    public Floor(ArrayList<Row> rows) {
        this.rows = rows;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }
}
