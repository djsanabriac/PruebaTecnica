package url.clasification.backend.dto;

import javax.persistence.*;

@Entity
public class URLScore {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Lob
    private String url;
    private Double score;

    @ManyToOne
    private User user;

    public URLScore(){};

    public URLScore(String url, Double score) {
        this.url = url;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
