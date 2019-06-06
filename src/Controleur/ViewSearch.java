package Controleur;

import FichierClients.MenuViews;
import Model.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

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

    private void updateClientsDisplayed(List<Client> clients) {
        vbox.getChildren().remove(0,vbox.getChildren().size());

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

    private List<Client> search() {
        String strFirstname = firstname.getText();
        String strLastname = lastname.getText();

        List<Client> searchingResult = recursive_search(strLastname, clients);

        if(!strFirstname.equals("")) {
            List<Client> tmp = new ArrayList<>(searchingResult);
            for(Client c : tmp) {
                if(!c.getFirstname().startsWith(strFirstname)) {
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

        if(clients.get(i).getLastname().startsWith(lastname)) {
            searchingResult.add(clients.get(i));

            int j = i-1;
            if(j>=0) {
                while (j >= 0 && clients.get(j).getLastname().startsWith(lastname)) {
                    searchingResult.add(0, clients.get(j));
                    j = j - 1;
                }
            }

            j = i+1;
            if(j<clients.size()) {
                while (j < clients.size() && clients.get(j).getLastname().startsWith(lastname)) {
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
