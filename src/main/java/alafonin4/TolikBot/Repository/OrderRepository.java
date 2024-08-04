package alafonin4.TolikBot.Repository;

import alafonin4.TolikBot.Entity.Order;
import alafonin4.TolikBot.Entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findFirstByStatusOrderByCreatedAtDesc(Status status);

    List<Order> findAllByStatus(Status unseen);
}
