module com.example.testappjavatoandroid {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.testappjavatoandroid to javafx.fxml;
    opens com.example.testappjavatoandroid.methode.model to com.google.gson;
    opens com.example.testappjavatoandroid.methode.modelExecute to com.google.gson;
    opens com.example.testappjavatoandroid.methode to com.google.gson;
    opens com.example.testappjavatoandroid.button to com.google.gson, javafx.fxml;
    exports com.example.testappjavatoandroid;
    exports com.example.testappjavatoandroid.methode;
    exports com.example.testappjavatoandroid.methode.model;
    exports com.example.testappjavatoandroid.button;
}