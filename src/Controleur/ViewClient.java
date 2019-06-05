package Controleur;

import FichierClients.MenuViews;
import Model.Client;
import Model.Prestation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewClient {
    Client client;

    @FXML
    private Label firstname;
    @FXML
    private Label lastname;
    @FXML
    private Label email;
    @FXML
    private VBox vbox;

    public ViewClient(Client client) {
        this.client = client;
    }

    public void init() {
        firstname.setText(client.getFirstname());
        lastname.setText(client.getLastname());
        email.setText(client.getEmail());

        for(Prestation p : client.getPrestations()) {
            HBox hbox = new HBox();
            VBox labelsVBox = new VBox();
            VBox buttonsVBox = new VBox();

            Label date = new Label();
            date.setText(p.getDate().toString());
            labelsVBox.getChildren().add(date);

            Label description = new Label();
            description.setText(p.getDescription());
            labelsVBox.getChildren().add(description);

            Label price = new Label();
            price.setText(Float.toString(p.getPrice()));
            labelsVBox.getChildren().add(price);

            hbox.getChildren().add(labelsVBox);

            Button same = new Button();
            same.setText("même prestation");
            same.setOnAction((event) -> {
                client.addPrestation_atTheBegining(p);
            });
            buttonsVBox.getChildren().add(same);

            hbox.getChildren().add(buttonsVBox);

            vbox.getChildren().add(hbox);
        }
    }

    public void retour() {
        MenuViews.setViewSearch();
    }

    public void nouveau() {
        MenuViews.setViewNewPrestation(client);
    }
}
