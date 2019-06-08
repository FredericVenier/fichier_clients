package Controleur;

import FichierClients.MenuViews;
import Model.Client;
import Model.Prestation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Date;

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
        updatePrestations();
    }

    public void updatePrestations() {
        vbox.getChildren().remove(0,vbox.getChildren().size());

        for(Prestation p : client.getPrestations()) {
            HBox hbox = new HBox();
            VBox labelsVBox = new VBox();

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
                samePrestation(p);
            });
            hbox.getChildren().add(same);

            Button modifier = new Button();
            modifier.setText("modifier");
            modifier.setOnAction((event) -> {
                editPrestation(p);
            });
            hbox.getChildren().add(modifier);

            Button supprimer = new Button();
            supprimer.setText("supprimer");
            supprimer.setOnAction((event) -> {
                removePrestation(p);
            });
            hbox.getChildren().add(supprimer);

            vbox.getChildren().add(hbox);
        }
    }

    public void samePrestation(Prestation p) {
        Prestation newPrestation = new Prestation(p);
        newPrestation.setDate(new Date());

        client.addPrestation(newPrestation);
        updatePrestations();
    }

    public void editPrestation(Prestation prestation) {
        MenuViews.setViewEditPrestation(client, prestation);
    }

    public void removePrestation(Prestation prestation) {
        client.removePrestation(prestation);
        updatePrestations();
    }

    public void retour() {
        MenuViews.setViewSearch();
    }

    public void nouveau() {
        MenuViews.setViewNewPrestation(client);
    }
}
