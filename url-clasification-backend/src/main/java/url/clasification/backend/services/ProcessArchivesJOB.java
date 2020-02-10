package url.clasification.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ProcessArchivesJOB {

    @Autowired
    private FileStorageService fileStorageService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void scheduleFixedDelayTask() {
        fileStorageService.processFiles();
    }

}
