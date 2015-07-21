package visualisationjgroups.web.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import visualisationjgroups.web.config.AppConfig;

/**
 * 
 *
 */
public class Boot {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
}
