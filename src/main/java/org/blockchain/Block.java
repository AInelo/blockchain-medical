package org.blockchain;

//public class Block {
//}
import java.util.Date;
import java.util.List;

public class Block {
    String previousHash;
    private String hash;
    private List<Transaction> transactions;
    private long timeStamp;

    public Block(String previousHash, List<Transaction> transactions) {
        this.previousHash = previousHash;
        this.transactions = transactions;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String dataToHash = previousHash + Long.toString(timeStamp) + transactions.toString();
        return Integer.toHexString(dataToHash.hashCode());
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }
}
