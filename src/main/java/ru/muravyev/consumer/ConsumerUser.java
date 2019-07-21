package ru.muravyev.consumer;

import ru.muravyev.consumer.Consumer;

public class ConsumerUser {
    public static void main(String[] args) {
        // specify the queue in the constructor
        Consumer consumer = new Consumer("queue-3");

        while (consumer.consumeMessage()!= null){
            System.out.println(consumer.consumeMessage());
        }

        // close connection
        consumer.closeConnection();
    }
}
