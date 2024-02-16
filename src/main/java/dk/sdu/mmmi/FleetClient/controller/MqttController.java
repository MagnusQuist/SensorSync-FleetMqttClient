package dk.sdu.mmmi.FleetClient.controller;

import dk.sdu.mmmi.FleetClient.entity.MqttResponse;
import dk.sdu.mmmi.FleetClient.service.MqttService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MqttController {
    private static final Logger log = LoggerFactory.getLogger(MqttController.class);
    private final MqttService mqttService;
    MqttController(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    @PostMapping("/message")
    void newDevice(@RequestBody MqttResponse requestBody) throws IOException {
        log.info("Got message with topic: " + requestBody.toString());
        byte[] message = requestBody.getMessage();
        String jsonObject = new String(message);
        mqttService.handleMessage(requestBody.getTopic(), jsonObject);
    }
}

