package ru.muravyev;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class Sender {
    private final static String EXCHANGE_NAME = "directExchange";
    private final static String TYPE_OF_EXCHANGE = "direct";
    private static int countOfMessages = 5000;

    public static void main(String[] args) throws IOException, TimeoutException {
        // создаем соединение
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection connection = factory.newConnection()) {
            // для отправки сообщений на другой хост, нужно дописать ip адрес нужного хоста
            factory.setHost("localhost");
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, TYPE_OF_EXCHANGE, true);

            int count = 0;
            while (count < countOfMessages){
                String message = generateRandomText();
                // send message to exchange
                channel.basicPublish(EXCHANGE_NAME, "tree", null, message.getBytes("UTF-8"));
                System.out.println("Message is published: " + count + " " + message);
                count++;
                //System.out.println("Published message " + count + ": " + message);
                try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (TimeoutException e){
            e.printStackTrace();
        }
    }

    // generate random text for String
    public static String generateRandomText (){
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
