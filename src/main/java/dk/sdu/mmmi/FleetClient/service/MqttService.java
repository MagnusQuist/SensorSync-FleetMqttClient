package dk.sdu.mmmi.FleetClient.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.sdu.mmmi.FleetClient.entity.DeviceDTO;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
@Service
public class MqttService {
    private static final Logger log = LoggerFactory.getLogger(MqttService.class);
    private final MqttClient mqttClient;
    private final WebClient.Builder webClientBuilder;

    public MqttService(MqttClient mqttClient, WebClient.Builder webClientBuilder) {
        this.mqttClient = mqttClient;
        this.webClientBuilder = webClientBuilder;
    }

    public void handleMessage(String topic, String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        if ("devices/new".equals(topic)) {
            log.info("Trying to convert message to DeviceDTO...");
            DeviceDTO device = objectMapper.readValue(message, DeviceDTO.class);
            createDevice(device);
        } else if ("lifecycle/status".equals(topic)) {
            System.out.println("Got lifecycle status: " + message);
        }
    }

    public void createDevice(DeviceDTO device) {
        log.info("Sending new device to Device Manager: " + device.toString());
        webClientBuilder.build()
                .post()
                .uri("http://localhost:8080/api/v1/devices")
                .bodyValue(device)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }

    @PostConstruct
    public void subscribe() {
        try {
            mqttClient.subscribe("devices/new");
            mqttClient.subscribe("lifecycle/status");
            log.info("Subscribed to topics");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
