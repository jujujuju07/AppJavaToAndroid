package com.example.testappjavatoandroid;

import com.example.testappjavatoandroid.methode.Case;
import com.example.testappjavatoandroid.methode.DonnerApp;
import com.example.testappjavatoandroid.methode.model.Donner;
import com.example.testappjavatoandroid.methode.model.Response;
import com.example.testappjavatoandroid.methode.modelExecute.ResponseExecute;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController extends Application implements Initializable{
    public Stage stage;

    public ServeurTCP serveurTCP = new ServeurTCP(this);
    public BufferedWriter bw ;
    public BufferedReader br ;
    public Path pathDonner = Paths.get("donner.txt");
    public Path pathExecute = Paths.get("execute.txt");
    private Path pathValeur = Paths.get("valeur.txt");
    public int selection;
    public List<Donner> donnerList = new ArrayList<>();
    public List<Case> caseList = new ArrayList<>();
    public List<com.example.testappjavatoandroid.methode.modelExecute.Donner> execute = new ArrayList<>();
    public ModifeButton modifeButton;
    public DonnerApp donnerApp;

    public Label EtatServeur;
    public TextField largeur;
    public TextField longeur;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void lance(){
        serveurTCP.lancer();
        selection = -1;
        if (Files.exists(pathDonner)){
            try {
                Gson gson = new Gson();
                br = Files.newBufferedReader(pathDonner);
                String line = br.readLine();
                if (!Objects.equals(line, "")){
                    Response response = gson.fromJson(line, Response.class);
                    donnerList = response.getDonner();
                }else {
                    for (int i = 0; i < (Integer.parseInt(donnerApp.getLongeur()) * Integer.parseInt(donnerApp.getLargeur())); i++) {
                        Donner donner = new Donner();
                        donner.setText("");
                        donner.setImage("");
                        donnerList.add(donner);
                    }
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            for (int i = 0; i < (Integer.parseInt(donnerApp.getLongeur()) * Integer.parseInt(donnerApp.getLargeur())); i++) {
                Donner donner = new Donner();
                donner.setText("");
                donner.setImage("");
                donnerList.add(donner);
            }

        }
        if (Files.exists(pathExecute)){
            try {
                Gson gson = new Gson();
                br = Files.newBufferedReader(pathExecute);
                String line = br.readLine();
                if (!Objects.equals(line,"")){
                    ResponseExecute responseExecute = gson.fromJson(line, ResponseExecute.class);
                    execute = responseExecute.getDonner();
                }else {
                    for (int i = 0; i < (Integer.parseInt(donnerApp.getLongeur()) * Integer.parseInt(donnerApp.getLargeur())); i++) {
                        com.example.testappjavatoandroid.methode.modelExecute.Donner donner = new com.example.testappjavatoandroid.methode.modelExecute.Donner();
                        donner.setExecute("");
                        execute.add(donner);
                    }
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            for (int i = 0; i < (Integer.parseInt(donnerApp.getLongeur()) * Integer.parseInt(donnerApp.getLargeur())); i++) {
                com.example.testappjavatoandroid.methode.modelExecute.Donner donner = new com.example.testappjavatoandroid.methode.modelExecute.Donner();
                donner.setExecute("");
                execute.add(donner);
            }

        }


        for (int i = 0; i < caseList.size(); i++) {
            if (!Objects.equals(donnerList.get(i).getImage(), "")){
                caseList.get(i).imageView.setImage(new Image(donnerList.get(i).getImage()));
            }
            caseList.get(i).label.setText(donnerList.get(i).getText());
        }

    }

    public void fermeture(){
        Gson gson = new Gson();
        Response response = new Response();
        response.setDonner(donnerList);
        response.setLine(donnerApp.getLargeur());
        ResponseExecute responseExecute = new ResponseExecute();
        responseExecute.setDonner(execute);
        if (largeur.getText() != ""){
            donnerApp.setLargeur(largeur.getText());
        }
        if (longeur.getText() != ""){
            donnerApp.setLongeur(longeur.getText());
        }
        try {
            bw = Files.newBufferedWriter(pathDonner);
            bw.write(gson.toJson(response));
            bw.close();
            bw = Files.newBufferedWriter(pathExecute);
            bw.write(gson.toJson(responseExecute));
            bw.close();
            bw = Files.newBufferedWriter(pathValeur);
            bw.write(gson.toJson(donnerApp));
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void close(){
        fermeture();
        System.exit(0);
    }

    public void execute(String messageRecu){
        try {
            Runtime r = Runtime.getRuntime();
            Process p = null;
            String s = execute.get(Integer.parseInt(messageRecu)).getExecute();
            p = r.exec(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void modif(){
        try {
            start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void updateButton(){
            if (!donnerList.get(selection).getImage().equals("")){
                caseList.get(selection).imageView.setImage(new Image(donnerList.get(selection).getImage()));
            }else {
                caseList.get(selection).imageView.setImage(new Image("http://192.168.1.17:8080/image/carre-blanc.jpg"));
            }
            caseList.get(selection).label.setText(donnerList.get(selection).getText());

            selection = -1;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("titre");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifeButton.fxml"));
        Scene scene = new Scene(loader.load());
        modifeButton = loader.getController();
        modifeButton.donner(donnerList.get(selection), execute.get(selection) ,this);

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        updateButton();

    }

    public EventHandler<? super MouseEvent> button(int i){
        selection = i;
        modif();
        return null;
    }


    public void Button11(MouseEvent mouseEvent) {
        selection = 0;
        modif();
    }
    public void Button12(MouseEvent mouseEvent) {
        selection = 1;
        modif();

    }
    public void Button13(MouseEvent mouseEvent) {
        selection = 2;
        modif();

    }
    public void Button14(MouseEvent mouseEvent) {
        selection = 3;
        modif();

    }
    public void Button21(MouseEvent mouseEvent) {
        selection = 4;
        modif();

    }
    public void Button22(MouseEvent mouseEvent) {
        selection = 5;
        modif();

    }
    public void Button23(MouseEvent mouseEvent) {
        selection = 6;
        modif();

    }
    public void Button24(MouseEvent mouseEvent) {
        selection = 7;
        modif();

    }
    public void Button31(MouseEvent mouseEvent) {
        selection = 8;
        modif();

    }
    public void Button32(MouseEvent mouseEvent) {
        selection = 9;
        modif();

    }
    public void Button33(MouseEvent mouseEvent) {
        selection = 10;
        modif();

    }
    public void Button34(MouseEvent mouseEvent) {
        selection = 11;
        modif();

    }


}
