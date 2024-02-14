package dk.sdu.mmmi.FleetClient.controllers;

import dk.sdu.mmmi.FleetClient.Services.MessageService;
import dk.sdu.mmmi.FleetClient.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/message")
public class MessageController {


    private final MessageService messageClient;

    @Autowired
    public MessageController(MessageService messageClient) {
        this.messageClient = messageClient;
    }

    @GetMapping
    public List<Message> getMessages() {
        return messageClient.getMessages();
    }
}
