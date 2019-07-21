package ru.muravyev.other;

import java.util.Random;

public class GenerateRandomText {

    // generate random text for String
    public String generateRandomText (){
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
