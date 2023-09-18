package radio.view;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetFrequenzController {
    private String ln;
    @FXML
    Button okButton;
    @FXML
    Button cancelButton;
    @FXML
    TextField text;
    @FXML
    Parent pane;

    private boolean okPressed = false;

    @FXML
    private void okHandle() {
        ln = text.getText();
        okPressed = true;
        exitHandle();
    }

    @FXML
    private void exitHandle() {
        ((Stage)pane.getScene().getWindow()).close();
    }

    public String getLn() {
        return ln;
    }

    public boolean isOkPressed() {
        return okPressed;
    }
}
