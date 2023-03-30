package es.bifacia.printerstudio;

import es.bifacia.printerstudio.manager.MainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PrinterStudioManagerApplication {

	@Autowired
	private MainManager mainManager = new MainManager();

	public static void main(String[] args) {

		SpringApplication.run(PrinterStudioManagerApplication.class, args);
	}

	@Bean
	public void run() throws Exception {
		mainManager.launchFullProcess();
	}

}
