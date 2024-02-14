package dk.sdu.mmmi.FleetClient.Services;

import dk.sdu.mmmi.FleetClient.models.Message;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private IMqttClient mqttClient;

    public List<Message> getMessages() {
        return List.of(
                new Message(
                        "336f9f06-ac77-4743-8830-18de612be652",
                        "1.10.1",
                        "0.0.1",
                        "nu"
                )
        );
    }
}
