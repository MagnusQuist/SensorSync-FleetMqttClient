package dk.sdu.mmmi.FleetClient.config;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Mqtt {
    private static IMqttClient instance;
    @Value("${athena.broker.url}")
    private String url;
    @Value("${athena.broker.port}")
    private String port;
    @Value("${athena.broker.clientId}")
    private String clientId;
    @Value("${athena.broker.password}")
    private String password;
    @Value("${athena.broker.user}")
    private String user;

    public IMqttClient getInstance() {
        try {
            if (instance == null) {
                instance = new MqttClient("tcp://" + url + ":" +  port, clientId);
            }

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            options.setPassword(password.toCharArray());
            options.setUserName(user);

            if (!instance.isConnected()) {
                instance.connect(options);
                System.out.println("Connected to broker");
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }

        return instance;
    }

    private Mqtt() {}
}
