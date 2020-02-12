package score.middle.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import score.middle.configuration.Properties;
import score.middle.dto.GeneralResponse;
import score.middle.dto.URLScore;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class QueueService {

    private static QueueService instance;
    private static Map<String, URLScore> urlMap = new HashMap<>();
    private static HashMap<String, Thread> threadMap = new HashMap<>();
    private static HashMap<String, Thread> threadResponseMap = new HashMap<>();
    private static List<String> urlListToDelete = new ArrayList<>();
    private static Integer maxThreads;

    private static final Logger log = LoggerFactory.getLogger(QueueService.class);
    @Qualifier("Properties")
    @Autowired
    private Properties env;

    @Autowired
    private URLScoreService getURLScore;

    @PostConstruct
    public void init(){
        try {
            maxThreads = env.getMaxThreads();
        }catch (Exception e){
            maxThreads = 25;
        }
        log.info("[CUSTOM-INFO] Using threads : " + maxThreads);
    }

    public void addURL(String url){
        urlMap.put(url, new URLScore(url, null));
    }

    public void addURLList(List<String> urlList){
        for (String url : urlList) {
            addURL(url);
        }
    }

    public void processQueue(){
        for (String url : urlMap.keySet()) {

            URLScore urlScore = urlMap.get(url);

            switch ( urlScore.getStatus() ){
                case NEW:
                    if ((!threadMap.containsKey(url) && threadMap.size() < maxThreads)
                        || (threadMap.containsKey(url) && threadMap.get(url).getState() == Thread.State.TERMINATED)){
                        Thread thread = new Thread(new MyRunnable(url));
                        threadMap.put(url, thread);
                        thread.start();
                    }
                    break;
                case SCORED:

                    threadMap.remove(url);

                    if ((!threadResponseMap.containsKey(url) && threadResponseMap.size() < maxThreads)
                        || (threadResponseMap.containsKey(url) && threadResponseMap.get(url).getState() == Thread.State.TERMINATED)){
                        Thread thread = new Thread(new MyRunnableResponse(url));
                        threadResponseMap.put(url, thread);
                        thread.start();
                    }

                    break;
                case SENT_TO_BACK:
                    if ( threadResponseMap.get(url).getState() == Thread.State.TERMINATED ){
                        threadResponseMap.remove(url);
                        urlListToDelete.add(url);
                    }
                    break;
                default:
                    break;
            }

        }

        for (String url : urlListToDelete) {
            urlMap.remove(url);
        }

        urlListToDelete = new ArrayList<>();


    }

    class MyRunnable implements Runnable {

        private String url;

        MyRunnable(String url){
            this.url = url;
        }

        @Override
        public void run() {
            URLScore urlScoreT = getURLScore.getURLScore(url);
            if(urlScoreT != null){
                urlScoreT.setStatus(URLScore.Status.SCORED);
                if (urlScoreT != null) {
                    urlMap.put(url, urlScoreT);
                }
            }
        }
    }

    class MyRunnableResponse implements Runnable {

        private String url;

        MyRunnableResponse(String url){
            this.url = url;
        }

        @Override
        public void run() {

            GeneralResponse response = getURLScore.answerURLScore(urlMap.get(url));

            if( response != null && response.getSuccess() ){
                urlMap.get(url).setStatus(URLScore.Status.SENT_TO_BACK);
                return;
            }

            log.error("Didnt sent [ " + url + "]");

        }
    }
}

