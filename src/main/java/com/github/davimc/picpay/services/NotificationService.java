package com.github.davimc.picpay.services;

import com.github.davimc.picpay.DTO.NotificationDTO;
import com.github.davimc.picpay.entities.Notification;
import com.github.davimc.picpay.entities.User;
import com.github.davimc.picpay.repositories.NotificationRepository;
import com.github.davimc.picpay.services.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {
    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }
    @Transactional(readOnly = true)
    protected Notification find (Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Notification.class));
    }
    @Transactional(readOnly = true)
    public NotificationDTO findById (Long id) {
        Notification user = find(id);
        return new NotificationDTO(user);
    }
    @Transactional(readOnly = true)
    public Page<NotificationDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(NotificationDTO::new);
    }

    public void notify(User notifier, User notified, String message) {
        Notification obj = new Notification(null, message, notifier, notified);
        obj = repository.save(obj);
        for(var x: notifier.getChannels()) {
            x.getNotify().sendNotification(notifier, message);
        }
        for(var x: notified.getChannels()) {
            x.getNotify().sendNotification(notified, message);
        }
    }
    /*@Transactional
    public NotificationDTO insert(Double amount, Wallet notifier, Wallet notified) {
        Notification obj = new Notification(null, amount, notifier, notified);
        obj = repository.save(obj);

        return new NotificationDTO(user);
    }*/
}
