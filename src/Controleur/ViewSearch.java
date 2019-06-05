package Controleur;

import FichierClients.MenuViews;
import Model.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class ViewSearch {
    private List<Client> clients;

    @FXML
    private VBox vbox;

    public ViewSearch(List<Client> clients) {
        this.clients = clients;
    }

    public void init() {
        for(Client c : clients) {
            HBox hbox = new HBox();

            Label lastname = new Label();
            lastname.setText(c.getLastname());
            hbox.getChildren().add(lastname);

            Label firstname = new Label();
            firstname.setText(c.getFirstname());
            hbox.getChildren().add(firstname);

            Button voir = new Button();
            voir.setText("voir");
            voir.setOnAction((event) -> {
                seeClient(c);
            });
            hbox.getChildren().add(voir);

            vbox.getChildren().add(hbox);
        }
    }

    public void newClient() {
        MenuViews.setViewNewClient();
    }

    public void seeClient(Client client) {
        MenuViews.setViewClient(client);
    }
}
