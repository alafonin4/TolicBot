package alafonin4.TolikBot.Repository;

import alafonin4.TolikBot.Entity.Image;
import alafonin4.TolikBot.Entity.Order;
import alafonin4.TolikBot.Entity.OrderImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderImageRepository extends CrudRepository<OrderImage, Long> {

    List<OrderImage> findByOrder(Order order);
}
