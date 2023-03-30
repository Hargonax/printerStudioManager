package es.bifacia.printerstudio;

import es.bifacia.printerstudio.manager.MainManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrinterStudioManagerApplication {
	public static void main(String[] args) {
		final MainManager mainManager = new MainManager();
		mainManager.launchFullProcess();
		SpringApplication.run(PrinterStudioManagerApplication.class, args);
	}

}
