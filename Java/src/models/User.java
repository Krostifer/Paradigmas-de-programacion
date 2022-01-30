package models;
import java;

public class User {
    private String name;
    private String pass;
    private int reputation;

    public User(String name, String pass, int reputation) {
        this.name = name;
        this.pass = pass;
        this.reputation = reputation;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }
}
