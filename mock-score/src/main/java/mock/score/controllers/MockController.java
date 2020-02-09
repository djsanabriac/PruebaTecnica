package mock.score.controllers;

import mock.score.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/mock")
public class MockController {

    @PostMapping("/mock_score")
    public ResponseEntity search(@RequestBody Map<String, String> body){
        String url = body.get("url");
        return url != null ?
                ResponseEntity.ok(new GeneralResponse<>(true, "score_success", truncateDecimal(Math.random(), 2)).toMap()) :
                ResponseEntity.badRequest().body(new GeneralResponse<>(false, "no_url_provided_error", null))
                ;
    }

    private static BigDecimal truncateDecimal(double x, int numberofDecimals)
    {
        if ( x > 0) {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
        } else {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
        }
    }

}
