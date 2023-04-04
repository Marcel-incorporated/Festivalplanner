module com.example.festivalplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires datafaker;
    requires fxgraphics2d;
    requires javax.json;

    opens gui to javafx.fxml;
    exports gui;
    exports controllers;
    opens controllers to javafx.fxml;
    exports classes;
    opens classes to javafx.fxml;
}