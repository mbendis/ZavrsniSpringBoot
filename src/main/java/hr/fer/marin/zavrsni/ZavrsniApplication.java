package hr.fer.marin.zavrsni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ZavrsniApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZavrsniApplication.class, args);
    }

}
