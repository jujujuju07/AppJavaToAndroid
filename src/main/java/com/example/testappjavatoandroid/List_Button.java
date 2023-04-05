package com.example.testappjavatoandroid;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class List_Button {
    private HBox buttonbasique(ArrayList<HBox> hBoxes,String label){
        Button buttonSupprimmer = new Button();
        Label labeltext = new Label(" " + label + " ");
        TextField textField = new TextField();
        HBox hBox = new HBox();
        ImageView imageView = new ImageView("https://cdn-icons-png.flaticon.com/512/561/561125.png");

        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        buttonSupprimmer.setStyle("-fx-border-color: Black");
        buttonSupprimmer.setPrefHeight(30);
        buttonSupprimmer.setGraphic(imageView);
        buttonSupprimmer.setContentDisplay(ContentDisplay.CENTER);
        buttonSupprimmer.setId("ButtonSuprimer");
        labeltext.setAlignment(Pos.CENTER);
        labeltext.setStyle("-fx-border-color: Black");
        labeltext.setPrefHeight(30);
        labeltext.setId("Label");
        textField.setStyle("-fx-border-color: Black");
        textField.setPrefHeight(30);
        textField.setId("TextField");
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(labeltext);
        hBox.getChildren().add(textField);
        hBox.getChildren().add(buttonSupprimmer);
        buttonSupprimmer.setOnAction(event -> {
            hBox.getChildren().clear();
            hBoxes.remove(hBox);
        });
        return hBox;
    }
    public HBox buttonVolumePlus(ArrayList<HBox> hBoxes){
        return buttonbasique(hBoxes,"volume +");

    }

    public String decodeTestButton(HBox hBox){
        TextField textField = (TextField) hBox.lookup("#TextField");
        return textField.getText();
    }

    public HBox button12(ArrayList<HBox> hBoxes){
        return buttonbasique(hBoxes,"volume -");
    }
}
