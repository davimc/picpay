package infra.notification;

import com.github.davimc.picpay.entities.Notification;
import com.github.davimc.picpay.entities.User;

public class NotifyEmail implements Notify{
    public void sendNotification(User user, String message) {
        System.out.println(user.getEmail() + ": " + message);
    }
}
