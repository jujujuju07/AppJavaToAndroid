package com.example.testappjavatoandroid;

import com.example.testappjavatoandroid.methode.model.Response;
import com.google.gson.Gson;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServeurTCP {
    final int port=1222;
    char [ ] bufferEntree = new char [65535];
    String messageRecu = null;
    BufferedReader fluxEntree;
    PrintStream fluxSortie;
    Socket socketDuClient;
    HelloController fxmlCont;
    ServerSocket monServerDeSocket = null;
    ArrayList<Socket> socketA = new ArrayList<Socket>();


    ServeurTCP(HelloController fxmlCont){
        this.fxmlCont = fxmlCont;
    }

    public void lancer(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    monServerDeSocket = new ServerSocket(port);
                    updateMessage("Serveur en fonctionnement",true);
                    System.out.println("Serveur en fonctionnement.");

                    while (true) {
                        socketDuClient = monServerDeSocket.accept();
                        System.out.println("Connexion avec : "+socketDuClient.getInetAddress());

                        fluxEntree = new BufferedReader(new InputStreamReader(socketDuClient.getInputStream()));
                        fluxSortie = new PrintStream(socketDuClient.getOutputStream());
                        socketA.add(socketDuClient);

                        reception();
                    }

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }

    public void reception() throws IOException {
        try{

            while (socketDuClient.isConnected()){
                System.out.println("attente...");
                Gson gson = new Gson();
                Response response = new Response();
                response.setDonner(fxmlCont.donnerList);
                response.setLine(fxmlCont.donnerApp.getLargeur());
                fluxSortie.println(gson.toJson(response));
                int NbLus = fluxEntree.read(bufferEntree);
                if (NbLus != -1){
                    messageRecu = new String(bufferEntree, 0,NbLus);
                    fxmlCont.execute(messageRecu);
                }else {
                    deconexion();
                }

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            deconexion();
        }
    }

    public void envoier(String message){
        if (message.length() != 0 )
        {
            System.out.println("\t\t Message reçu : " + message );
            fluxSortie.println(message);
            updateMessage(message,false);
            System.out.println("\t\t Message emis : "+message);
        }

    }

    public void deconexion() throws IOException {
        fluxSortie.close();
        fluxEntree.close();
        socketDuClient.close();
        socketA.clear();

    }


    protected void updateMessage(String message,Boolean serv) {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                if (serv) {
                    fxmlCont.EtatServeur.setText(message);
                }else {
                    //fxmlCont.MessageResu.setText(message);

                }

            }
        };
        if (Platform.isFxApplicationThread()) {
            // Nous sommes déjà dans le thread graphique
            command.run();
        } else {
            // Nous ne sommes pas dans le thread graphique
            // on utilise runLater.
            Platform.runLater(command);
        }

    }


}
