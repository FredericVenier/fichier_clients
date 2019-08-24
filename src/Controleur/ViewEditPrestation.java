package Controleur;

import FichierClients.MenuViews;
import Model.Client;
import Model.JSONHandler;
import Model.Prestation;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;

public class ViewEditPrestation {
    private Prestation prestation;
    private Client client;

    @FXML
    private DatePicker date;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        int dateValue = Integer.parseInt(dateFormat.format(prestation.getDate()));
        int monthValue = Integer.parseInt(monthFormat.format(prestation.getDate()));
        int yearValue = Integer.parseInt(yearFormat.format(prestation.getDate()));

        date.setValue(LocalDate.of(yearValue, monthValue, dateValue));
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

        String strDate = (date.getValue().getDayOfMonth()<10? ("0"+date.getValue().getDayOfMonth()) : (date.getValue().getDayOfMonth()+""))
                + "/" + (date.getValue().getMonthValue()<10? ("0"+date.getValue().getMonthValue()) : (date.getValue().getMonthValue()+""))
                + "/" + date.getValue().getYear();

        try {
            editedPrestation.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(strDate));
        } catch(Exception ex) {
            //nothing to do here, it's normal to possibly have an exception
        }

        if(editedPrestation.isWellCreated()) {
            prestation.Copy(editedPrestation);
            JSONHandler.savePrestations(client);
            Collections.sort(client.getPrestations(), Collections.reverseOrder());
            MenuViews.setViewClient(client);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Un problème a été rencontré.\nLa prestation n'a pas pu être ajoutée au client.");
            alert.setContentText(editedPrestation.getErrorMessage());

            alert.showAndWait();
        }
    }
}
