package com.example.testappjavatoandroid;

import com.example.testappjavatoandroid.methode.Case;
import com.example.testappjavatoandroid.methode.DonnerApp;
import com.example.testappjavatoandroid.methode.model.Donner;
import com.example.testappjavatoandroid.methode.model.ArrayListListDonner;
import com.example.testappjavatoandroid.methode.modelExecute.ResponseExecute;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    public int selectionlistcase;
    public int selectionlistlistcase;
    public ArrayList<ArrayList<Donner>> donnerListList = new ArrayList<>();
    public List<Donner> donnerList = new ArrayList<>();
    public ArrayList<ArrayList<Case>> listlistcase = new ArrayList<>();
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
        selectionlistlistcase = -1;
        selectionlistcase = -1;

        if (Files.exists(pathDonner)){
            try {
                Gson gson = new Gson();
                br = Files.newBufferedReader(pathDonner);
                String line = br.readLine();
                if (!Objects.equals(line, "")){
                    ArrayListListDonner arrayListListDonner = gson.fromJson(line, ArrayListListDonner.class);
                    donnerListList = arrayListListDonner.getArrayListDonner();
                }else {
                    for (int i = 0; i < Integer.parseInt(donnerApp.getLargeur()); i++) {
                        donnerListList.add(new ArrayList<Donner>());
                        for (int j = 0; j < Integer.parseInt(donnerApp.getLongeur()); j++) {
                            Donner donner = new Donner();
                            donner.setText("");
                            donner.setImage("http://192.168.1.17:8080/image/carre-blanc.jpg");
                            donnerListList.get(i).add(donner);
                        }
                    }
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            for (int i = 0; i < Integer.parseInt(donnerApp.getLargeur()); i++) {
                donnerListList.add(new ArrayList<Donner>());
                for (int j = 0; j < Integer.parseInt(donnerApp.getLongeur()); j++) {
                    Donner donner = new Donner();
                    donner.setText("");
                    donner.setImage("http://192.168.1.17:8080/image/carre-blanc.jpg");
                    donnerListList.get(i).add(donner);
                }
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

        for (int i = 0; i < listlistcase.size(); i++) {
            for (int j = 0; j < listlistcase.get(i).size(); j++) {
                if (!Objects.equals(donnerListList.get(i).get(j).getImage(), "")){
                    listlistcase.get(i).get(j).imageView.setImage(new Image(donnerListList.get(i).get(j).getImage()));
                }
                listlistcase.get(i).get(j).label.setText(donnerListList.get(i).get(j).getText());

            }
        }


    }

    public void fermeture(){
        Gson gson = new Gson();
        ArrayListListDonner donner = new ArrayListListDonner();
        donner.setArrayListDonner(donnerListList);
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
            bw.write(gson.toJson(donner));
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
        try {
            serveurTCP.deconexionAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        if (!donnerListList.get(selectionlistlistcase).get(selectionlistcase).getImage().equals("")){
            listlistcase.get(selectionlistlistcase).get(selectionlistcase).imageView.setImage(new Image(donnerListList.get(selectionlistlistcase).get(selectionlistcase).getImage()));
        }else {
            listlistcase.get(selectionlistlistcase).get(selectionlistcase).imageView.setImage(new Image("http://192.168.1.17:8080/image/carre-blanc.jpg"));
        }
        listlistcase.get(selectionlistlistcase).get(selectionlistcase).label.setText(donnerListList.get(selectionlistlistcase).get(selectionlistcase).getText());

        selectionlistlistcase = -1;
        selectionlistcase = -1;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("titre");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifeButton.fxml"));
        Scene scene = new Scene(loader.load());
        modifeButton = loader.getController();
        modifeButton.donner(donnerListList.get(selectionlistlistcase).get(selectionlistcase), execute.get(selectionlistcase) ,this);

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        updateButton();

    }

}
