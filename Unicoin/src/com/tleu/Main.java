package com.tleu;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> transactions = new ArrayList<>();
        transactions.add("aa");
        transactions.add("bb");
        transactions.add("dd");
        transactions.add("ee");
        transactions.add("16");
        transactions.add("22");
        transactions.add("44");
        transactions.add("55");
        transactions.add("33");
        MerkleTree merkleTree = new MerkleTree(transactions);
        System.out.println(merkleTree.getMerkeRoot().get(0));

        //37cf8cc250029b6a7181eb966633830c4c120506fbe0ea32b9ed3c002300d56a
    }
}
