package alafonin4.TolikBot.Repository;

import alafonin4.TolikBot.Entity.Category;
import alafonin4.TolikBot.Entity.Image;
import alafonin4.TolikBot.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findByUser(User user);

    List<Image> findByCategory(Category category);
}
