package com.github.davimc.picpay.repositories;

import com.github.davimc.picpay.DTO.NotificationDTO;
import com.github.davimc.picpay.entities.Notification;
import com.github.davimc.picpay.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
//TODO ajeitar essa query
    @Query(value="SELECT * FROM tb_notification n WHERE n.notifier_id = :userId OR n.notified_id = :userId",nativeQuery = true)
    Page<Notification> findByUser(Pageable pageable, Long userId);
}
