package alafonin4.TolikBot.Repository;

import alafonin4.TolikBot.Entity.Order;
import alafonin4.TolikBot.Entity.Product;
import alafonin4.TolikBot.Entity.Question;
import alafonin4.TolikBot.Entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    Optional<Question> findFirstByStatusOrderByCreatedAtDesc(Status status);

    List<Question> findAllByStatus(Status unseen);
}
