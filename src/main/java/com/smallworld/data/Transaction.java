package com.smallworld.data;

import lombok.Data;

import java.util.Objects;

@Data
public class Transaction {
    // Represent your transaction data here.
    private Integer mtn;
    private double amount;
    private String senderFullName;
    private Integer senderAge;
    private String beneficiaryFullName;
    private Integer beneficiaryAge;
    private Integer issueId;
    private Boolean issueSolved;
    private String issueMessage;

    /**
     * To return distinct transactions based on the mtn.
     * @param o object to be compared
     * @return true in case mtn matches, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(mtn, that.mtn);
    }

    /**
     * Default implementation of hashCode, needed when overriding equals method.
     * @return default hash value against mtn.
     */
    @Override
    public int hashCode() {
        return Objects.hash(mtn);
    }
}
