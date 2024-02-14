package dk.sdu.mmmi.FleetClient;

import dk.sdu.mmmi.FleetClient.Services.MessageService;
import dk.sdu.mmmi.FleetClient.models.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class FleetClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FleetClientApplication.class, args);
	}
}
