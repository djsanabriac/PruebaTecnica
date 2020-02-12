package url.clasification.backend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import url.clasification.backend.dto.GeneralResponse;
import url.clasification.backend.dto.GeneralResponseList;
import url.clasification.backend.dto.URLScore;
import url.clasification.backend.dto.User;
import url.clasification.backend.repository.URLScorePageRepository;
import url.clasification.backend.repository.URLScoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/url_score")
public class URLScoreController {

    private static final Logger log = LoggerFactory.getLogger(URLScoreController.class);

    @Autowired
    private URLScorePageRepository urlScorePageRepository;

    @Autowired
    private URLScoreRepository urlScoreRepository;

    @PostMapping("/register_url_score")
    public ResponseEntity registerURLScore(@RequestBody Map<String, String> body){

        String url = body.get("url");
        Double score = Double.valueOf(body.get("score"));
        URLScore urlScore = new URLScore(url, score);
        URLScore save = urlScoreRepository.save(urlScore);
        return ResponseEntity.ok(new GeneralResponse(true, "register_url_score_success"));
    }

    @GetMapping("/get_all")
    public ResponseEntity getAll(@RequestParam(required = false) Integer page,
                                 @RequestParam(required = false) Integer quantity ){

        Integer page_c = page != null ? page : 0;
        Integer quantity_c = quantity != null ? quantity : 50;

        Iterable<URLScore> all = urlScoreRepository.findAll();

        List<URLScore> allToResponse = new ArrayList<>();
        all.forEach(allToResponse::add);

        return ResponseEntity.ok( allToResponse);
    }

    @PutMapping("/set_client")
    public ResponseEntity setClient(@RequestBody Map<String, String> body){

        if( body.get("url_score_id") == null || body.get("user_id") == null){
            return ResponseEntity.badRequest().body(new GeneralResponse(false, "null_parameters"));
        }

        Integer userId = Integer.valueOf(body.get("user_id"));
        Integer urlScoreId = Integer.valueOf(body.get("url_score_id"));
        urlScoreRepository.updateUserId(urlScoreId, userId);
        return ResponseEntity.ok("");
    }

}