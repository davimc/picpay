package com.github.davimc.picpay.entities;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_notification")
public class Notification implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne
    @JoinColumn(name = "notifier_id")
    private User notifier;
    @ManyToOne
    @JoinColumn(name = "notified_id")
    private User notified;

    private LocalDateTime createdAt;
    private boolean read;
    public Notification() {
    }

    public Notification(Long id, String message, User notifier, User notified) {
        this.id = id;
        this.message = message;
        this.notifier = notifier;
        this.notified = notified;
        read = false;
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getNotifier() {
        return notifier;
    }

    public void setNotifier(User notifier) {
        this.notifier = notifier;
    }

    public User getNotified() {
        return notified;
    }

    public void setNotified(User notified) {
        this.notified = notified;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification transaction = (Notification) o;
        return id.equals(transaction.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
