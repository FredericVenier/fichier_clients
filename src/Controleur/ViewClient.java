package Controleur;

import FichierClients.MenuViews;
import Model.Client;
import Model.JSONHandler;
import Model.Prestation;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

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
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date.setText(dateFormat.format(p.getDate()));
            labelsVBox.getChildren().add(date);

            Label description = new Label();
            description.setText(p.getDescription());
            labelsVBox.getChildren().add(description);

            Label price = new Label();
            price.setText(Float.toString(p.getPrice()) + " €");
            labelsVBox.getChildren().add(price);

            HBox.setMargin(labelsVBox, new Insets(5,0,5,5));
            hbox.getChildren().add(labelsVBox);

            Pane rightAlignmentPane = new Pane();
            HBox.setHgrow(rightAlignmentPane, Priority.ALWAYS);
            hbox.getChildren().add(rightAlignmentPane);

            Button same = new Button();
            same.setText("même prestation");
            same.setOnAction((event) -> {
                samePrestation(p);
            });
            Tooltip sameTooltip = new Tooltip("Ajouter une prestation identique à la date du jour.");
            same.setTooltip(sameTooltip);
            hbox.getChildren().add(same);

            Button modifier = new Button();
            modifier.setText("modifier");
            modifier.setOnAction((event) -> {
                editPrestation(p);
            });
            Tooltip modifierTooltip = new Tooltip("Modifier les informations de la prestation.");
            modifier.setTooltip(modifierTooltip);
            hbox.getChildren().add(modifier);

            Button supprimer = new Button();
            supprimer.setText("supprimer");
            supprimer.setOnAction((event) -> {
                removePrestation(p);
            });
            Tooltip supprimerTooltip = new Tooltip("Supprimer la prestation au client.");
            supprimer.setTooltip(supprimerTooltip);
            HBox.setMargin(supprimer, new Insets(0,5,0,0));
            hbox.getChildren().add(supprimer);

            hbox.setSpacing(5);
            hbox.prefWidthProperty().bind(vbox.widthProperty());
            if(vbox.getChildren().size()%2 == 1) {
                hbox.setBackground((new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))));
            }
            hbox.setAlignment(Pos.CENTER);
            vbox.getChildren().add(hbox);
        }
    }

    public void samePrestation(Prestation p) {
        Prestation newPrestation = new Prestation(p);
        newPrestation.setDate(new Date());

        client.addPrestation(newPrestation);
        JSONHandler.savePrestations(client);
        updatePrestations();
    }

    public void editPrestation(Prestation prestation) {
        MenuViews.setViewEditPrestation(client, prestation);
    }

    public void removePrestation(Prestation prestation) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette prestation ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            client.removePrestation(prestation);
            JSONHandler.savePrestations(client);
            updatePrestations();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public void retour() {
        client.resetPrestations();
        MenuViews.setViewSearch();
    }

    public void nouveau() {
        MenuViews.setViewNewPrestation(client);
    }
}
