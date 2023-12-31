package radio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RadioFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RadioFX.class.getResource("view/RadioFX.fxml"));
        AnchorPane pane = loader.load();
        primaryStage.setScene(new Scene(pane));

        primaryStage.show();
    }
}
