package alafonin4.TolikBot.Repository;

import alafonin4.TolikBot.Entity.Answer;
import alafonin4.TolikBot.Entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
