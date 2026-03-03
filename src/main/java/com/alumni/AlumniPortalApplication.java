package com.alumni;

import com.alumni.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class AlumniPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlumniPortalApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(AuthService authService) {
		return args -> {
			authService.seedInitialAdmin();
		};
	}

	@EventListener(ApplicationReadyEvent.class)
	public void openBrowserAfterStartup() {
		try {
			String url = "http://localhost:8080/index.html";
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
			System.out.println("Opened browser to " + url);
		} catch (Exception e) {
			System.err.println("Failed to open browser automatically: " + e.getMessage());
		}
	}
}
