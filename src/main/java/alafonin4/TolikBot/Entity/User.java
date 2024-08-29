package alafonin4.TolikBot.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity(name = "_user")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "chatId", nullable = false)
    private Long chatId;

    @Column(name = "firstName", nullable = false)
    private String name;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "stage", nullable = true)
    private Stage stageOfUsing;

    @Column(name = "number_of_invited_users", nullable = false)
    private Integer numberOfInvitedUsers;
}
