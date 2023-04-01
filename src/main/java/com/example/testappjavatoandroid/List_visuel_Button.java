package com.example.testappjavatoandroid;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class List_visuel_Button {
    public HBox testButton(ArrayList<HBox> hBoxes){
        Button button = new Button("button");
        Button buttonSupprimmer = new Button();
        Label label = new Label("label");
        TextField textField = new TextField();
        HBox hBox = new HBox();
        ImageView imageView = new ImageView("https://cdn-icons-png.flaticon.com/512/561/561125.png");

        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        button.setStyle("-fx-border-color: Black");
        button.setPrefHeight(30);
        button.setId("Button");
        buttonSupprimmer.setStyle("-fx-border-color: Black");
        buttonSupprimmer.setPrefHeight(30);
        buttonSupprimmer.setGraphic(imageView);
        buttonSupprimmer.setContentDisplay(ContentDisplay.CENTER);
        buttonSupprimmer.setId("ButtonSuprimer");
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-border-color: Black");
        label.setPrefHeight(30);
        label.setId("Label");
        textField.setStyle("-fx-border-color: Black");
        textField.setPrefHeight(30);
        textField.setId("TextField");
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(label);
        hBox.getChildren().add(textField);
        hBox.getChildren().add(button);
        hBox.getChildren().add(buttonSupprimmer);
        buttonSupprimmer.setOnAction(event -> {
            hBox.getChildren().clear();
            hBoxes.remove(hBox);
        });
        return hBox;

    }

    public String decodeTestButton(HBox hBox){
        TextField textField = (TextField) hBox.lookup("#TextField");
        return textField.getText();
    }

    public HBox button12(ArrayList<HBox> hBoxes){
        Button button = new Button("button");
        Label label = new Label("salut");
        HBox hBox = new HBox();

        button.setStyle("-fx-border-color: Black");
        button.setPrefHeight(30);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-border-color: Black");
        label.setPrefHeight(30);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(button);
        hBox.getChildren().add(label);
        button.setOnAction(event -> {
            hBox.getChildren().clear();
            hBoxes.remove(hBox);
        });

        return hBox;
    }
}
