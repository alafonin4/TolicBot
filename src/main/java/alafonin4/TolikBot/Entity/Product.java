package alafonin4.TolikBot.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "_product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "nameOfProject", nullable = false)
    private String nameOfProject;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Stat stat;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "shop", nullable = false)
    private String shop;

    @Column(name = "url")
    private String url;

    @Column(name = "count", nullable = false)
    private Integer countAvailable;

    @Column(name = "blockAll", nullable = true)
    private Boolean needBlockAll;
}
