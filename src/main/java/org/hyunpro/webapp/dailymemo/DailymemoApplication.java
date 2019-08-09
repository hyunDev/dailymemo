package org.hyunpro.webapp.dailymemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@EntityScan(basePackageClasses = {Jsr310Converters.class},
			basePackages = {"org.hyunpro.webapp.dailymemo"})
@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class DailymemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailymemoApplication.class, args);
	}

}
