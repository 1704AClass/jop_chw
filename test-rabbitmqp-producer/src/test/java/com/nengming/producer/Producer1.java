package com.nengming.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Queue;

public class Producer1 {
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

        connection = factory.newConnection();
        channel = connection.createChannel();

    /**            
      * 声明队列，如果Rabbit中没有此队列将自动创建             * param1:队列名称             * param2:是否持久化             * param3:队列是否独占此连接             * param4:队列不再使用时是否自动删除此队列             * param5:队列参数          
    */
         channel.queueDeclare(QUEUE ,true,false,false,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
