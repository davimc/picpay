package com.github.davimc.picpay.notification;

import com.github.davimc.picpay.entities.User;

public class NotifyApp implements Notify{
    public void sendNotification(User user, String message) {
        System.out.println(user.getPerson().getName() + ": " + message);
    }
}
