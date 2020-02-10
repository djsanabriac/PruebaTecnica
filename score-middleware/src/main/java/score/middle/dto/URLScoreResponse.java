package score.middle.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class URLScoreResponse{

    private Boolean success;
    private String message;
    private Double data;

    public URLScoreResponse(){}

    public URLScoreResponse(Boolean success,
                            String message,
                            Double data){
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getData(){ return this.data; }

    public void setData(Double data){
        this.data = data;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> toReturn = new HashMap<String, Object>();
        toReturn.put("success", true);
        toReturn.put("message", message);
        toReturn.put("data", data);
        return toReturn;
    }

    @Override
    public String toString() {
        return "URLScoreResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
