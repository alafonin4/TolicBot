package alafonin4.TolikBot.Repository;

import alafonin4.TolikBot.Entity.Order;
import alafonin4.TolikBot.Entity.Product;
import alafonin4.TolikBot.Entity.ProductReservation;
import alafonin4.TolikBot.Entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReservationRepository extends CrudRepository<ProductReservation, Long> {
    List<ProductReservation> findByReservation(Reservation res);
}
