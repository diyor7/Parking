package uz.pdp.service;

import uz.pdp.models.Organisation;
import uz.pdp.models.Park;
import uz.pdp.models.User;

import java.util.Objects;

import static uz.pdp.db.Db.*;
import static uz.pdp.models.Organisation.parks;
import static uz.pdp.service.OrganisationService.createOrganisation;
import static uz.pdp.service.OrganisationService.showOrganisation;
import static uz.pdp.utils.Color.*;
import static uz.pdp.utils.Input.getNum;
import static uz.pdp.utils.Input.getStr;
import static uz.pdp.utils.Print.println;

/**
 * @author Doston Bokhodirov, Wed 5:49 PM. 11/10/2021
 */
public class UserService {

    public static void login() {
        String username = getStr("username: ");
        String password = getStr("password: ");
        User user = findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            println(RED, "Bad Credentials");
            return;
        }
        println(GREEN, "Welcome 😊");
        loggedIn = true;
        session = user;
    }

    public static void logout() {
        session = null;
        loggedIn = false;
    }

    public static boolean isHavePark() {
        int i = 1;
        for (Park park : parks) {
            if (park.getOrganisationID().equals(session.getOrganisationID()) && !park.isBlocked()) {
                println(i++ + ". " + park.getName());
            }
        }
        if (i == 1) {
            println(RED, "Parks don't have");
            return true;
        }
        else return false;
    }

    public static void createUser() {
        User user = new User();
        if (isHavePark()) {
            return;
        }
        int index = getNum("Choice park: ");
        for (Park park : parks) {
            if (park.getOrganisationID().equals(session.getOrganisationID())) {
                index--;
            }
            if (index == 0) {
                user.setParkID(park.getId());
                break;
            }
        }
        String username = getStr("Username: ");
        String password = getStr("Password: ");
        if (Objects.nonNull(findByUsername(username))) {
            println(RED, "Username is already taken");
            return;
        }
        user.setUsername(username);
        user.setPassword(password);
        users.add(user);
    }

    public static void createAdminBySuper() {
        User user = new User();
//        ArrayList<Organisation> organisations;
        if(Objects.nonNull(organisations)){
            println(RED, "Create organization before ");
            System.out.println();
            createOrganisation();
        }
        showOrganisation();
        int numberOrganisation = getNum("Choice Organisation: ");
        for (Organisation organisation : organisations) {
            numberOrganisation--;
            if (numberOrganisation == 0) {
                user.setOrganisationID(organisation.getId());
                break;
            }
        }
        String username = getStr("Username: ");
        String password = getStr("Password: ");
        if (Objects.nonNull(findByUsername(username))) {
            println(RED, "Username is already taken");
            return;
        }
        println(BLUE, "Successfully created admin");
        user.setUsername(username);
        user.setPassword(password);
        user.setAdmin(true);
        users.add(user);
    }

    public static void createAdminByAdmin() {
        User user = new User();

        String username = getStr("Username: ");
        String password = getStr("Password: ");
        if (Objects.nonNull(findByUsername(username))) {
            println(RED, "Username is already taken");
            return;
        }
        println(BLUE, "Successfully created admin");
        user.setUsername(username);
        user.setPassword(password);
        user.setOrganisationID(session.getOrganisationID());
        user.setAdmin(true);
        users.add(user);
    }

    public static void removeUser() {
        if (isHavePark()) {
            return;
        }
        int index = getNum("Choice park: ");
        for (Park park : parks) {
            if (park.getOrganisationID().equals(session.getOrganisationID())) {
                index--;
            }
            if (index == 0) {
                int i = 1;
                for (User user : users) {
                    if (Objects.nonNull(user.getParkID()) && user.getParkID().equals(park.getId())) {
                        println(i++ + ". " + user.getUsername());
                    }
                }
                if (i == 1) {
                    println(RED, "Unblock users don't have");
                    return;
                }
                int index1 = getNum("Choice user: ");
                for (User user : users) {
                    if (Objects.nonNull(user.getParkID()) && user.getParkID().equals(park.getId())) {
                        index1--;
                    }
                    if (index1 == 0) {
                        users.remove(user);
                        break;
                    }
                }
                break;
            }
        }
    }

    public static void blockUser() {
        if (isHavePark()) {
            return;
        }
        int index = getNum("Choice park: ");
        for (Park park : parks) {
            if (park.getOrganisationID().equals(session.getOrganisationID())) {
                index--;
            }
            if (index == 0) {
                int i = 1;
                for (User user : users) {
                    if (Objects.nonNull(user.getParkID()) && user.getParkID().equals(park.getId()) && !user.isBlock()) {
                        println(i++ + ". " + user.getUsername());
                    }
                }
                int index1 = getNum("Choice user: ");
                for (User user : users) {
                    if (Objects.nonNull(user.getParkID()) && user.getParkID().equals(park.getId()) && !user.isBlock()) {
                        index1--;
                    }
                    if (index1 == 0) {
                        user.setBlock(true);
                        break;
                    }
                }
            }
            break;
        }
    }

    public static void unBlockUser() {
        if (isHavePark()) {
            return;
        }
        int index = getNum("Choice park: ");
        for (Park park : parks) {
            if (park.getOrganisationID().equals(session.getOrganisationID())) {
                index--;
            }
            if (index == 0) {
                int i = 1;
                for (User user : users) {
                    if (Objects.nonNull(user.getParkID()) && user.getParkID().equals(park.getId()) && user.isBlock()) {
                        println(i++ + ". " + user.getUsername());
                    }
                }
                int index1 = getNum("Choice user: ");
                for (User user : users) {
                    if (Objects.nonNull(user.getParkID()) && user.getParkID().equals(park.getId()) && user.isBlock()) {
                        index1--;
                    }
                    if (index1 == 0) {
                        user.setBlock(false);
                        break;
                    }
                }
            }
            break;
        }
    }

    public static void resetPassword() {
        String password = getStr("Enter password: ");
        if (!password.equals(session.getPassword())) {
            println(RED, "Wrong password");
            return;
        }
        String newPassword = getStr("Enter new password: ");
        String confirmPassword = getStr("Confirm password: ");
        if (!newPassword.equals(confirmPassword)) {
            println(RED, "Passwords don't match");
            return;
        }
        session.setPassword(newPassword);
    }

    private static User findByUsername(String username) {
        for (User user : users) {
            if (Objects.nonNull(user) && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
