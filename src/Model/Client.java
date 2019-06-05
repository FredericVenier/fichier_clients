package Model;

import java.util.ArrayList;
import java.util.List;

public class Client implements Comparable<Client>{
    private String firstname;
    private String lastname;
    private String email;
    private List<Prestation> prestations;

    public Client() {
        prestations = new ArrayList<>();
    }

    public Client(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;

        prestations = new ArrayList<>();
    }

    public void setFirstname(String newFirstname) {
        this.firstname = newFirstname;
    }

    public void setLastname(String newLastname) {
        this.lastname = newLastname;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void addPrestation_atTheBegining(Prestation newPrestation) {
        this.prestations.add(0, newPrestation);
    }

    public void addPrestation(Prestation newPrestation) {
        this.prestations.add(newPrestation);
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public List<Prestation> getPrestations() {
        return this.prestations;
    }

    public int compareTo(Client client) {
        return this.lastname.compareToIgnoreCase(client.getLastname());
    }

    public String toString() {
        return this.lastname + " " + this.firstname;
    }

    public boolean isWellCreated() {
        return !firstname.equals("") && !lastname.equals("") && !email.equals("");
    }
}
