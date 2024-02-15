package dk.sdu.mmmi.FleetClient.controller;

import dk.sdu.mmmi.FleetClient.config.Mqtt;
import dk.sdu.mmmi.FleetClient.entity.DeviceDTO;
import dk.sdu.mmmi.FleetClient.model.MqttPublishModel;
import dk.sdu.mmmi.FleetClient.model.MqttSubscribeModel;
import dk.sdu.mmmi.FleetClient.service.DeviceService;
import jakarta.validation.Valid;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/api/v1/mqtt")
public class MqttController {
    @Autowired
    private final Mqtt mqtt;
    @Autowired
    private final DeviceService deviceService;

    public MqttController(Mqtt mqtt, DeviceService deviceService) {
        this.mqtt = mqtt;
        this.deviceService = deviceService;
    }

    @PostMapping("publish")
    public void publishMessage(@RequestBody @Valid MqttPublishModel messagePublishModel,
                               BindingResult bindingResult) throws org.eclipse.paho.client.mqttv3.MqttException {
        if (bindingResult.hasErrors()) {
            throw new MqttException(1);
        }

        MqttMessage mqttMessage = new MqttMessage(messagePublishModel.getMessage().getBytes());
        mqttMessage.setQos(messagePublishModel.getQos());
        mqttMessage.setRetained(messagePublishModel.getRetained());

        mqtt.getInstance().publish(messagePublishModel.getTopic(), mqttMessage);
    }

    @GetMapping("subscribe")
    public List<MqttSubscribeModel> subscribeChannel(@RequestParam(value = "topic") String topic,
                                                     @RequestParam(value = "wait_millis") Integer waitMillis)
            throws InterruptedException, org.eclipse.paho.client.mqttv3.MqttException {
        List<MqttSubscribeModel> messages = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        mqtt.getInstance().subscribeWithResponse(topic, (s, mqttMessage) -> {
            MqttSubscribeModel mqttSubscribeModel = new MqttSubscribeModel();
            mqttSubscribeModel.setId(mqttMessage.getId());
            mqttSubscribeModel.setMessage(new String(mqttMessage.getPayload()));
            mqttSubscribeModel.setQos(mqttMessage.getQos());
            messages.add(mqttSubscribeModel);

            System.out.println("Creating new device with: " + mqttMessage);
            deviceService.createDevice(new DeviceDTO(
                    "My Device",
                    "1.0.0",
                    "0.1.0",
                    "111"
            ));

            countDownLatch.countDown();
        });

        countDownLatch.await(waitMillis, TimeUnit.MILLISECONDS);

        return messages;
    }
}
