package mock.score.dto;

import java.util.HashMap;
import java.util.Map;

public class GeneralResponse<T>{

    private Boolean success;
    private String message;
    private T data;

    public GeneralResponse(){}

    public GeneralResponse(Boolean success,
                    String message,
                    T data){
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

    public Map<String, Object> toMap(){
        HashMap<String, Object> toReturn = new HashMap<String, Object>();
        toReturn.put("success", true);
        toReturn.put("message", message);
        toReturn.put("data", data);
        return toReturn;
    }
}
