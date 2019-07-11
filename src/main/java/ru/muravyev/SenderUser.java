package ru.muravyev;

import java.util.Random;

public class SenderUser {
    public static void main (String [] args){
        // specify the host, exchange, typeOfExchange and routing key in the constructor
        Sender sender = new Sender("localhost", "directExchange", "direct", "tree");

        int amount = 5000;
        int count = 0;
        while (count < amount){
            sender.sendMessage(generateRandomText());
            try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
            count++;
        }
    }

    // generate random text for String
    private static String generateRandomText (){
        String characters = "abcdefghijklmnopqrstyvwxyz";
        String random = "";
        Random rand = new Random();
        int length = rand.nextInt(15);
        char [] a = new char[length];
        for (int i = 0; i < length; i++){
            a[i] = characters.charAt(rand.nextInt(characters.length()));
        }
        for (int i = 0; i < a.length; i++){
            random += a[i];
        }
        return random;
    }
}
