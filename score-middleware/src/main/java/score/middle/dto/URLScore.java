package score.middle.dto;

public class URLScore{

    private String url;
    private Double score;

    public URLScore(String url, Double score) {
        this.url = url;
        this.score = score;
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
}