package FichierClients;

import Model.Client;
import Model.JSONHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MenuViews {
    private static List<Client> clients;

    public static void setMainView() {
        //JSONHandler.save(songs);

        Stage stage=new Stage();
        Pane root= new Pane();
        FXMLLoader loader;
        loader=new FXMLLoader();

        ViewSearch view = new ViewSearch(0,144,500, clients);
        loader.setControllerFactory(instantiatedClass -> vw );
        vw.start(root);

        stage.setTitle("U-ziK");
        stage.setScene(new Scene(root, 1024, 576));
        stage.getScene().getStylesheets().add("css/style.css");
        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                vw.action(true);
            } else if(e.getCode() == KeyCode.Z) {
                vw.action(false);
            }
        });
        Main.setStage(stage);
    }

    public static void setClients() {
        clients = JSONHandler.loadClients();
    }

}
