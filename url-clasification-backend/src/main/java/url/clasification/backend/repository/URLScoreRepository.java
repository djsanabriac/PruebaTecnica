package url.clasification.backend.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import url.clasification.backend.dto.URLScore;

import javax.transaction.Transactional;
import java.util.List;

public interface URLScoreRepository extends CrudRepository<URLScore, Integer> {

    List<URLScore> findByurl(String url);

    List<URLScore> findByUrlIn(List<String> url);

    @Modifying
    @Transactional
    @Query("update URLScore u set u.user.id = ?2 where u.id = ?1")
    public int updateUserId(Integer urlScoreId, Integer userId);

}