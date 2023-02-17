package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import org.jfree.fx.FXGraphics2D;

public class ScheduleController {

    @FXML
    public Canvas canvasSchedule;
    @FXML
    void onImportButton() {
        try {                                           //try importing file, showing error when unsuccessfull
            Serializer.DeserializeFestival();
            notificationPrompt(false, "Successfully import festival file :)");
        } catch (Exception e) {
            notificationPrompt(true, "Unable to import festival file :(");
        }
        draw(new FXGraphics2D(canvasSchedule.getGraphicsContext2D()));
    }

    public void draw(FXGraphics2D graphics) {
        graphics.drawLine(  70, 40, 30, 90);
        graphics.drawLine(70, 40, 110, 90);
        graphics.drawLine(110, 90, 110, 150);
        graphics.drawLine(110, 150, 30, 150);
        graphics.drawLine(30, 150, 30, 90);
        graphics.drawLine(40, 150, 40, 110);
        graphics.drawLine(40, 110, 60, 110);
        graphics.drawLine(60, 110, 60, 150);
        graphics.drawLine(70, 110, 100, 110);
        graphics.drawLine(100, 110, 100, 130);
        graphics.drawLine(100, 130, 70, 130);
        graphics.drawLine(70, 130, 70, 110);
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
