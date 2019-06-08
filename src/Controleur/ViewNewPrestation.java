package Controleur;

import FichierClients.MenuViews;
import Model.Client;
import Model.Prestation;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class ViewNewPrestation {
    private Client client;

    @FXML
    private DatePicker date;
    @FXML
    private TextField description;
    @FXML
    private TextField price;

    public ViewNewPrestation(Client client) {
        this.client = client;
    }

    public void init() {
        date.setValue(LocalDate.now());
    }

    public void retour() {
        MenuViews.setViewClient(client);
    }

    public void ajouter() {
        Prestation prestation =  new Prestation();

        try {
            prestation.setPrice(Float.parseFloat(price.getText()));
        } catch (Exception ex) {
            //nothing to do here, it's normal to possibly have an exception
        }

        prestation.setDescription(description.getText());

        String strDate = (date.getValue().getDayOfMonth()<10? ("0"+date.getValue().getDayOfMonth()) : (date.getValue().getDayOfMonth()+""))
                + "/" + (date.getValue().getMonthValue()<10? ("0"+date.getValue().getMonthValue()) : (date.getValue().getMonthValue()+""))
                + "/" + date.getValue().getYear();

        try {
            prestation.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(strDate));
        } catch(Exception ex) {
            //nothing to do here, it's normal to possibly have an exception
        }

        if(prestation.isWellCreated()) {
            client.addPrestation(prestation);
            MenuViews.setViewClient(client);
        }
    }
}