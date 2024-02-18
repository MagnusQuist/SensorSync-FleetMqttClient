package dk.sdu.mmmi.FleetClient.service;

import dk.sdu.mmmi.FleetClient.config.ApiEndpoints;
import dk.sdu.mmmi.FleetClient.entity.DeviceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ApiService {
    private static final Logger log = LoggerFactory.getLogger(ApiService.class);
    private final WebClient webClient;

    @Autowired
    public ApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Send new device configuration to the device manager
     * */
    public void createNewDevice(DeviceDTO device) {
        log.info("Creating new device in Device Manager: " + device.toString());
        webClient.post()
                .uri(ApiEndpoints.CREATE_DEVICE)
                .bodyValue(device)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }
}
