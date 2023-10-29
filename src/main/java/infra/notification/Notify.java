package infra.notification;

import com.github.davimc.picpay.entities.Notification;
import com.github.davimc.picpay.entities.User;

public interface Notify {
    void sendNotification(User user, String message);
}
