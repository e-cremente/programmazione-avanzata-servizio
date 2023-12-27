package it.unipi.ServizioProgetto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServizioProgettoApplication {
    
    private static final Logger logger = LogManager.getLogger(ServizioProgettoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServizioProgettoApplication.class, args);
    }

}
