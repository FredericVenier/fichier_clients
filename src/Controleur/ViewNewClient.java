package Controleur;

import FichierClients.MenuViews;
import Model.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ViewNewClient {

    @FXML
    private TextField lastname;
    @FXML
    private TextField firstname;
    @FXML
    private TextField email;

    public ViewNewClient() {

    }

    public void retour() {
        MenuViews.setViewSearch();
    }

    public void ajouter() {
        Client client = new Client(firstname.getText(), lastname.getText(), email.getText());

        if(client.isWellCreated()) {
            MenuViews.addClient(client);
            MenuViews.setViewSearch();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Un problème a été rencontré.\nLe client n'a pas pu être ajouté au fichier.");
            alert.setContentText(client.getErrorMessage());

            alert.showAndWait();
        }
    }
}
