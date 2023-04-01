package com.example.testappjavatoandroid;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class Modifier_Button {
    public Button button1;
    public Button button11;
    public Button button12;
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


    public void button1(ActionEvent actionEvent) {
        if (aBoolean1){
            button11.setPrefHeight(0);
            button12.setPrefHeight(0);
            button13.setPrefHeight(0);
            button14.setPrefHeight(0);
            button15.setPrefHeight(0);
            button16.setPrefHeight(0);
            button17.setPrefHeight(0);
            button18.setPrefHeight(0);
            button19.setPrefHeight(0);
            aBoolean1 = false;
        }else {
            button11.setPrefHeight(30);
            button12.setPrefHeight(30);
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


}