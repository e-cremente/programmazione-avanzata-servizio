package it.unipi.ServerProgetto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerProgettoApplication {
    
    private static final Logger logger = LogManager.getLogger(ServerProgettoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServerProgettoApplication.class, args);
    }

}
