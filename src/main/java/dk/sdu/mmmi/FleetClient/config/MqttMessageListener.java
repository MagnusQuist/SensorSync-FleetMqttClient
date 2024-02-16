package dk.sdu.mmmi.FleetClient.config;

import dk.sdu.mmmi.FleetClient.entity.MqttResponse;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MqttMessageListener implements MqttCallback {
    private static final Logger log = LoggerFactory.getLogger(MqttMessageListener.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public void connectionLost(Throwable throwable) {
        log.warn("Disconnected from broker...");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        MqttResponse response = new MqttResponse(topic, message.getPayload());
        webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/message")
                .bodyValue(response)
                .retrieve().bodyToMono(Void.class)
                .subscribe();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // Handle delivery complete
    }
}
