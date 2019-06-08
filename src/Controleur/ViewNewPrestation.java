package Controleur;

import FichierClients.MenuViews;
import Model.Client;
import Model.Prestation;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewNewPrestation {
    private Client client;

    @FXML
    private TextField date;
    @FXML
    private TextField description;
    @FXML
    private TextField price;

    public ViewNewPrestation(Client client) {
        this.client = client;
    }

    public void init() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date.setPromptText(dateFormat.format(new Date()));
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

        if(date.getText().equals("")) {
            prestation.setDate(new Date());
        } else {
            try {
                prestation.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(date.getText()));
            } catch (Exception ex) {
                //nothing to do here, it's normal to possibly have an exception
            }
        }

        if(prestation.isWellCreated()) {
            client.addPrestation(prestation);
            MenuViews.setViewClient(client);
        }
    }
}