package com.lzt.kafka.demo;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 生产者实例
 * @author think
 *
 */
public class ProduceDemo {

    public static void main(String[] args) throws Exception {

        /**
         * 属性参数,设置
         * 方式1：config.setProperty("producer.type", "sync"); 可以手动设置生产者参数
         * 方式2：直接加载kafka集群中的producer.properties
         * 在producer.properties中指定broker List
         * 		metadata.broker.list=shb01:9092,129.168.79.139:9092
         */
        Properties config = new Properties();
        config.load(ProduceDemo.class.getClassLoader().getResourceAsStream("producer.properties"));
        ProducerConfig pConfig = new ProducerConfig(config);

        /**
         * 创建生产者对象
         * key：为 message的key
         * value:为message的value
         */
        Producer<String, String> produce = new Producer<String, String>(pConfig);

        /**
         * 将用户参数组装成message，
         * KeyedMessage<String, String>:key用来分区，value是消息本身；key可以不写
         */
        String topic = "test";
        List<KeyedMessage<String, String>> messageList = new ArrayList<KeyedMessage<String, String>>();
        KeyedMessage<String, String> message1 = new KeyedMessage<String, String>(topic, "key1", "value1");
        KeyedMessage<String, String> message2 = new KeyedMessage<String, String>(topic, "key2", "value2");
        messageList.add(message1);
        messageList.add(message2);

        KeyedMessage<String, String> message3 = new KeyedMessage<String, String>(topic, "value3");

        /**
         * 生产者生产消息
         */
        produce.send(messageList);
        produce.send(message3);

        //关闭
        produce.close();
    }

}