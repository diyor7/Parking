package uz.pdp;

import static uz.pdp.db.Db.*;
import static uz.pdp.service.OrganisationService.*;
import static uz.pdp.service.ParkService.*;
import static uz.pdp.service.UserService.*;
import static uz.pdp.utils.Color.*;
import static uz.pdp.utils.Input.getStr;
import static uz.pdp.utils.Print.println;

/**
 * @author Doston Bokhodirov, Wed 6:07 PM. 11/10/2021
 */
public class Main {
    public static void main(String[] args) {
        initSuperAdmin();
        run();
    }

    public static void run() {
        menu();
        String choice = getStr("Enter choice : ");
        if (choice.equals("1") && loggedIn && session.isSuperAdmin()) createOrganisation();
        else if (choice.equals("1") && loggedIn && session.isAdmin() && !session.isSuperAdmin()) createPark();
        else if (choice.equals("1") && !loggedIn) login();
        else if (choice.equals("1") && !session.isAdmin()) showPark(session);
        else if (choice.equals("2") && loggedIn && session.isAdmin() && !session.isSuperAdmin()) createUser();
        else if (choice.equals("2") && loggedIn && !session.isAdmin()) inAt(session);
        else if (choice.equals("2") && loggedIn && session.isSuperAdmin()) blockOrganisation();
        else if (choice.equals("3") && loggedIn && session.isAdmin() && !session.isSuperAdmin()) createAdminByAdmin();
        else if (choice.equals("3") && loggedIn && !session.isAdmin()) outAt(session);
        else if (choice.equals("3") && loggedIn && session.isSuperAdmin()) unblockOrganisation();
        else if (choice.equals("4") && loggedIn && session.isAdmin() && !session.isSuperAdmin()) removeUser();
        else if (choice.equals("4") && loggedIn && !session.isAdmin()) resetPassword();
        else if (choice.equals("4") && loggedIn && session.isSuperAdmin()) createAdminBySuper();
        else if (choice.equals("5") && loggedIn && session.isAdmin() && !session.isSuperAdmin()) blockUser();
        else if (choice.equals("5") && loggedIn && !session.isAdmin()) logout();
        else if (choice.equals("5") && loggedIn && session.isSuperAdmin()) logout();
        else if (choice.equals("6") && loggedIn && session.isAdmin() && !session.isSuperAdmin()) unBlockUser();
        else if (choice.equals("7") && loggedIn && session.isAdmin() && !session.isSuperAdmin()) blockUser();
        else if (choice.equals("8") && loggedIn && session.isAdmin() && !session.isSuperAdmin()) showPark(session);
        else if (choice.equals("9") && loggedIn && session.isAdmin() && !session.isSuperAdmin()) inAt(session);
        else if (choice.equals("10") && loggedIn && session.isAdmin() && !session.isSuperAdmin()) outAt(session);
        else if (choice.equals("11") && loggedIn && session.isAdmin() && !session.isSuperAdmin()) blockPark();
        else if (choice.equals("12") && loggedIn && session.isAdmin() && !session.isSuperAdmin()) resetPassword();
        else if (choice.equals("13") && loggedIn && session.isAdmin() && !session.isSuperAdmin()) logout();
        else if (choice.startsWith("q")) {
            println(RED,"Bye");
            return;
        }
        else {
            println(RED, "Wrong choice");
        }
        run();
    }

    public static void menu() {
        if (loggedIn) {
            // superAdmin
            if (session.isSuperAdmin()) {
                println(GREEN, "Create Organisation -> 1");
                println(GREEN, "Block Organisation -> 2");
                println(GREEN, "Unblock Organisation -> 3");
                println(GREEN, "Create Admin -> 4");
                println(GREEN, "Logout -> 5");
            }
            // Admin
            else if (session.isAdmin()) {
                println(GREEN, "Create Park -> 1");
                println(GREEN, "Create User -> 2");
                println(GREEN, "Create Admin -> 3");
                println(GREEN, "Remove User -> 4");
                println(GREEN, "Block User -> 5");
                println(GREEN, "Unblock User -> 6");
                println(GREEN, "Block Users -> 7");
                println(GREEN, "Show All Parks -> 8");
                println(GREEN, "InAt -> 9");
                println(GREEN, "OutAt -> 10");
                println(GREEN, "Block Park -> 11");
                println(GREEN, "Reset Password -> 12");
                println(GREEN, "Logout -> 13");
            }
            // users
            else if (!session.isAdmin()) {
                println(GREEN, "Show Park -> 1");
                println(GREEN, "InAt -> 2");
                println(GREEN, "OutAt -> 3");
                println(GREEN, "Reset Password -> 4");
                println(GREEN, "Logout -> 5");
            }
        } else println(YELLOW, "Login -> 1");

        println(YELLOW, "Quit -> q...");
    }
}
