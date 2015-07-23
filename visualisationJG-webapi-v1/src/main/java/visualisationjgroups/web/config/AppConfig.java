package visualisationjgroups.web.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import visualisationjgroups.config.DomainAndPersistenceConfig;


/**
 * 
 * @author Rabaa
 *
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = { "visualisationjgroups.web" })
@Import({ DomainAndPersistenceConfig.class, WebConfig.class })

public class AppConfig {

}


