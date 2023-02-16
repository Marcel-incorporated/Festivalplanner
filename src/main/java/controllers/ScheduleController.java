package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class ScheduleController {
    @FXML
    void onImportButton() {
        try {                                           //try importing file, showing error when unsuccessfull
            Serializer.DeserializeFestival();
            notificationPrompt(false, "Successfully import festival file :)");
        } catch (Exception e) {
            notificationPrompt(true, "Unable to import festival file :(");
        }
    }

    public void notificationPrompt(boolean error, String message) {
        if (error) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR, message);
            alert1.setTitle("Error");
            alert1.setHeaderText("Error");
            alert1.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
            alert.setTitle("Information");
            alert.setHeaderText("Information");
            alert.showAndWait();
        }
    }
}
