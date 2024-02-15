package dk.sdu.mmmi.FleetClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FleetClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FleetClientApplication.class, args);
	}
}
