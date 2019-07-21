package ru.muravyev.sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public  class Sender {
    private String exchange_name;
    private String routing_key;
    private static Channel channel;
    private Connection connection;

    public Sender(String host, String exchange_name, String type_of_exchange, String routing_key) {
        this.exchange_name = exchange_name;
        this.routing_key = routing_key;
        ConnectionFactory factory = new ConnectionFactory();
        try {
            connection = factory.newConnection();
            factory.setHost(host);
            channel = connection.createChannel();
            channel.exchangeDeclare(exchange_name, type_of_exchange, true);
        }catch (IOException e){
            e.printStackTrace();
        }catch (TimeoutException e){
            e.printStackTrace();
        }
    }

    // send message to exchange
    public void sendMessage (String message){
        try {
            channel.basicPublish(exchange_name, routing_key, null, message.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Message is published: " + " " + message);
    }

    // close connection
    public void closeConnection () {
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
