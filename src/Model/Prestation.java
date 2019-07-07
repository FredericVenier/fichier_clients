package Model;

import java.util.Date;

public class Prestation implements Comparable<Prestation> {
    private Date date;
    private String description;
    private float price;

    public Prestation() {
        this.price = -1;
        this.date = null;
        this.description = "";
    }

    public Prestation(Date date, String description, float price) {
        this.date = date;
        this.description = description;
        this.price = price;
    }

    public Prestation(Prestation p) {
        this.date = new Date(p.getDate().toGMTString());
        this.description = new String(p.getDescription());
        this.price = new Float(p.getPrice());
    }

    public void Copy(Prestation p) {
        this.date = new Date(p.getDate().toGMTString());
        this.description = new String(p.getDescription());
        this.price = new Float(p.getPrice());
    }

    public void setDate(Date newDate) {
        this.date = newDate;
    }

    public void setDate(String newDate) {
        this.date = new Date(newDate);
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setPrice(float newPrice) {
        this.price = newPrice;
    }

    public Date getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }

    public float getPrice() {
        return this.price;
    }

    public boolean isWellCreated() {
        return !description.equals("") && date!=null && price>=0;
    }

    public int compareTo(Prestation prestation) {
        return this.date.compareTo(prestation.getDate());
    }

    public String getErrorMessage() {
        String errorMessage = "";

        if(description.equals(""))
            errorMessage += "Veuillez décrire la prestation.\n";

        if(date == null)
            errorMessage += "La date indiquée n'est pas valide.\nFormat attendu : jj/mm/aaaa\n";

        if(price<0)
            errorMessage += "Le prix indiqué n'est pas valide.\nFormat attendu : xx.xx";

        return errorMessage;
    }
}
