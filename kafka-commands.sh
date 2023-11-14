KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"

bin/kafka-storage.sh format -t $KAFKA_CLUSTER_ID -c config/kraft/server.properties

# start kafka server
bin/kafka-server-start.sh config/kraft/server.properties

# start kafka producer
bin/kafka-console-producer.sh --topic order.created --bootstrap-server localhost:9092

