package com.example.testappjavatoandroid;

import com.example.testappjavatoandroid.model.methode.model.Donner;
import com.example.testappjavatoandroid.model.methode.model.Response;
import com.example.testappjavatoandroid.model.socketlist.Socketlist;
import com.google.gson.Gson;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ServeurTCP {
    final int port=1222;
    char [ ] bufferEntree = new char [65535];
    String messageRecu = null;
    Socket socketDuClient;
    HelloController fxmlCont;
    ServerSocket monServerDeSocket = null;
    MulticastSocket multicastSocket = null;
    InetAddress multicastGroup = null;
    Timer timer = null;
    ArrayList<Socketlist> socketA = new ArrayList<Socketlist>();


    ServeurTCP(HelloController fxmlCont){
        this.fxmlCont = fxmlCont;
    }

    public void lancer(){
        new Thread(() -> {
            try {
                monServerDeSocket = new ServerSocket(port);
                updateMessage("Serveur en fonctionnement",true);
                System.out.println("Serveur en fonctionnement.");

                while (true) {
                    socketDuClient = monServerDeSocket.accept();
                    System.out.println("Connexion avec : "+socketDuClient.getInetAddress());
                    BufferedReader fluxEntree = new BufferedReader(new InputStreamReader(socketDuClient.getInputStream()));
                    PrintStream fluxSortie = new PrintStream(socketDuClient.getOutputStream());
                    Socketlist socketlist = new Socketlist();
                    socketlist.setSocket(socketDuClient);
                    socketlist.setFluxEntree(fluxEntree);
                    socketlist.setFluxSortie(fluxSortie);
                    socketA.add(socketlist);
                    Thread thread = new Thread(() -> {
                        try {
                            reception(socketlist);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    thread.start();
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }

        }).start();
    }

    public void reception(Socketlist socketlist) throws IOException {
        try{
            Response response = new Response();
            ArrayList<Donner> donner = new ArrayList<>();
            for (int i = 0; i < fxmlCont.donnerListList.size(); i++) {
                for (int j = 0; j < fxmlCont.donnerListList.get(i).size(); j++) {
                    donner.add(fxmlCont.donnerListList.get(i).get(j));
                }
            }

            while (socketlist.getSocket().isConnected()){
                System.out.println("attente...");
                Gson gson = new Gson();
                response.setDonner(donner);
                response.setLine(fxmlCont.donnerApp.getLargeur());
                socketlist.getFluxSortie().println(gson.toJson(response));
                int NbLus = socketlist.getFluxEntree().read(bufferEntree);
                if (NbLus != -1){
                    messageRecu = new String(bufferEntree, 0,NbLus);
                    fxmlCont.execute(Integer.parseInt(messageRecu));
                }else {
                    deconexion(socketlist);
                    socketA.remove(socketlist);
                }

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            deconexion(socketlist);
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
            envoier(gson.toJson(response), socketA.get(i).getFluxSortie());
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

    public void deconexion(Socketlist socketlist) throws IOException {
        socketlist.getFluxSortie().close();
        socketlist.getFluxEntree().close();
        socketlist.getSocket().close();
    }

    public void deconexionAll() throws IOException, InterruptedException {
        timer.cancel();
        multicastSocket.leaveGroup(multicastGroup);
        Thread.sleep(1000);
        for (int i = 0; i < socketA.size();) {
            System.out.println(i);
            if (socketA.get(i).getSocket().isConnected()){
                socketA.get(i).getFluxSortie().println("exit");
                //Thread.sleep(1000);
/*
                if (socketA.get(i).getSocket().isConnected()){
                    deconexion(socketA.get(i));
                }
*/
            }
            socketA.remove(socketA.get(i));
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

    public void serveurMulticast() throws IOException {
        multicastSocket = new MulticastSocket();
        multicastGroup = InetAddress.getByName("224.0.0.107");
        multicastSocket.joinGroup(multicastGroup);

        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    String message = InetAddress.getLocalHost().getHostAddress();
                    byte[] buffer = message.getBytes();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, multicastGroup, 4446);

                    multicastSocket.send(packet);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask,0,1000);





    }


}
