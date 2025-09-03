package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import utilities.Paths;

public class App extends Application {

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(@NotNull Stage stage) throws Exception {


        AnchorPane load = FXMLLoader.load(getClass().getResource("/application/agenda.fxml"));
        Scene scene = new Scene( load);
        stage.setScene(scene);


        stage.show();
    }
}
