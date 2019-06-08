package Controleur;

import FichierClients.MenuViews;
import Model.Client;
import Model.Prestation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

public class ViewEditPrestation {
    private Prestation prestation;
    private Client client;

    @FXML
    private TextField date;
    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private Button ajouter;

    public ViewEditPrestation(Client client, Prestation prestation) {
        this.client = client;
        this.prestation = prestation;
    }

    public void init() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date.setPromptText(dateFormat.format(prestation.getDate()));
        description.setPromptText(prestation.getDescription());
        price.setPromptText(Float.toString(prestation.getPrice()));
        ajouter.setText("modifier");
    }

    public void retour() {
        MenuViews.setViewClient(client);
    }

    public void ajouter() {
        Prestation editedPrestation =  new Prestation();

        editedPrestation.setPrice(price.getText().equals("")? prestation.getPrice() : Float.parseFloat(price.getText()));
        editedPrestation.setDescription(description.getText().equals("")? prestation.getDescription() : description.getText());
        try {
            editedPrestation.setDate(date.getText().equals("")? prestation.getDate() : new SimpleDateFormat("dd/MM/yyyy").parse(date.getText()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(prestation.isWellCreated()) {
            prestation.Copy(editedPrestation);
            Collections.sort(client.getPrestations(), Collections.reverseOrder());
            MenuViews.setViewClient(client);
        }
    }
}
