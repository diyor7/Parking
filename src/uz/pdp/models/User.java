package uz.pdp.models;

/**
 * @author Doston Bokhodirov, Wed 5:00 PM. 11/10/2021
 */
public class User {
    private String parkID;
    private String organisationID;
    private String username;
    private String password;
    private boolean isBlock;
    private boolean isAdmin;
    private boolean isSuperAdmin;

    public User() {}

    public User(String parkID, String organisationID, String username, String password, boolean isBlock, boolean isAdmin) {
        this.parkID = parkID;
        this.organisationID = organisationID;
        this.username = username;
        this.password = password;
        this.isBlock = isBlock;
        this.isAdmin = isAdmin;
        this.isSuperAdmin = false;
    }

    public User(String parkID, String organisationID, String username, String password) {
        this.parkID = parkID;
        this.organisationID = organisationID;
        this.username = username;
        this.password = password;
    }

    public String getParkID() {
        return parkID;
    }

    public void setParkID(String parkID) {
        this.parkID = parkID;
    }

    public String getOrganisationID() {
        return organisationID;
    }

    public void setOrganisationID(String organisationID) {
        this.organisationID = organisationID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }
}
