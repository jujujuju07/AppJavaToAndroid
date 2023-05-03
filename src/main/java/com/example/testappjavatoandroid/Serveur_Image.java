package com.example.testappjavatoandroid;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur_Image extends Thread {

    public void run()
    {
        try {
            // Création du socket serveur
            ServerSocket serverSocket = new ServerSocket(8080);

            System.out.println("Le serveur image est lancer");

            while (true) {

                // Attente d'une connexion cliente
                Socket clientSocket = serverSocket.accept();

                // Ouverture des flux d'entrée et de sortie
                OutputStream out = clientSocket.getOutputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Lecture de la requête HTTP
                String request = in.readLine();

                // Extraction du nom de fichier demandé
                String[] requestParts = request.split(" ");
                String fileName = requestParts[1].substring(1);

                // Chargement du fichier demandé
                File file = new File(fileName);
                if (file.exists()){
                    FileInputStream fileInput = new FileInputStream(file);
                    byte[] fileData = new byte[(int) file.length()];
                    fileInput.read(fileData);

                    // Envoi de la réponse HTTP
                    out.write("HTTP/1.1 200 OK\r\n".getBytes());
                    out.write(("Content-Length: " + fileData.length + "\r\n").getBytes());
                    out.write("Content-Type: image/jpeg\r\n\r\n".getBytes());
                    out.write(fileData);

                    // Fermeture des flux et du socket client
                    fileInput.close();
                }

                out.close();
                in.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
