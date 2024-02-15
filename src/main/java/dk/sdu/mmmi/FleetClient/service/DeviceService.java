package dk.sdu.mmmi.FleetClient.service;

import dk.sdu.mmmi.FleetClient.controller.MqttController;
import dk.sdu.mmmi.FleetClient.entity.DeviceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DeviceService {
    private final WebClient webClient;

    @Autowired
    public DeviceService(
            @Value("${device.manager.api.url}") String baseURL,
            @Value("${device.manager.api.port}") String port,
            @Value("${device.manager.api.version}") String version
    ) {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/api/v1")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<DeviceDTO> createDevice(DeviceDTO deviceRequest) {
        System.out.println("Trying to create device");
        System.out.println(deviceRequest.toString());
        return webClient.post()
                .uri("/devices")
                .body(Mono.just(deviceRequest), DeviceDTO.class)
                .retrieve()
                .bodyToMono(DeviceDTO.class);
    }
}
