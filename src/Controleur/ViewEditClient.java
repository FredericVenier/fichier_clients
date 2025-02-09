package Controleur;

import FichierClients.MenuViews;
import Model.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ViewEditClient {
    private Client client;

    @FXML
    private TextField lastname;
    @FXML
    private TextField firstname;
    @FXML
    private TextField email;
    @FXML
    private Button ajouter;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField address;

    public ViewEditClient(Client client) {
        this.client = client;
    }

    public void init() {
        lastname.setPromptText(client.getLastname());
        firstname.setPromptText(client.getFirstname());
        email.setPromptText(client.getEmail());
        address.setPromptText(client.getAddress());
        phoneNumber.setPromptText(client.getPhoneNumber());

        ajouter.setText("modifier");
    }

    public void retour() {
        MenuViews.setViewSearch();
    }

    public void ajouter() {
        Client editedClient = new Client(firstname.getText().equals("")? client.getFirstname() : firstname.getText(),
                lastname.getText().equals("")? client.getLastname() : lastname.getText(),
                email.getText().equals("")? client.getEmail() : email.getText(),
                address.getText().equals("")? client.getAddress() : address.getText(),
                phoneNumber.getText().equals("")? client.getPhoneNumber() : phoneNumber.getText(),
                client.getHashCode());

        if(editedClient.isWellCreated()) {
            MenuViews.editClient(client, editedClient);
            MenuViews.setViewSearch();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Un problème a été rencontré.\nLe client n'a pas pu être modifié.");
            alert.setContentText(editedClient.getErrorMessage());

            alert.showAndWait();
        }
    }
}
