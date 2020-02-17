package com.ningmeng.consumer;

import com.rabbitmq.client.*;

public class Consumer1 {
    private static final String QUEUE = "helloworld";
    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.0.0.1");//地址
            factory.setPort(5672);
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");
            //测试
            connection = factory.newConnection();
            channel = connection.createChannel();

            DefaultConsumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws java.io.IOException {
                String exchange = envelope.getExchange();
                String routingKey = envelope.getRoutingKey();
                long deliveryTag = envelope.getDeliveryTag();
                String msg = new String(body,"utf‐8");
                System.out.println("receive message.." + msg);
                }
            };


            channel.basicConsume(QUEUE,true,consumer);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
