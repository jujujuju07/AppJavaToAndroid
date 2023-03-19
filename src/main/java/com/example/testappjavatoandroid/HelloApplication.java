package com.example.testappjavatoandroid;

import com.example.testappjavatoandroid.methode.Case;
import com.example.testappjavatoandroid.methode.DonnerApp;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class HelloApplication extends Application {
    private Path pathValeur = Paths.get("valeur.txt");
    private BufferedReader br ;
    private DonnerApp donnerApp;

    @Override
    public void start(Stage stage) throws IOException {
        lire();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Parent root = fxmlLoader.load();
        HelloController helloController = fxmlLoader.getController();
        helloController.donnerApp = donnerApp;
        helloController.longeur.setText(donnerApp.getLongeur());
        helloController.largeur.setText(donnerApp.getLargeur());
        VBox vBox = (VBox) root.lookup("#liste_button");
        boolean nouvauxlistCase = false;
        for (int i = 0; i <Integer.parseInt(donnerApp.getLargeur()); i++) {
            HBox hBox = new HBox();
            for (int j = 0; j <Integer.parseInt(donnerApp.getLongeur()); j++) {
                StackPane stackPane = new StackPane();
                ImageView imageView = new ImageView();
                Label label = new Label();

                imageView.setImage(new Image("http://192.168.1.17:8080/image/carre-blanc.jpg"));
                imageView.setFitHeight(150);
                imageView.setFitWidth(150);
                imageView.setId("ImageView "+ i + " " + j);
                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String[] t = imageView.getId().split(" ");
                        helloController.selectionlistlistcase = Integer.parseInt(t[1]);
                        helloController.selectionlistcase = Integer.parseInt(t[2]);
                        helloController.modif();
                        System.out.println("salut");
                    }
                });



                label.setId("LabelView"+i+j);
                if (!nouvauxlistCase){
                    helloController.listlistcase.add(new ArrayList<Case>());
                }
                helloController.listlistcase.get(i).add(new Case(imageView,label));

                stackPane.getChildren().add(imageView);
                stackPane.getChildren().add(label);
                hBox.getChildren().add(stackPane);

            }
            nouvauxlistCase = true;
            vBox.getChildren().add(hBox);
        }
        helloController.lance();




        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> helloController.close());
        stage.show();
    }

    public void lire(){
        if (Files.exists(pathValeur)){
            try {
                Gson gson = new Gson();
                br = Files.newBufferedReader(pathValeur);
                String line = br.readLine();
                donnerApp = gson.fromJson(line, DonnerApp.class);
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            DonnerApp donnerApp1 = new DonnerApp();
            donnerApp1.setLargeur("5");
            donnerApp1.setLongeur("5");

            donnerApp = donnerApp1;

        }

    }

    public static void main(String[] args) {
        launch();
    }
}