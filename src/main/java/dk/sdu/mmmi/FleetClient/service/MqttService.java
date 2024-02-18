package dk.sdu.mmmi.FleetClient.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.sdu.mmmi.FleetClient.config.Topics;
import dk.sdu.mmmi.FleetClient.entity.DeviceDTO;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class MqttService {
    private static final Logger log = LoggerFactory.getLogger(MqttService.class);
    private final MqttClient mqttClient;
    private final ApiService apiService;
    private final ObjectMapper objectMapper;

    @Autowired
    public MqttService(MqttClient mqttClient, ApiService apiService, ObjectMapper objectMapper) {
        this.mqttClient = mqttClient;
        this.apiService = apiService;
        this.objectMapper = objectMapper;
    }

    public void handleMessage(String topic, String message) throws IOException {
        if (mqttClient.isConnected()) {
            if (Topics.NEW_DEVICE.equals(topic)) {
                log.info("Trying to convert message to DeviceDTO...");
                DeviceDTO device = objectMapper.readValue(message, DeviceDTO.class);
                apiService.createNewDevice(device);
            } else if ("lifecycle/status".equals(topic)) {
                System.out.println("Got lifecycle status: " + message);
            }
        } else {
            log.error("MQTT client is not connected. Cannot handle message.");
        }
    }

    @PostConstruct
    public void subscribe() {
        if (mqttClient.isConnected()) {
            try {
                mqttClient.subscribe("devices/new");
                mqttClient.subscribe("lifecycle/status");
                log.info("Subscribed to topics");
            } catch (MqttException e) {
                log.error("Error subscribing to topics", e);
            }
        } else {
            log.error("MQTT client is not connected. Cannot subscribe to topics.");
        }
    }
}
