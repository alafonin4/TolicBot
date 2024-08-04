package alafonin4.TolikBot.Repository;

import alafonin4.TolikBot.Entity.Role;
import alafonin4.TolikBot.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserName(String messageText);

    List<User> getAllByRole(Role moderator);
}
