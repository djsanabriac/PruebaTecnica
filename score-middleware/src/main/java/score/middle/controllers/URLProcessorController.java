package score.middle.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import score.middle.dto.GeneralResponse;
import score.middle.services.QueueService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/url_processor")
public class URLProcessorController {

    @PostMapping("/register_url")
    public ResponseEntity registerURL(@RequestBody Map<String, List<String>> body){

        List<String> url = body.get("url");
        QueueService.getInstance().addURLList(url);
        return ResponseEntity.ok(new GeneralResponse(true, "success", null));
    }

}