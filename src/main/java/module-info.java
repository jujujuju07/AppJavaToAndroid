module com.example.testappjavatoandroid {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;


    opens com.example.testappjavatoandroid to javafx.fxml;
    opens com.example.testappjavatoandroid.model.methode.model to com.google.gson;
    opens com.example.testappjavatoandroid.model.methode to com.google.gson;
    opens com.example.testappjavatoandroid.model.button to com.google.gson, javafx.fxml;
    exports com.example.testappjavatoandroid;
    exports com.example.testappjavatoandroid.model.methode;
    exports com.example.testappjavatoandroid.model.methode.model;
    exports com.example.testappjavatoandroid.model.button;
    exports com.example.testappjavatoandroid.model.execute;
}