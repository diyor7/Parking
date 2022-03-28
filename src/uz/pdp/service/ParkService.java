package uz.pdp.service;

import uz.pdp.models.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static uz.pdp.db.Db.session;
import static uz.pdp.models.Organisation.parks;
import static uz.pdp.service.UserService.isHavePark;
import static uz.pdp.utils.BaseUtil.getLetter;
import static uz.pdp.utils.Color.*;
import static uz.pdp.utils.Input.getNum;
import static uz.pdp.utils.Input.getStr;
import static uz.pdp.utils.Print.print;
import static uz.pdp.utils.Print.println;

/**
 * @author Doston Bokhodirov, Wed 5:49 PM. 11/10/2021
 */
public class ParkService {
    public static void createPark() {
        ArrayList<Floor> floors = new ArrayList<>();
        String name = getStr("Name: ");
        int floorCount = getNum("Floors: ");
        int rowCount = getNum("Rows: ");
        int cellCount = getNum("Cells: ");
        for (int i = 0; i < floorCount; i++) {
            Floor floor = getNewFloor(rowCount, cellCount);
            floor.setFloor(i + 1);
            floors.add(floor);
        }
        Park park = new Park(session.getOrganisationID(), name, floorCount, rowCount, cellCount, floors);
        parks.add(park);
    }

    public static Floor getNewFloor(int rowCount, int cellCount) {

        ArrayList<Row> rows = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            rows.add(getNewRow(getLetter(i), cellCount));
        }
        return new Floor(rows);
    }

    public static Row getNewRow(String str, int cellCount) {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < cellCount; i++) {
            cells.add(getNewCell(str));
        }
        return new Row(str, cells);
    }

    private static Cell getNewCell(String str) {
        return new Cell(str);
    }

    public static void showPark(User session) {
        if (session.isAdmin()) {
            int i = 1;
            for (Park park : parks) {
                if (park.getOrganisationID().equals(session.getOrganisationID())) {
                    println(i++ + ". " + park.getName());
                }
            }
            if (i == 1) {
                println(RED, "Parks don't have got");
                return;
            }
            int index = getNum("Choice park: ");
            for (Park park : parks) {
                if (park.getOrganisationID().equals(session.getOrganisationID())) {
                    index--;
                }
                if (index == 0) {
                    free(park);
                }
            }
        } else {
            for (Park park : parks) {
                if (park.getId().equals(session.getParkID())) {
                    free(park);
                    break;
                }
            }
        }
    }

    private static void free(Park park) {
        ArrayList<Floor> floors = park.getFloors();
        for (int i = 0; i < floors.size(); i++) {
            Floor floor = floors.get(i);
            println(RED, (i + 1) + "-Floor");
            print("\t");
            for (int j = 0; j < park.getCellCount(); j++) {
                print(PURPLE, (j + 1) + "\t");
            }
            println("");
            for (Row row : floor.getRows()) {
                print(PURPLE, row.getRowName() + "\t");
                for (Cell cell : row.getCells()) {
                    if (Objects.isNull(cell.getCar())) {
                        print(GREEN, "✅\t");
                    } else print(RED, "❌\t");
                }
                println("");
            }
        }
    }

    public static void blockPark() {
        if (isHavePark()) {
            return;
        }
        int index = getNum("Choice park: ");
        for (Park park : parks) {
            if (park.getOrganisationID().equals(session.getOrganisationID())) {
                index--;
            }
            if (index == 0) {
                ArrayList<Floor> floors = park.getFloors();
                if (checkForEmptyPark(floors)) {
                    park.setBlocked(true);
                    println(RED, "Park is blocked");
                    break;
                }
                println(RED, "Park has some car \nDo you delete all cars from park ?");
                println("Yes -> 1");
                println("No -> 2");
                int choice = getNum("-> ");
                if (choice == 1) {
                    for (Floor floor : floors) {
                        ArrayList<Row> rows = floor.getRows();
                        for (Row row : rows) {
                            ArrayList<Cell> cells = row.getCells();
                            for (Cell cell : cells) {
                                cell.setCar(null);
                            }
                        }
                    }
                    println(GREEN, "All cars are removed and Park is blocked");
                    park.setBlocked(true);
                }
            }
        }
    }

    public static void inAt(User session) {
        if (session.isAdmin()) {
            if (isHavePark()) {
                return;
            }
            int index = getNum("Choice park: ");
            for (Park park : parks) {
                if (park.getOrganisationID().equals(session.getOrganisationID())) {
                    index--;
                }
                if (index == 0) {
                    int floorIndex = getNum("Choice floor: ");
                    Floor floor = park.getFloors().get(floorIndex - 1);
                    if (checkForEmptyCell(floor.getRows())) {
                        println(RED, "Sorry, there is no place ");
                        return;
                    }
                    String number = getStr("Enter car number: ");
                    int time = getNum("Enter car time: ");
                    putCar(park, floor, number, time);
                }
            }
        } else {
            int floorIndex = getNum("Choice floor: ");
            for (Park park : parks) {
                if (park.getId().equals(session.getParkID())) {
                    Floor floor = park.getFloors().get(floorIndex - 1);
                    if (checkForEmptyCell(floor.getRows())) {
                        println(RED, "Sorry, there is no place ");
                        return;
                    }
                    String number = getStr("Enter car number: ");
                    int time = getNum("Enter car time: ");
                    putCar(park, floor, number, time);
                }
            }
        }
    }

    private static void putCar(Park park, Floor floor, String number, int time) {
        Car car = new Car(number, time);
        Random random = new Random();
        int rowIndex = random.nextInt(park.getRowCount());
        int cellIndex = random.nextInt(park.getCellCount());
        ArrayList<Row> rows = floor.getRows();
        if (rows.get(rowIndex).getCells().get(cellIndex).getCar() == null) {
            rows.get(rowIndex).getCells().get(cellIndex).setCar(car);
            println(RED, floor.getFloor() + "_" + rows.get(rowIndex).getRowName() + "_" + (cellIndex + 1));
            return;
        }
        putCar(park, floor, number, time);
    }

    private static boolean checkForEmptyCell(ArrayList<Row> rows) {
        for (Row row : rows) {
            for (Cell cell : row.getCells()) {
                if (cell.getCar() == null) return false;
            }
        }
        return true;
    }

    private static boolean checkForEmptyPark(ArrayList<Floor> floors) {
        for (Floor floor : floors) {
            ArrayList<Row> rows = floor.getRows();
            for (Row row : rows) {
                ArrayList<Cell> cells = row.getCells();
                for (Cell cell : cells) {
                    if (Objects.nonNull(cell.getCar())) return false;
                }
            }
        }
        return true;
    }

    public static void outAt(User session) {
        if (session.isAdmin()) {
            if (isHavePark()) {
                return;
            }
            int index = getNum("Choice park: ");
            for (Park park : parks) {
                if (park.getOrganisationID().equals(session.getOrganisationID())) {
                    index--;
                }
                if (index == 0) {
                    String[] arr = getStr("Qr ?: ").toLowerCase().split("_");
                    int floorIndex = Integer.parseInt(arr[0]) - 1;
                    int rowIndex = getRowIndex(park, arr[1].charAt(0));
                    int cellIndex = Integer.parseInt(arr[2]) - 1;
                    if (floorIndex >= park.getFloorCount() ||
                            rowIndex >= park.getRowCount() ||
                            cellIndex >= park.getCellCount()) {
                        println(RED, "Qr is invalid");
                        return;
                    }
                    park.getFloors().get(floorIndex).getRows().get(rowIndex).getCells().get(cellIndex).setCar(null);
                }
            }
        } else {
            for (Park park : parks) {
                if (park.getId().equals(session.getParkID())) {
                    String[] arr = getStr("Enter QR Code : ").toLowerCase().split("_");
                    int floorIndex = Integer.parseInt(arr[0]) - 1;
                    int rowIndex = getRowIndex(park, arr[1].charAt(0));
                    int cellIndex = Integer.parseInt(arr[2]) - 1;
                    if (floorIndex >= park.getFloorCount() ||
                            rowIndex >= park.getRowCount() ||
                            cellIndex >= park.getCellCount()) {
                        println(RED, "Qr is invalid");
                        return;
                    }
                    park.getFloors().get(floorIndex).getRows().get(rowIndex).getCells().get(cellIndex).setCar(null);
                }
            }
        }
    }

    private static int getRowIndex(Park park, char a) {
        int r = a - 'a';
        return r > 0 && r < park.getRowCount() ? r : -1;
    }
}
