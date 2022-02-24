package repository;

import repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pluralsight.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import entity.Application;


@SpringBootApplication
public class FundamentalsApplication {
	
	private static final Logger log = LoggerFactory.getLogger(FundamentalsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FundamentalsApplication.class, args);
		System.out.println("Hello Pluralsight!");
		
		@Bean
		public CommandLineRunner demo(ApplicationRepository repository) {
			return (args) -> {
				repository.save(new Application("Trackzilla","kesha.williams", "smoething"));
				repository.save(new Application("Expenses","mary.jones", "l"));
				repository.save(new Application("Notifications","karen.kane", "k"));
				
				for (Application application : repository.findAll()) {
					log.info("The application is: " + application.toString());
				}
			};
		}
	}

}
