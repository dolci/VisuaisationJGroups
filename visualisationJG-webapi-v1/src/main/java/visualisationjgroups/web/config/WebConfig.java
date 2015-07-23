package visualisationjgroups.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	
	/**
	 *  configuration dispatcherservlet pour les entetes CORS
	 * @return DispatcherServlet
	 */
	@Bean
	public DispatcherServlet dispatcherServlet() {
		DispatcherServlet servlet = new DispatcherServlet();
		servlet.setDispatchOptionsRequest(true);
		return servlet;
	}
}
