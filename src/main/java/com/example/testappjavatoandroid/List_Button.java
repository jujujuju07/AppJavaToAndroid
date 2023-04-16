package com.example.testappjavatoandroid;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class List_Button {
    private HBox buttonbasique(VBox vBox, String label, String identification, String valuetextField){
        Button buttonSupprimmer = new Button();
        Label labeltext = new Label(" " + label + " ");
        TextField textField = new TextField();
        HBox hBox = new HBox();
        ImageView imageView = new ImageView("https://cdn-icons-png.flaticon.com/512/561/561125.png");
        VBox vBoxmouve = new VBox();
        Button buttonUP = new Button();
        Button buttonDOWN = new Button();
        ImageView imageViewUP = new ImageView("image/fleche.png");
        ImageView imageViewDOWN = new ImageView("image/fleche.png");

        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        buttonSupprimmer.setStyle("-fx-border-color: Black");
        buttonSupprimmer.setPrefHeight(40);
        buttonSupprimmer.setGraphic(imageView);
        buttonSupprimmer.setContentDisplay(ContentDisplay.CENTER);
        buttonSupprimmer.setId("ButtonSuprimer");
        labeltext.setAlignment(Pos.CENTER);
        labeltext.setStyle("-fx-border-color: Black");
        labeltext.setPrefHeight(40);
        labeltext.setId("Label");
        textField.setStyle("-fx-border-color: Black");
        textField.setPrefHeight(40);
        textField.setId("TextField");
        textField.setText(valuetextField);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(labeltext);
        hBox.getChildren().add(textField);
        hBox.getChildren().add(buttonSupprimmer);
        hBox.getChildren().add(vBoxmouve);
        hBox.setId(identification);
        buttonSupprimmer.setOnAction(event -> {
            hBox.getChildren().clear();
            vBox.getChildren().remove(hBox);
        });
        imageViewUP.setFitWidth(20);
        imageViewUP.setFitHeight(20);
        imageViewUP.setRotate(180);
        buttonUP.setMinSize(10,10);
        buttonUP.setPrefSize(37,20);
        buttonUP.setGraphic(imageViewUP);
        buttonUP.setStyle("-fx-border-color: Black");
        buttonUP.setOnAction(event -> {
            int postion = vBox.getChildren().indexOf(hBox);
            if (postion > 0){
                vBox.getChildren().remove(hBox);
                vBox.getChildren().add(postion-1,hBox);
            }
        });
        imageViewDOWN.setFitWidth(20);
        imageViewDOWN.setFitHeight(20);
        buttonDOWN.setMinSize(10,10);
        buttonDOWN.setPrefSize(37,20);
        buttonDOWN.setGraphic(imageViewDOWN);
        buttonDOWN.setStyle("-fx-border-color: Black");
        buttonDOWN.setOnAction(event -> {
            int postion = vBox.getChildren().indexOf(hBox);
            if (postion < vBox.getChildren().size()-1){
                vBox.getChildren().remove(hBox);
                vBox.getChildren().add(postion+1,hBox);
            }
        });
        vBoxmouve.getChildren().add(buttonUP);
        vBoxmouve.getChildren().add(buttonDOWN);

        return hBox;
    }
    public HBox buttonVolumePlus(VBox vBox,String valuetextField){
        return buttonbasique(vBox,"volume +","volumePlus",valuetextField);

    }

    public String decodeTestButton(HBox hBox){
        System.out.println(hBox.getId());
        TextField textField = (TextField) hBox.lookup("#TextField");
        return textField.getText();
    }

    public HBox buttonVolumeMoins(VBox vBox,String valuetextField){
        return buttonbasique(vBox,"volume -","VolumeMoins",valuetextField);
    }
}
