package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import springboot.filters.JWTFilter;
import springboot.filters.SimpleCorsFilter;


@SpringBootApplication(scanBasePackages={"springboot"})
public class SpringBootRestApiApp {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JWTFilter());
		registrationBean.addUrlPatterns(
				"/questionnaires", "/questionnaires/*",
				"/questions", "/questions/*",
				"/tags", "/tags/*");

		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new SimpleCorsFilter());
		registrationBean.addUrlPatterns(
				"/login", "/logout");

		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiApp.class, args);
	}
}