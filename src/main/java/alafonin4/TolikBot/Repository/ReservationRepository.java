package alafonin4.TolikBot.Repository;

import alafonin4.TolikBot.Entity.Question;
import alafonin4.TolikBot.Entity.Reservation;
import alafonin4.TolikBot.Entity.Status;
import alafonin4.TolikBot.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> getAllReservationByUser(User u);

    List<Reservation> findByUserAndStatus(User u, Status unseen);
}
