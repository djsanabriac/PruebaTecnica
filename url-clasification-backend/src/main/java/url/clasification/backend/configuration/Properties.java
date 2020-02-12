package url.clasification.backend.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("Properties")
@ConfigurationProperties(prefix= "url")
public class Properties {

    private String provider;
    private String scoreMiddle;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getScoreMiddle() {
        return scoreMiddle;
    }

    public void setScoreMiddle(String scoreMiddle) {
        this.scoreMiddle = scoreMiddle;
    }
}
