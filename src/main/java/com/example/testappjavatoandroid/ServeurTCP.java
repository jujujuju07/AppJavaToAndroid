package com.example.testappjavatoandroid;

import com.example.testappjavatoandroid.methode.model.Donner;
import com.example.testappjavatoandroid.methode.model.Response;
import com.google.gson.Gson;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;

public class ServeurTCP {
    final int port=1222;
    char [ ] bufferEntree = new char [65535];
    String messageRecu = null;
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
                        socketA.add(socketDuClient);
                        Thread thread = new Thread(){
                            @Override
                            public void run() {
                                try {
                                    reception(socketDuClient);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        };
                        thread.start();
                    }

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }

    public void reception(Socket socket) throws IOException {
        BufferedReader fluxEntree = new BufferedReader(new InputStreamReader(socketDuClient.getInputStream()));
        PrintStream fluxSortie = new PrintStream(socketDuClient.getOutputStream());
        try{
            Response response = new Response();
            ArrayList<Donner> donner = new ArrayList<>();
            for (int i = 0; i < fxmlCont.donnerListList.size(); i++) {
                for (int j = 0; j < fxmlCont.donnerListList.get(i).size(); j++) {
                    donner.add(fxmlCont.donnerListList.get(i).get(j));
                }
            }

            while (socket.isConnected()){
                System.out.println("attente...");
                Gson gson = new Gson();
                response.setDonner(donner);
                response.setLine(fxmlCont.donnerApp.getLargeur());
                fluxSortie.println(gson.toJson(response));
                int NbLus = fluxEntree.read(bufferEntree);
                if (NbLus != -1){
                    messageRecu = new String(bufferEntree, 0,NbLus);
                    fxmlCont.execute(Integer.parseInt(messageRecu));
                }else {
                    deconexion(socket,fluxEntree,fluxSortie);
                }

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            deconexion(socket,fluxEntree,fluxSortie);
        }
    }

    public void mise_a_jour_app() throws IOException {
        Response response = new Response();
        ArrayList<Donner> donner = new ArrayList<>();
        for (int i = 0; i < fxmlCont.donnerListList.size(); i++) {
            for (int j = 0; j < fxmlCont.donnerListList.get(i).size(); j++) {
                donner.add(fxmlCont.donnerListList.get(i).get(j));
            }
        }

        Gson gson = new Gson();
        response.setDonner(donner);
        response.setLine(fxmlCont.donnerApp.getLargeur());
        for (int i = 0; i < socketA.size(); i++) {
            System.out.println(socketA.size() + " " + i);
            envoier(gson.toJson(response), new PrintStream(socketA.get(i).getOutputStream()));
        }

    }

    public void envoier(String message,PrintStream fluxSortie){
        if (message.length() != 0 )
        {
            System.out.println("\t\t Message reçu : " + message );
            fluxSortie.println(message);
            updateMessage(message,false);
            System.out.println("\t\t Message emis : "+message);
        }

    }

    public void deconexion(Socket socket,BufferedReader fluxEntree,PrintStream fluxSortie) throws IOException {
        fluxSortie.close();
        fluxEntree.close();
        socket.close();
    }

    public void deconexionAll() throws IOException {
        for (int i = 0; i < socketA.size(); i++) {
            socketA.get(i).close();
        }
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

    public void serveurMulticast() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                MulticastSocket multicastSocket = null;
                try {
                    multicastSocket = new MulticastSocket();

                InetAddress multicastGroup = InetAddress.getByName("224.0.0.107");
                multicastSocket.joinGroup(multicastGroup);

                while (true) {
                    String message = InetAddress.getLocalHost().getHostAddress();
                    byte[] buffer = message.getBytes();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, multicastGroup, 4446);

                    multicastSocket.send(packet);

                    Thread.sleep(1000);
                }
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        thread.start();


    }


}
