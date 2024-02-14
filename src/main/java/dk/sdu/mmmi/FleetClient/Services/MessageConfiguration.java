package dk.sdu.mmmi.FleetClient.Services;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "athena.broker")
    public MqttConnectOptions mqttConnectOptions(
            @Value("${athena.broker.user}") String user,
            @Value("${athena.broker.password}") String password
    ) {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(user);
        options.setPassword(password.toCharArray());
        options.setConnectionTimeout(60);
        options.setKeepAliveInterval(60);
        return options;
    }

    @Bean
    public IMqttClient mqttClient(
            @Value("${athena.broker.clientId}") String clientId,
            @Value("${athena.broker.url}") String hostname,
            @Value("${athena.broker.port}") int port) throws MqttException
    {
        IMqttClient mqttClient = new MqttClient("tcp://" + hostname + ":" + port, clientId);

        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("Connection Lost: " + cause.getMessage());
            }

            @Override

            public void messageArrived(String s, MqttMessage message) {
                System.out.println("Qos: " + message.getQos());
                System.out.println("message content: " + new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("deliveryComplete---------" + token.isComplete());
            }
        });

        mqttClient.connect(mqttConnectOptions("admin", "password"));
        if (mqttClient.isConnected()) {
            System.out.println("Connected to broker");
        }
        mqttClient.subscribe("lifecycle/status");

        return mqttClient;
    }
}
