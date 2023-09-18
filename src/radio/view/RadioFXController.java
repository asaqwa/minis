package radio.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import radio.model.Radio;

import java.io.IOException;
import java.util.Arrays;

public class RadioFXController {
    private final Radio radio = new Radio();

    @FXML
    Button einAusButton;

    @FXML
    Button lauterButton;

    @FXML
    Button leiserButton;

    @FXML
    Label lautstaerkeLabel;

    @FXML
    Button setSenderButton;

    @FXML
    Button frequenzZugeben;

    @FXML
    Button frequenzAbnehmen;

    @FXML
    Pane userPane;

    @FXML
    Label oneLabel;

    @FXML
    Label secLabel;

    Button[] buttons;

    @FXML
    public void initialize() {
        lautstaerkeLabel.setText("" + radio.getLautstaerke());
        setSenderButton.setText("" + radio.getFrequenz());
        buttons =  new Button[] {lauterButton, leiserButton, frequenzZugeben, setSenderButton, frequenzAbnehmen};
        refreshUserZone();
    }

    @FXML
    private void einAus() {
        if (radio.isEingeschaltet()) aus();
        else an();
    }

    private void an() {
        radio.an();
        refreshUserZone();
    }

    private void aus() {
        radio.aus();
        refreshUserZone();
    }

    private void refreshUserZone() {
        einAusButton.setText((radio.isEingeschaltet()? "Aus" : "Ein"));
        Arrays.stream(buttons).forEach(b-> b.setDisable(!radio.isEingeschaltet()));
        String color = radio.isEingeschaltet()? "black": "gray";
        lautstaerkeLabel.setStyle("-fx-text-fill:" + color);
        oneLabel.setStyle("-fx-text-fill:" + color);
        secLabel.setStyle("-fx-text-fill:" + color);
    }

    @FXML
    private void volumeUp() {
        lautstaerkeLabel.setText("" + radio.lauter());
    }

    @FXML
    private void volumeDown() {
        lautstaerkeLabel.setText("" + radio.leiser());
    }

    @FXML
    private void frequenzUp() {
        radio.setSender(radio.getFrequenz()+0.01);
        setSenderButton.setText("" + radio.getFrequenz());
    }

    @FXML
    private void frequenzDown() {
        radio.setSender(radio.getFrequenz()-0.01);
        setSenderButton.setText("" + radio.getFrequenz());
    }

    @FXML
    private void setFrequenz() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SetFrequenz.fxml"));
            AnchorPane root = loader.load();
            SetFrequenzController controller = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(userPane.getScene().getWindow());

            stage.showAndWait();

            if (controller.isOkPressed()) {
                radio.setSender(Double.parseDouble(controller.getLn()));
                setSenderButton.setText("" + radio.getFrequenz());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
