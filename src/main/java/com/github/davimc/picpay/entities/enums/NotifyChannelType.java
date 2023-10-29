package com.github.davimc.picpay.entities.enums;

import infra.notification.Notify;
import infra.notification.NotifyApp;
import infra.notification.NotifyEmail;
import infra.notification.NotifySms;

public enum NotifyChannelType {
    EMAIL(1, "e-mail", new NotifyEmail()),
    SMS (2, "sms", new NotifySms()),
    APP(3, "app", new NotifyApp());


    private final Integer cod;
    private final String qualifier;
    private final Notify notify;

    NotifyChannelType(Integer cod, String qualifier, Notify notify) {
        this.cod = cod;
        this.qualifier = qualifier;
        this.notify = notify;
    }

    public Integer getCod() {
        return cod;
    }

    public String getQualifier() {
        return qualifier;
    }

    public Notify getNotify() {
        return notify;
    }

    public static NotifyChannelType toEnum(Integer cod) {
        if(cod == null) return null;
        for(NotifyChannelType x: values())
            if(cod.equals(x.getCod()))
                return x;
        throw new IllegalArgumentException("Tipo inválido");
    }
}
