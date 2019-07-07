package Controleur;

import FichierClients.MenuViews;
import Model.Client;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ViewSearch {
    private List<Client> clients;

    @FXML
    private VBox vbox;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;

    public ViewSearch(List<Client> clients) {
        this.clients = clients;
    }

    public void init() {
        updateClientsDisplayed(this.clients);
    }

    public void newClient() {
        MenuViews.setViewNewClient();
    }

    public void seeClient(Client client) {
        MenuViews.setViewClient(client);
    }

    public void clickOnSearch() {
        updateClientsDisplayed(search());
    }

    public void updateResearch() {
        updateClientsDisplayed(search());
    }

    private void updateClientsDisplayed(List<Client> clients) {
        vbox.getChildren().remove(0,vbox.getChildren().size());

        for(Client c : clients) {
            HBox hbox = new HBox();

            Label lastname = new Label();
            lastname.setText(c.getLastname());
            HBox.setMargin(lastname, new Insets(0,0,0,5));
            hbox.getChildren().add(lastname);

            Label firstname = new Label();
            firstname.setText(c.getFirstname());
            hbox.getChildren().add(firstname);

            Pane rightAlignmentPane = new Pane();
            HBox.setHgrow(rightAlignmentPane, Priority.ALWAYS);
            hbox.getChildren().add(rightAlignmentPane);

            Button voir = new Button();
            voir.setText("voir");
            voir.setOnAction((event) -> {
                seeClient(c);
            });
            Tooltip voirTooltip = new Tooltip("Voir les détails du client et ses précédentes prestations.");
            voir.setTooltip(voirTooltip);
            HBox.setMargin(voir, new Insets(5,0,5,0));
            hbox.getChildren().add(voir);

            Button modifier = new Button();
            modifier.setText("modifier");
            modifier.setOnAction((event) -> {
                editClient(c);
            });
            Tooltip modifierTooltip = new Tooltip("Modifier les informations du client.");
            modifier.setTooltip(modifierTooltip);
            HBox.setMargin(modifier, new Insets(5,0,5,0));
            hbox.getChildren().add(modifier);

            Button supprimer = new Button();
            supprimer.setText("supprimer");
            supprimer.setOnAction((event) -> {
                removeClient(c);
            });
            Tooltip supprimerTooltip = new Tooltip("Supprimer le client du fichier clients.");
            supprimer.setTooltip(supprimerTooltip);
            HBox.setMargin(supprimer, new Insets(5,5,5,0));
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

    private void editClient(Client c) {
        MenuViews.setViewEditClient(c);
    }

    private void removeClient(Client c) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce client du fichier ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            this.clients.remove(c);
            updateClientsDisplayed(clients);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    private List<Client> search() {
        String strFirstname = firstname.getText();
        String strLastname = lastname.getText();

        List<Client> searchingResult = recursive_search(strLastname, clients);

        if(!strFirstname.equals("") && !strLastname.equals("")) {
            List<Client> tmp = new ArrayList<>(searchingResult);
            for(Client c : tmp) {
                if(!c.getFirstname().toLowerCase().startsWith(strFirstname.toLowerCase())) {
                    searchingResult.remove(c);
                }
            }
        }

        return searchingResult;
    }

    private List<Client> recursive_search(String lastname, List<Client> clients) {
        List<Client> searchingResult = new ArrayList<>();

        if(clients.size() == 0 || lastname.equals("")) {
            return clients;
        }

        int i = clients.size()/2;

        if(clients.get(i).getLastname().toLowerCase().startsWith(lastname.toLowerCase())) {
            searchingResult.add(clients.get(i));

            int j = i-1;
            if(j>=0) {
                while (j >= 0 && clients.get(j).getLastname().toLowerCase().startsWith(lastname.toLowerCase())) {
                    searchingResult.add(0, clients.get(j));
                    j = j - 1;
                }
            }

            j = i+1;
            if(j<clients.size()) {
                while (j < clients.size() && clients.get(j).getLastname().toLowerCase().startsWith(lastname.toLowerCase())) {
                    searchingResult.add(clients.get(j));
                    j = j + 1;
                }
            }

            return searchingResult;

        } else if(clients.get(i).getLastname().compareToIgnoreCase(lastname)>0) {
            return recursive_search(lastname, clients.subList(0,i));

        } else if(i<clients.size()-1) {
            return recursive_search(lastname, clients.subList(i+1,clients.size()));
        }

        return searchingResult;
    }
}
