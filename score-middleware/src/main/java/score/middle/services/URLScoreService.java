package score.middle.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import score.middle.configuration.Properties;
import score.middle.dto.GeneralResponse;
import score.middle.dto.URLScore;
import score.middle.dto.URLScoreResponse;

import javax.annotation.PostConstruct;

@Service
public class URLScoreService {

    private static URLScoreService instance;
    private static final Logger log = LoggerFactory.getLogger(URLScoreService.class);
    private static String urlMock;
    private static String urlBack;
    @Qualifier("Properties")
    @Autowired
    private Properties env;

    @PostConstruct
    public void init(){

        try {
            urlMock = env.getMockUrl();
            urlBack = env.getBackUrl();
        }catch (Exception e){
            urlMock = "http://localhost:8081/api/mock/mock_score";
            urlBack = "http://localhost:8080/api/register_url_score";
        }

        log.info("[CUSTOM-INFO] Using mock url : " + urlMock);
        log.info("[CUSTOM-INFO] Using back url : " + urlBack);

    }

    public URLScore getURLScore(String url){

        URLScore urlScore = new URLScore(url,null);

        try{
            URLScoreResponse urlScoreResponse = new RestTemplate().postForObject(
                    urlMock,
                    urlScore,
                    URLScoreResponse.class
            );

            log.info(urlScoreResponse.toString());

            if( urlScoreResponse.getSuccess() != null & urlScoreResponse.getSuccess() ){
                urlScore.setScore(urlScoreResponse.getData());
                return urlScore;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return null;
    }

    public GeneralResponse answerURLScore(URLScore urlScore){

        try{
            GeneralResponse urlScoreResponse = new RestTemplate().postForObject(
                    urlBack,
                    urlScore,
                    GeneralResponse.class
            );

            log.info(urlScoreResponse.toString());

            return urlScoreResponse;

        }catch (Exception e){
            log.error(e.getMessage());
        }

        return null;
    }

}