package ru.muravyev;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    private String queue_name;
    private static String message;
    private static Channel channel;

    public Consumer(String queue_name) {
        this.queue_name = queue_name;
        ConnectionFactory factory = new ConnectionFactory();
        try {
            // make connection
            Connection connection = factory.newConnection();
            // make chanel
            channel = connection.createChannel();
            // specify the queue for receiving messages
            channel.queueDeclare(queue_name, false, false, false, null);
        }catch (IOException e){
            e.printStackTrace();
        }catch (TimeoutException e){
            e.printStackTrace();
        }
    }

    // consume messages from queue(s)
    public String consumeMessage () {
        try {
            // receive all messages
            channel.basicConsume(queue_name, true, (consumerTag, delivery) -> {
                message = new String (delivery.getBody(), "UTF-8");
                System.out.println("I just received the message = " + message);
            }, consumerTag -> {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
