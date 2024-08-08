package alafonin4.TolikBot.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "ProductReservation")
@Getter
@Setter
public class ProductReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservation reservation;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "cost", nullable = true)
    private Integer cost;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "chatId", nullable = true)
    private User moderator;
}
