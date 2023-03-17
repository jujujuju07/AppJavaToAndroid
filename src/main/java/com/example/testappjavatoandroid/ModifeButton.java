package com.example.testappjavatoandroid;

import com.example.testappjavatoandroid.methode.model.Donner;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class ModifeButton {
    public TextField TextText;
    public TextField TextImage;
    public TextField TextExecute;
    public VBox test;
    private Donner donner;
    private com.example.testappjavatoandroid.methode.modelExecute.Donner donnerE;
    private HelloController helloController;

    public void donner(Donner donner, com.example.testappjavatoandroid.methode.modelExecute.Donner donnerE, HelloController helloController){
        this.donner = donner;
        this.donnerE = donnerE;
        this.helloController = helloController;
        mettreText();
    }

    private void mettreText(){
        TextText.setText(donner.getText());
        TextImage.setText(donner.getImage());
        TextExecute.setText(donnerE.getExecute());
    }

    public void OK(ActionEvent actionEvent) {
        donner.setImage(TextImage.getText());
        donner.setText(TextText.getText());
        donnerE.setExecute(TextExecute.getText());
        helloController.stage.close();

    }
}
