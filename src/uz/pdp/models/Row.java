package uz.pdp.models;

import java.util.ArrayList;

/**
 * @author Doston Bokhodirov, Wed 5:01 PM. 11/10/2021
 */
public class Row {
    private String rowName;
    ArrayList<Cell> cells = new ArrayList<>();

    public Row() {
    }

    public Row(String rowName, ArrayList<Cell> cells) {
        this.rowName = rowName;
        this.cells = cells;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }
}
