package com.example.testappjavatoandroid.model.socketlist;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;

public class Socketlist {
    private Socket socket;
    private BufferedReader fluxEntree;
    private PrintStream fluxSortie;


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getFluxEntree() {
        return fluxEntree;
    }

    public void setFluxEntree(BufferedReader fluxEntree) {
        this.fluxEntree = fluxEntree;
    }

    public PrintStream getFluxSortie() {
        return fluxSortie;
    }

    public void setFluxSortie(PrintStream fluxSortie) {
        this.fluxSortie = fluxSortie;
    }
}
