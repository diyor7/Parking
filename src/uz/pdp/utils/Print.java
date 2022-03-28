package uz.pdp.utils;

import static uz.pdp.utils.Color.BLACK;
import static uz.pdp.utils.Color.RESET;

/**
 * @author Elmurodov Javohir, Fri 5:07 PM. 11/5/2021
 */
public class Print {
    public static void print(Object obj) {
        print(BLACK, obj);
    }

    public static void print(String color, Object obj) {
        System.out.print(color + obj + RESET);
    }

    public static void println(Object obj) {
        println(BLACK, obj);
    }

    public static void println(String color, Object obj) {
        System.out.println(color + obj + RESET);
    }
}
