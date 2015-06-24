package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("/integration.xml")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
//		ConfigurableApplicationContext ctx = new SpringApplication("/integration.xml").run(args);
//        System.out.println("Hit Enter to terminate");
//        System.in.read();
//        ctx.close();
    }

}
