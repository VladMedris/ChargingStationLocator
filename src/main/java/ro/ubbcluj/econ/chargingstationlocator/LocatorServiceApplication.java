package ro.ubbcluj.econ.chargingstationlocator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class LocatorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LocatorServiceApplication.class, args);}
}