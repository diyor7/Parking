package uz.pdp.db;

import uz.pdp.models.Organisation;
import uz.pdp.models.User;

import java.util.ArrayList;

/**
 * @author Doston Bokhodirov, Wed 5:48 PM. 11/10/2021
 */
public class Db {
    public static User session;
    public static boolean loggedIn;
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Organisation> organisations = new ArrayList<>();

    public static void initSuperAdmin() {
        User user = new User();
        user.setUsername("1");
        user.setPassword("1");
        user.setAdmin(true);
        user.setSuperAdmin(true);
        users.add(user);
    }
}
