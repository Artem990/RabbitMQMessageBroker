package ru.muravyev;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    private static final String QUEUE_NAME = "queue-3";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            Connection connection = factory.newConnection();
            // make connection
            Channel channel = connection.createChannel();
            // specify the queue for receiving messages
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // receive all messages
            channel.basicConsume(QUEUE_NAME, true, (consumerTag, delivery) -> {
                String mes = new String (delivery.getBody(), "UTF-8");
                System.out.println("I just received the message = " + mes);
            }, consumerTag -> {});
        }catch (IOException e){
            e.printStackTrace();
        }catch (TimeoutException e){
            e.printStackTrace();
        }
    }
}
