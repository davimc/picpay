package com.github.davimc.picpay.DTO;

import com.github.davimc.picpay.entities.Transfer;

public class TransferDTO {
        Long id;
        String sender;
        String receiver;
        Double amount;

    public TransferDTO() {
    }

    public TransferDTO(Long id, String sender, String receiver, Double amount) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }
    public TransferDTO(Transfer obj) {
        this.id = obj.getId();
        this.sender = obj.getSender().getUser().getPerson().getName();
        this.receiver = obj.getReceiver().getUser().getPerson().getName();;
        this.amount = obj.getAmount();
    }

    public Long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public Double getAmount() {
        return amount;
    }
}
