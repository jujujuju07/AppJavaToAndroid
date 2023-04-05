package com.example.testappjavatoandroid;

import com.example.testappjavatoandroid.methode.model.Donner;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalTime;
import java.util.Objects;

public class Modifier_Button {
    public Button volume;
    public Button volumePlus;
    public Button volumeMoins;
    public Button button13;
    public Button button14;
    public Button button15;
    public Button button16;
    public Button button17;
    public Button button18;
    public Button button19;
    public boolean aBoolean1 = true;
    public Button button110;
    public Button button111;
    public Button button121;
    public Button button131;
    public Button button141;
    public Button button151;
    public Button button161;
    public Button button171;
    public Button button181;
    public Button button191;
    public boolean aBoolean2 = true;
    public TextField textFieldText;
    public TextField textFieldImage;
    public Button buttonSave;
    public Label labelInfo;
    private Donner donner;


    public void volume(ActionEvent actionEvent) {
        if (aBoolean1){
            volumePlus.setPrefHeight(0);
            volumeMoins.setPrefHeight(0);
            button13.setPrefHeight(0);
            button14.setPrefHeight(0);
            button15.setPrefHeight(0);
            button16.setPrefHeight(0);
            button17.setPrefHeight(0);
            button18.setPrefHeight(0);
            button19.setPrefHeight(0);
            aBoolean1 = false;
        }else {
            volumePlus.setPrefHeight(30);
            volumeMoins.setPrefHeight(30);
            button13.setPrefHeight(30);
            button14.setPrefHeight(30);
            button15.setPrefHeight(30);
            button16.setPrefHeight(30);
            button17.setPrefHeight(30);
            button18.setPrefHeight(30);
            button19.setPrefHeight(30);
            aBoolean1 = true;
        }

    }

    public void button110(ActionEvent actionEvent) {
        if (aBoolean2){
            button111.setPrefHeight(0);
            button121.setPrefHeight(0);
            button131.setPrefHeight(0);
            button141.setPrefHeight(0);
            button151.setPrefHeight(0);
            button161.setPrefHeight(0);
            button171.setPrefHeight(0);
            button181.setPrefHeight(0);
            button191.setPrefHeight(0);
            aBoolean2 = false;
        }else {
            button111.setPrefHeight(30);
            button121.setPrefHeight(30);
            button131.setPrefHeight(30);
            button141.setPrefHeight(30);
            button151.setPrefHeight(30);
            button161.setPrefHeight(30);
            button171.setPrefHeight(30);
            button181.setPrefHeight(30);
            button191.setPrefHeight(30);
            aBoolean2 = true;
        }
    }

    public void donner(Donner donner){
        this.donner = donner;
        mettreDonner();
    }

    private void mettreDonner(){
        textFieldText.setText(donner.getText());
        textFieldImage.setText(donner.getImage());
    }

    public void buttonSave(ActionEvent actionEvent) {
        save();
    }

    public void save(){
        donner.setText(textFieldText.getText());
        donner.setImage(textFieldImage.getText());
        if (Objects.equals(donner.getText(), textFieldText.getText()) || Objects.equals(donner.getImage(), textFieldImage.getText())){
            LocalTime localTime = LocalTime.now();
            labelInfo.setText("save " + localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond());
        }
    }
}