package com.example.testappjavatoandroid;

import com.example.testappjavatoandroid.button.ButtonList;
import com.example.testappjavatoandroid.methode.model.Donner;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Modifier_Button implements Initializable {
    public Button volume;
    public Button volumePlus;
    public Button volumeMoins;
    public boolean aBoolean1 = true;
    public boolean aBoolean2 = true;
    public TextField textFieldText;
    public TextField textFieldImage;
    public Button buttonSave;
    public Label labelInfo;
    public VBox vBoxM;
    public Button execute;
    public Button lancerAPP;
    public Button lancerSon;
    public ImageView buttonVolume;
    public ImageView buttonExecute;
    private Donner donner;
    private List<ButtonList> buttonLists;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("image/fleche_menu_déroulante.png");
        buttonVolume.setImage(image);
        buttonExecute.setImage(image);
    }

    public void volume(ActionEvent actionEvent) {
        if (aBoolean1){
            volumePlus.setPrefHeight(0);
            volumeMoins.setPrefHeight(0);
            buttonVolume.setRotate(180);
            aBoolean1 = false;
        }else {
            volumePlus.setPrefHeight(30);
            volumeMoins.setPrefHeight(30);
            buttonVolume.setRotate(0);
            aBoolean1 = true;
        }

    }

    public void execute(ActionEvent actionEvent) {
        if (aBoolean2){
            lancerAPP.setPrefHeight(0);
            lancerSon.setPrefHeight(0);
            buttonExecute.setRotate(180);
            aBoolean2 = false;
        }else {
            lancerAPP.setPrefHeight(30);
            lancerSon.setPrefHeight(30);
            buttonExecute.setRotate(0);
            aBoolean2 = true;
        }
    }

    public void donner(Donner donner, List<ButtonList> buttonLists){
        this.donner = donner;
        this.buttonLists = buttonLists;
        mettreDonner();
    }

    private void mettreDonner(){
        textFieldText.setText(donner.getText());
        textFieldImage.setText(donner.getImage());

        for (int i = 0; i < buttonLists.size(); i++) {
            List_Button listButton = new List_Button();

            switch (buttonLists.get(i).getButtonType()){
                case "volumePlus":
                    vBoxM.getChildren().add(listButton.buttonVolumePlus(vBoxM,buttonLists.get(i).getButtonDonner()));
                    break;
                case "VolumeMoins":
                    vBoxM.getChildren().add(listButton.buttonVolumeMoins(vBoxM,buttonLists.get(i).getButtonDonner()));
                    break;
                case "lancerSon":
                    vBoxM.getChildren().add(listButton.buttonLancerSon(vBoxM,buttonLists.get(i).getButtonDonner()));
                    break;
                case "lancerAPP":
                    vBoxM.getChildren().add(listButton.buttonLancerAPP(vBoxM,buttonLists.get(i).getButtonDonner()));
                    break;
                default:
            }

        }
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
        buttonLists.clear();
        System.out.println(vBoxM.getChildren().size());
        for (int i = 0; i < vBoxM.getChildren().size(); i++) {
            HBox hBox = (HBox) vBoxM.getChildren().get(i);
            TextField textField = (TextField) hBox.getChildren().get(1);

            ButtonList buttonList = new ButtonList();
            buttonList.setButtonType(hBox.getId());
            buttonList.setButtonDonner(textField.getText());

            buttonLists.add(buttonList);

        }

    }
}