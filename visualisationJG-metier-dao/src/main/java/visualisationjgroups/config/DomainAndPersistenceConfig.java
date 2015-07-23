package visualisationjgroups.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;




@EnableJpaRepositories(basePackages = { "visualisationjgroups.repositories"})
@EnableAutoConfiguration
@ComponentScan(basePackages = { "visualisationjgroups" })
@EntityScan(basePackages = { "visualisationjgroups.entities" })
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })

public class DomainAndPersistenceConfig {

	/*private final String driver ;
	private final String url;
	private final String userName ;
	private final String password;*/
	
    @Autowired
    Environment env;

	
	// source data MySQL
	
	 @Bean
	    static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
	        return new PropertySourcesPlaceholderConfigurer();}
	@Bean
	public DataSource dataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
		
		/*dataSource.setDriverClassName(env.getProperty("driver-class-name"));
		dataSource.setUrl(env.getProperty("url"));
		dataSource.setUsername(env.getProperty("username"));
		dataSource.setPassword(env.getProperty("password"));
		
		*/dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/dbvisualisationjgroups");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}

	// le provider JPA - n'est pas nécessaire si on est satisfait des valeurs par
	// défaut utilisées par Spring boot
	// ici on le définit pour activer / désactiver les logs SQL
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(false);
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}

	
}
