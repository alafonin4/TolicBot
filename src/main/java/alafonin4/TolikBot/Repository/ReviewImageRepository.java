package alafonin4.TolikBot.Repository;

import alafonin4.TolikBot.Entity.Review;
import alafonin4.TolikBot.Entity.ReviewImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewImageRepository extends CrudRepository<ReviewImage, Long> {

    List<ReviewImage> findByReview(Review review);
}
