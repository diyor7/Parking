package uz.pdp.models;

/**
 * @author Doston Bokhodirov, Wed 5:01 PM. 11/10/2021
 */
public class Cell {
    private String rowName;
    private Car car;

    public Cell() {
    }

    public Cell(String rowName) {
        this.rowName = rowName;
    }

    public Cell(String rowName, Car car) {
        this.rowName = rowName;
        this.car = car;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
