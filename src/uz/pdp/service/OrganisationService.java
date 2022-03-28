package uz.pdp.service;

import uz.pdp.models.Organisation;

import static uz.pdp.db.Db.organisations;
import static uz.pdp.utils.Color.RED;
import static uz.pdp.utils.Input.getNum;
import static uz.pdp.utils.Input.getStr;
import static uz.pdp.utils.Print.println;

/**
 * @author Doston Bokhodirov, Wed 5:50 PM. 11/10/2021
 */
public class OrganisationService {
    public static void createOrganisation() {
        Organisation organisation = new Organisation();
        String name = getStr("Name: ");
        organisation.setName(name);
        organisations.add(organisation);
    }

    public static void blockOrganisation() {
        int i = 1;
        for (Organisation organisation : organisations) {
            if (!organisation.isBlocked()) {
                println(i++ + ". " + organisation.getName());
            }
        }
        if (i == 1) {
            println(RED, "All organisations were blocked");
            return;
        }
        int index = getNum("Enter index: ");
        for (Organisation organisation : organisations) {
            if (!organisation.isBlocked()) {
                index--;
            }
            if (index == 0) {
                organisation.setBlocked(true);
                break;
            }
        }
    }

    public static void unblockOrganisation() {
        int i = 1;
        for (Organisation organisation : organisations) {
            if (organisation.isBlocked()) {
                println(i++ + ". " + organisation.getName());
            }
        }
        if (i == 1) {
            println(RED, "All organisations were unblocked");
            return;
        }
        int index = getNum("Enter index: ");
        for (Organisation organisation : organisations) {
            if (organisation.isBlocked()) {
                index--;
            }
            if (index == 0) {
                organisation.setBlocked(false);
                break;
            }
        }
    }

    public static void showOrganisation() {
        int index = 1;
        for (Organisation organisation : organisations) {
            println(index++ + ". " + organisation.getName());
        }
    }
}
