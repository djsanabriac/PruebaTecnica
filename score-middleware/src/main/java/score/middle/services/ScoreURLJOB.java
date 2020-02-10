package score.middle.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
public class ScoreURLJOB {

    @Scheduled(cron = "0/5 * * * * ?")
    public void scheduleFixedDelayTask() {
        QueueService.getInstance().processQueue();
    }

}
