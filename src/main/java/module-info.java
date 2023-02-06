module com.example.festivalplanner {
    requires javafx.controls;
    requires javafx.fxml;

    opens GUI to javafx.fxml;
    exports GUI;
    exports controllers;
    opens controllers to javafx.fxml;
    exports classes;
    opens classes to javafx.fxml;
}