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
            // создаем новое соединение
            Channel channel = connection.createChannel();
            // указываем очередь из которой будем получать сообщения
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // получаем все
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
