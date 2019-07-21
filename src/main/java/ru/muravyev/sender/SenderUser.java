package ru.muravyev.sender;

import ru.muravyev.other.GenerateRandomText;
import ru.muravyev.sender.Sender;

import java.util.Random;

public class SenderUser {
    public static void main (String [] args){
        GenerateRandomText randomText = new GenerateRandomText();
        // specify the host, exchange, typeOfExchange and routing key in the constructor
        Sender sender = new Sender("localhost", "directExchange", "direct", "tree");

        int amount = 5000;
        int count = 0;
        while (count < amount){
            sender.sendMessage(randomText.generateRandomText());
            try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
            count++;
        }

        // close connection
        sender.closeConnection();
    }

}
