package katanskiy.DemoTemplate.Repositories;

import katanskiy.DemoTemplate.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByLogin(String login);
    boolean existsByLogin(String login);

}
