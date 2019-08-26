package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client implements Comparable<Client>{
    private String firstname;
    private String lastname;
    private String email;
    private List<Prestation> prestations;
    private String hashCode;
    private String address;
    private String phoneNumber;

    public Client() {
        prestations = new ArrayList<>();
        hashCode = null;
    }

    public Client(String firstname, String lastname, String email, String address, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;

        generateHashCode();
        prestations = new ArrayList<>();
    }

    public Client(String firstname, String lastname, String email, String address, String phoneNumber, String hashCode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.hashCode = hashCode;
        this.address = address;
        this.phoneNumber = phoneNumber;

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

    public void setAddress(String newAddress) {this.address = newAddress;}

    public void setPhoneNumber(String newPhoneNumber) {this.phoneNumber = newPhoneNumber;}

    public void setHashCode(String newHashCode) {if(this.hashCode == null)this.hashCode = newHashCode;}

    public void addPrestation(Prestation newPrestation) {
        this.prestations.add(newPrestation);
        Collections.sort(this.prestations, Collections.reverseOrder());
    }

    public void removePrestation(Prestation prestation) {this.prestations.remove(prestation);}

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getHashCode() {return this.hashCode;}

    public String getAddress() {return this.address;}

    public String getPhoneNumber() {return phoneNumber;}

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
        return !firstname.equals("") && !lastname.equals("") && (email.equals("") || email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) && (phoneNumber.isEmpty() || phoneNumber.matches("^[0-9]{10}$") || phoneNumber.matches("^[+][0-9]{11,12}$"));
    }

    public boolean isCompletelyLoaded() {
        return  (email != null && !email.isEmpty());
    }

    public void resetPrestations(){
        this.prestations = new ArrayList<>();
    }

    public String getErrorMessage() {
        String errorMessage = "";

        if(firstname.equals(""))
            errorMessage += "Le prénom n'a pas été indiqué.\n";

        if(lastname.equals(""))
            errorMessage += "Le nom de famille n'a pas été indiqué.\n";

        if(!email.isEmpty() && !email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))
            errorMessage += "Le format de l'e-mail n'est pas valide.\nFormat attendu : xx@xx.xx";

        if(!(phoneNumber.isEmpty() || phoneNumber.matches("^[0-9]{10}$") || phoneNumber.matches("^[+][0-9]{11,12}$")))
            errorMessage += "Le format du numéro de téléphone n'est pas valide.\nFormat attendu : xxxxxxxxxx ou +xxxxxxxxxxx.";

        return errorMessage;
    }

    private void generateHashCode() {
        String toBeHashed = firstname;
        toBeHashed += lastname;
        toBeHashed += System.currentTimeMillis();

        this.hashCode = "" + toBeHashed.hashCode();
    }
}
