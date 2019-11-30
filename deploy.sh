java -jar config-server/target/config-server-0.0.1-SNAPSHOT.jar > config-server.log &  
echo $! >> pid.txt
java -jar discovery-server/target/discovery-server-0.0.1-SNAPSHOT.jar > discovery-server.log &  
echo $! >> pid.txt
java -jar zipkin-server/target/zipkin-server-0.0.1-SNAPSHOT.jar > zipkin-server.log & 
echo $! >> pid.txt
echo "going to sleep for 3 minutes"
sleep 3m
echo "restoring..."
java -jar defect-api-gateway/target/defect-api-gateway-0.0.1-SNAPSHOT.jar > api-gateway.log & 
echo $! >> pid.txt
java -jar auth-service/target/auth-service-0.0.1-SNAPSHOT.jar > auth-service.log & 
echo $! >> pid.txt
java -jar defect-service/target/defect-service-0.0.1-SNAPSHOT.jar > defect-service.log & 
echo $! >> pid.txt
java -jar user-profile-service/target/user-profile-service-0.0.1-SNAPSHOT.jar > user-profile-service.log &
echo $! >> pid.txt
