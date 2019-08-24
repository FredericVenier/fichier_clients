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

    public Client() {
        prestations = new ArrayList<>();
        hashCode = null;
    }

    public Client(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;

        generateHashCode();
        prestations = new ArrayList<>();
    }

    public Client(String firstname, String lastname, String email, String hashCode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.hashCode = hashCode;
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
        return !firstname.equals("") && !lastname.equals("") && !email.equals("") && email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
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

        if(email.equals(""))
            errorMessage += "L'e-mail n'a pas été indiqué.";
        else if(!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))
            errorMessage += "Le format de l'e-mail n'est pas valide.\nFormat attendu : xx@xx.xx";

        return errorMessage;
    }

    private void generateHashCode() {
        String toBeHashed = firstname;
        toBeHashed += lastname;
        toBeHashed += System.currentTimeMillis();

        this.hashCode = "" + toBeHashed.hashCode();
    }
}
