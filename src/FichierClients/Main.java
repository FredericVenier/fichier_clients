package FichierClients;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage mainStage = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        MenuViews.setViewSearch();
        mainStage.show();

    }

    public static void setStage(Stage s) {
        if(mainStage==null) mainStage = s;
        else {
            mainStage.setTitle(s.getTitle());
            mainStage.setScene(s.getScene());
        }
    }

    public static void main(String[] args) {
        //MenuViews.setViewSearch();
        launch(args);
    }
}