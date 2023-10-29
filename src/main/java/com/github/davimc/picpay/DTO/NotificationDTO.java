package com.github.davimc.picpay.DTO;

import com.github.davimc.picpay.entities.Notification;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class NotificationDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String message;
    private String notifierName;
    private String notifiedName;

    public NotificationDTO() {
    }

    public NotificationDTO(Long id, String message, String notifierName, String notifiedName) {
        this.id = id;
        this.message = message;
        this.notifierName = notifierName;
        this.notifiedName = notifiedName;
    }

    public NotificationDTO(Notification obj) {
        id = obj.getId();
        this.message = obj.getMessage();
        this.notifierName = obj.getNotifier().getPerson().getName();
        this.notifiedName = obj.getNotified().getPerson().getName();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getNotifierName() {
        return notifierName;
    }

    public String getNotifiedName() {
        return notifiedName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationDTO that = (NotificationDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
