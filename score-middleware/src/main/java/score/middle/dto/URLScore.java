package score.middle.dto;

public class URLScore{

    public enum Status {
        NEW,
        SCORED,
        SENT_TO_BACK
    };

    private String url;
    private Double score;
    private Status status;

    public URLScore(String url, Double score) {
        this.url = url;
        this.score = score;
        this.status = Status.NEW;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}