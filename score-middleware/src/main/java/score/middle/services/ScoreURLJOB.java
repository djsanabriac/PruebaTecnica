package score.middle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScoreURLJOB {

    @Autowired
    private QueueService queueService;

    @Scheduled(cron = "0/1 * * * * ?")
    public void scheduleFixedDelayTask() {
        queueService.processQueue();
    }

}
