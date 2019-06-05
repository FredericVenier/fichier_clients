package FichierClients;

import Controleur.ViewClient;
import Controleur.ViewNewClient;
import Controleur.ViewNewPrestation;
import Controleur.ViewSearch;
import Model.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuViews {
    private static List<Client> clients;

    public static void setViewSearch() {

        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        FXMLLoader loader = new FXMLLoader();

        ViewSearch view = new ViewSearch(clients);
        loader.setLocation(Main.class.getResource("/Controleur/Search.fxml"));
        loader.setControllerFactory(instantiatedClass -> view );
        try {
            root.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        view.init();
        stage.setTitle("Fichier Clients");
        stage.setScene(new Scene(root, 1024, 576));
        stage.getScene().getStylesheets().add("css/style.css");
        Main.setStage(stage);
    }

    public static void setViewNewClient() {

        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        FXMLLoader loader = new FXMLLoader();

        ViewNewClient view = new ViewNewClient();
        loader.setLocation(Main.class.getResource("/Controleur/NewClient.fxml"));
        loader.setControllerFactory(instantiatedClass -> view );
        try {
            root.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Fichier Clients");
        stage.setScene(new Scene(root, 1024, 576));
        stage.getScene().getStylesheets().add("css/style.css");
        Main.setStage(stage);
    }

    public static void setViewClient(Client client) {

        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        FXMLLoader loader = new FXMLLoader();

        ViewClient view = new ViewClient(client);
        loader.setLocation(Main.class.getResource("/Controleur/Client.fxml"));
        loader.setControllerFactory(instantiatedClass -> view );
        try {
            root.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        view.init();
        stage.setTitle("Fichier Clients");
        stage.setScene(new Scene(root, 1024, 576));
        stage.getScene().getStylesheets().add("css/style.css");
        Main.setStage(stage);
    }

    public static void setViewNewPrestation(Client client) {

        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        FXMLLoader loader = new FXMLLoader();

        ViewNewPrestation view = new ViewNewPrestation(client);
        loader.setLocation(Main.class.getResource("/Controleur/NewPrestation.fxml"));
        loader.setControllerFactory(instantiatedClass -> view );
        try {
            root.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Fichier Clients");
        stage.setScene(new Scene(root, 1024, 576));
        stage.getScene().getStylesheets().add("css/style.css");
        Main.setStage(stage);
    }


    public static void setClients() {
        //clients = JSONHandler.loadClients();
        clients = new ArrayList<>();
    }

    public static void addClient(Client newClient) {
        clients.add(newClient);
        Collections.sort(clients);
    }

}
