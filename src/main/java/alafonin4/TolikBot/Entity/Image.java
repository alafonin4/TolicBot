package alafonin4.TolikBot.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "_image")
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "chatId")
    private User user;

    @Column(name = "img", nullable = false)
    private byte[] img;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "url", nullable = true)
    private String urlToDisk;
}
