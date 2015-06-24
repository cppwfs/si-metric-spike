package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("/integration.xml")
@EnableAutoConfiguration(exclude = {JmxAutoConfiguration.class, IntegrationAutoConfiguration.class})
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }
}
