package FichierClients;

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
    private static List<Song> songs;

    public static void setWheelView() {
        //JSONHandler.save(songs);

        Stage stage=new Stage();
        Pane root= new Pane();
        FXMLLoader loader;
        loader=new FXMLLoader();

        ViewWheel vw = new ViewWheel(0,144,500, songs);
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

    public static void setSongView(Song song) {
        ViewSong vs = new ViewSong(song);

        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("/Controleur/Song.fxml"));
        loader.setControllerFactory(instantiatedClass -> vs );
        try {
            root.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("U-ziK");
        stage.setScene(new Scene(root, 1024, 576));
        stage.getScene().getStylesheets().add("css/style.css");
        Main.setStage(stage);
    }

    public static void setStarsView(Song song, int nbOfGoodAnswers) {
        ViewStars vs = new ViewStars(song, nbOfGoodAnswers);

        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("/Controleur/Stars.fxml"));
        loader.setControllerFactory(instantiatedClass -> vs );
        try {
            root.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        vs.start(root);

        stage.setTitle("U-ziK");
        stage.setScene(new Scene(root, 1024, 576));
        stage.getScene().getStylesheets().add("css/style.css");
        Main.setStage(stage);
    }

    public static void setQuizzView(Song song) {
        ViewQuizz vq = new ViewQuizz(song);

        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("/Controleur/Quizz.fxml"));
        loader.setControllerFactory(instantiatedClass -> vq );
        try {
            root.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("U-ziK");
        stage.setScene(new Scene(root, 1024, 576));
        stage.getScene().getStylesheets().add("css/style.css");
        Main.setStage(stage);
    }

    public static void setSongs() {
        songs = JSONHandler.load();
    }

}
