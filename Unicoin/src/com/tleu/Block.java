package com.tleu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Block {

    private int id;
    private int nonce;
    private long timestamp;
    private String hash;
    private String previousHash;
    private List<Transaction> transactions;



    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.transactions = new ArrayList<>();
        generateHash();
    }

    public void generateHash() {
        String dataToHash = Integer.toString(id)+previousHash+Long.toString(timestamp)
                +Integer.toString(nonce)+transactions.toString();
        String hashValue = SHA256Helper.generateHash(dataToHash);
        this.hash = hashValue;
    }

    public boolean addTransaction(Transaction transaction) {

        if(transaction == null) return false;

        if((!previousHash.equals(Constants.GENESIS_PREV_HASH))) {
            if((!transaction.verifyTransaction())) {
                System.out.println("Transaction is not valid...");
                return false;
            }
        }

        transactions.add(transaction);
        System.out.println("Transaction is valid and it's " +
                "added to the block "+this);
        return true;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public void incrementNonce() {
        this.nonce++;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", previousHash='" + previousHash + '\'' +
                ", transaction='" + transactions + '\'' +
                '}';
    }
}
