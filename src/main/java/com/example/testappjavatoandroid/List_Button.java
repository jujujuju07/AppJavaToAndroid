package com.example.testappjavatoandroid;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class List_Button {
    private HBox buttonbasique(String label, String identification, String valuetextField){
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
        textField.setText(valuetextField);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(labeltext);
        hBox.getChildren().add(textField);
        hBox.getChildren().add(buttonSupprimmer);
        hBox.setId(identification);
        buttonSupprimmer.setOnAction(event -> {
            hBox.getChildren().clear();
        });
        return hBox;
    }
    public HBox buttonVolumePlus(String valuetextField){
        return buttonbasique("volume +","volumePlus",valuetextField);

    }

    public String decodeTestButton(HBox hBox){
        System.out.println(hBox.getId());
        TextField textField = (TextField) hBox.lookup("#TextField");
        return textField.getText();
    }

    public HBox buttonVolumeMoins(String valuetextField){
        return buttonbasique("volume -","VolumeMoins",valuetextField);
    }
}
