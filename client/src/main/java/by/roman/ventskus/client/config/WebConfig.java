package by.roman.ventskus.client.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Roman Ventskus on 06.12.2015.
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig extends WebMvcAutoConfiguration {
}
