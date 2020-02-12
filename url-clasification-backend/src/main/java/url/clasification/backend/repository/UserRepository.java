package url.clasification.backend.repository;

import org.springframework.data.repository.CrudRepository;
import url.clasification.backend.dto.User;

public interface UserRepository extends CrudRepository<User, Integer>{
}