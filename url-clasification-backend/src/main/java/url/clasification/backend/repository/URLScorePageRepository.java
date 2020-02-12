package url.clasification.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import url.clasification.backend.dto.URLScore;

import java.util.List;

@Repository
public interface URLScorePageRepository extends PagingAndSortingRepository<URLScore, Integer> {

    List<URLScore> findByurl(String url);

    List<URLScore> findByUrlIn(List<String> url);

}