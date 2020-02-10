package score.middle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import score.middle.dto.URLScore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueueService {

    private static QueueService instance;
    private static Map<String, URLScore> urlMap = new HashMap<>();
    private static HashMap<String, Thread> threadMap = new HashMap<>();
    private static Map<String, URLScore> urlMapToRespond = new HashMap<>();
    private static Integer maxThreads;

    @Autowired
    private static Environment env;

    public static QueueService getInstance(){

        if( instance == null ){
            instance = new QueueService();
            try {
                maxThreads = Integer.valueOf(env.getProperty("max.threads"));
            }catch (Exception e){
                maxThreads = 1000;
            }
        }

        return instance;
    }

    public static void addURL(String url){
        urlMap.put(url, new URLScore(url, null));
    }

    public static void addURLList(List<String> urlList){
        for (String url : urlList) {
            addURL(url);
        }
    }

    public static void processQueue(){
        for (String url : urlMap.keySet()) {

            URLScore urlScore = urlMap.get(url);

            if( urlScore.getScore() != null ){
                urlMapToRespond.put(url, urlScore);
                if ( threadMap.get(url).getState() == Thread.State.TERMINATED ){
                    threadMap.remove(url);
                }
                continue;
            }else if (!threadMap.containsKey(url) && threadMap.size() < maxThreads){
                Thread thread = new Thread(new MyRunnable(url));
                threadMap.put(url, thread);
                thread.start();
            }

        }

        for (String url : urlMapToRespond.keySet()) {
            urlMap.remove(url);
        }


    }

    static class MyRunnable implements Runnable {

        private String url;

        MyRunnable(String url){
            this.url = url;
        }

        @Override
        public void run() {
            URLScore urlScoreT = URLScoreService.getInstance().getURLScore(url);
            if (urlScoreT != null) {
                urlMap.put(url, urlScoreT);
            }
        }
    }
}

