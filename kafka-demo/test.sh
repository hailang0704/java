#show all topics
./kafka_2.11-2.1.0/bin/kafka-topics.sh --zookeeper server1:2181 --list
#show all brokers
docker exec zookeeper bin/zkCli.sh ls /brokers/ids
# produce 
./kafka_2.11-2.1.0/bin/kafka-console-producer.sh --broker-list server1:9092 --topic test
# consumer
./kafka_2.11-2.1.0/bin/kafka-console-consumer.sh --bootstrap-server server1:9092 --topic test --from-beginning
