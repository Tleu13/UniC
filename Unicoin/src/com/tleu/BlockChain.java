package com.tleu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockChain {

    private List<Block> blockChain;
    public static Map<String,TransactionOutput> UTXOs;

    public BlockChain() {
        BlockChain.UTXOs = new HashMap<String,TransactionOutput>();
        this.blockChain = new ArrayList<>();
    }


    public void addBlock(Block block) {
        this.blockChain.add(block);
    }

    public List<Block> getBlockChain() {
        return blockChain;
    }

    public int size() {
        return this.blockChain.size();
    }

    @Override
    public String toString() {
        String blockChain = "";
        for (Block block : this.blockChain) {
            blockChain+=block.toString()+"\n";
        }
        return blockChain;
    }
}
