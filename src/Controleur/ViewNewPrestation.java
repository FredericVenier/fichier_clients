package Controleur;

import FichierClients.MenuViews;
import Model.Client;
import Model.Prestation;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    public void retour() {
        MenuViews.setViewClient(client);
    }

    public void ajouter() {
        Prestation prestation =  new Prestation();

        prestation.setPrice(Float.parseFloat(price.getText()));
        prestation.setDescription(description.getText());
        try {
            prestation.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(date.getText()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(prestation.isWellCreated()) {
            client.addPrestation_atTheBegining(prestation);
            MenuViews.setViewClient(client);
        }
    }
}
