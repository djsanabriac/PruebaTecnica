package url.clasification.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import url.clasification.backend.configuration.Properties;

@SpringBootApplication
@EnableConfigurationProperties(
        Properties.class
)
public class URLClasificationApp {

    public static void main(String[] args) {
        SpringApplication.run(URLClasificationApp.class, args);
    }

}
