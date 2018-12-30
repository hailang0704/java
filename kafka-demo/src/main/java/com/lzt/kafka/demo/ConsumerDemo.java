package com.lzt.kafka.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;


public class ConsumerDemo {

    public static void main(String[] args) throws Exception {
        String topic = "test";
        /**
         * 属性参数
         */
        Properties prop = new Properties();
        prop.load(Consumer.class.getClassLoader().getResourceAsStream("consumer.properties"));
        ConsumerConfig conf = new ConsumerConfig(prop);

        //创建消费者
        ConsumerConnector createJavaConsumerConnector = Consumer.createJavaConsumerConnector(conf);

        //执行消费
        //topicCountMap:key是topic名称，value是该topic所使用的消费者的个数
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, 2);//使用2个消费者消费topic-hello
        /**
         * messageStreams:
         * 		key是消费的topic的名称。
         * 		value是消费者读取 kafka流
         */
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = createJavaConsumerConnector.createMessageStreams(topicCountMap);
        //list就是消费者读取的kafka流，有几个消费者就有几个流，此处kafka流的数量为2
        List<KafkaStream<byte[], byte[]>> list = messageStreams.get(topic);

        //启动多线程来分别读取
        //KafkaStream<byte[], byte[]>，key是所消费的消息的key，value是所消费的消息value
        for (KafkaStream<byte[], byte[]> kafkaStream : list) {
            new Thread(new Worker(kafkaStream)).start();
            ;
        }
    }

    /**
     * 内部类，用线程接收kafka流
     *
     * @author think
     */
    static class Worker implements Runnable {

        private KafkaStream<byte[], byte[]> kafkaStream;

        public Worker(KafkaStream<byte[], byte[]> kafkaStream) {
            this.kafkaStream = kafkaStream;
        }

        public void run() {
            //使用kafkaStream的迭代器，迭代消费数据
            ConsumerIterator<byte[], byte[]> iterator = kafkaStream.iterator();
            while (iterator.hasNext()) {
                //MessageAndMetadata就是消息本身
                MessageAndMetadata<byte[], byte[]> next = iterator.next();
                System.out.println(String.format("key:%s  value:%s partition:%s  offset:%s",
                        next.key() == null ? "" : new String(next.key()),
                        new String(next.message()),
                        next.partition(),
                        next.offset()));
            }
        }

    }
}