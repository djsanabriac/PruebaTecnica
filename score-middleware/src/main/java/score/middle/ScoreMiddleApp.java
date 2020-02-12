package score.middle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import score.middle.configuration.Properties;

@SpringBootApplication
@EnableConfigurationProperties(
        Properties.class
)
public class ScoreMiddleApp {

    public static void main(String[] args) {
        SpringApplication.run(ScoreMiddleApp.class, args);
    }

}
