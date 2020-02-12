package url.clasification.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import url.clasification.backend.dto.GeneralResponse;
import url.clasification.backend.dto.PhishInfo;
import url.clasification.backend.configuration.Properties;
import url.clasification.backend.dto.URLScore;
import url.clasification.backend.repository.URLScoreRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class URLProviderService{

    private static URLProviderService instance;
    private static final Logger log = LoggerFactory.getLogger(URLProviderService.class);
    private static String urlProvider;
    private static String urlScoreMiddle;

    @Autowired
    private URLScoreRepository urlScoreRepository;
    @Qualifier("Properties")
    @Autowired
    private Properties env;

    @PostConstruct
    public void init(){
        if ( urlProvider == null ){
            try {
                urlProvider = env.getProvider();
                urlScoreMiddle = env.getScoreMiddle();
            }catch (Exception e){
                urlProvider = "https://data.phishtank.com/data/online-valid.json";
                urlScoreMiddle = "http://localhost:8082/api/url_processor/register_url";
            }
        }


        log.info("[CUSTOM-INFO] Using url provider : " + urlProvider);
        log.info("[CUSTOM-INFO] Using url score middle : " + urlScoreMiddle);

    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void getURLInfo(){



        List<PhishInfo> phishInfoList = new ArrayList<>();
        URLList listForScore;

        {
            List pi = new RestTemplate().getForObject(urlProvider, List.class);

            for (int i = 0 ; i < pi.size() - 1 ; i++ ) {

                PhishInfo phi = PhishInfo.fromMap((Map<String, String>) pi.get(i));
                phishInfoList.add(phi);

            }

            listForScore = getURLListForScore(phishInfoList);
            List<URLScore> u = urlScoreRepository.findByUrlIn(listForScore.url);
            List<String> copy = listForScore.getUrl();
            for ( URLScore us : u) {
                copy.remove(us.getUrl());
            }
            listForScore.setUrl(copy);
        }

        try{
            GeneralResponse response = new RestTemplate().postForObject(urlScoreMiddle, listForScore, GeneralResponse.class);

            log.info(response.toString());
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    private URLList getURLListForScore(List<PhishInfo> phishInfoList) {

        URLList urls = new URLList();

        for (PhishInfo pi: phishInfoList) {
            urls.addURL(pi.getURL());
        }

        return urls;

    }

    static class URLList{
        private List<String> url;

        URLList()
        {
            this.url = new ArrayList<>();
        }
        public List<String> getUrl() {
            return url;
        }
        public void setUrl(List<String> url){ this.url = url; }
        public void addURL(String url) {
            this.url.add(url);
        }
    }

}