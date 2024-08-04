package alafonin4.TolikBot.Repository;

import alafonin4.TolikBot.Entity.Review;
import alafonin4.TolikBot.Entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    Optional<Review> findFirstByStatusOrderByCreatedAtDesc(Status status);

    List<Review> findAllByStatus(Status unseen);
}
