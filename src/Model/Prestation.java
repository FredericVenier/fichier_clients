package Model;

import java.util.Date;

public class Prestation {
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
}
