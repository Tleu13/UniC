package com.tleu;

import java.security.PublicKey;

public class TransactionOutput {

    private String id;

    private String parentTransactionId;

    private PublicKey receiver;

    private double amount;

    public TransactionOutput(PublicKey receiver, double amount, String parentTransactionId) {
        this.receiver = receiver;
        this.amount = amount;
        this.parentTransactionId = parentTransactionId;
        generateId();
    }

    private void generateId(){
        this.id = SHA256Helper.generateHash(receiver.toString()+
                Double.toString(amount)+parentTransactionId);
    }

    public boolean isMine(PublicKey publicKey) {
        return  publicKey == receiver;
    }

    public String getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(String parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }

    public PublicKey getReceiver() {
        return receiver;
    }

    public void setReceiver(PublicKey receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }
}
