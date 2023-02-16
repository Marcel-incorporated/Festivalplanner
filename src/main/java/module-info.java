module com.example.festivalplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires datafaker;
    requires fxgraphics2d;

    opens GUI to javafx.fxml;
    exports GUI;
    exports controllers;
    opens controllers to javafx.fxml;
    exports classes;
    opens classes to javafx.fxml;
}