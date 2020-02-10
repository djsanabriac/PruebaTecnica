package score.middle.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import score.middle.dto.URLScore;
import score.middle.dto.URLScoreResponse;

public class URLScoreService {

    private static URLScoreService instance;
    private static final Logger log = LoggerFactory.getLogger(URLScoreService.class);
    private static String urlMock;

    @Autowired
    private static Environment env;

    public static URLScoreService getInstance(){

        if( instance == null ){
            instance = new URLScoreService();

            try {
                urlMock = env.getProperty("mock.url");
            }catch (Exception e){
                urlMock = "http://localhost:8081/api/mock/mock_score";
            }

        }

        return instance;

    }

    public URLScore getURLScore(String url){
        URLScore urlScore = new URLScore(url,null);

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

        return null;
    }



}