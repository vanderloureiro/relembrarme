package dev.vanderloureiro.lembrarme.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "users_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(name = "is_email_valid", nullable = false)
    private Boolean isEmailValid = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Message> messages;

    private User() { }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    private String getEmail() {
        if (isEmailValid) {
            return this.email;
        }
        throw new RuntimeException();
    }

    public void confirmEmailValidity() {
        this.isEmailValid = true;
    }
}
