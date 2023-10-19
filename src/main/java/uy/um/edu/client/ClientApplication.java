package uy.um.edu.client;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClientApplication {


	private static ConfigurableApplicationContext context;
	public static void main(String[] args) {
		ClientApplication.context = SpringApplication.run(ClientApplication.class, args);


		Application.launch(uy.um.edu.client.ui.JavaFXApplication.class, args);



	}

	public static ConfigurableApplicationContext getContext() {
		return context;
	}
}
