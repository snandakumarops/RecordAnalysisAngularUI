package com.redhat.rest.example.demorest;

public class TransactionObj {
    String transactionReferenceNumber;
    String partitionDate;
    String originalAmount;
    String thresholdAmount;
    String message;
    String nested;
    String field52;
    String senderBIC;
    String intermediataryBank;
    String beneficiaryBank;
    String rule1;
    String rule2;

    public String getRule1() {
        return rule1;
    }

    public void setRule1(String rule1) {
        this.rule1 = rule1;
    }

    public String getRule2() {
        return rule2;
    }

    public void setRule2(String rule2) {
        this.rule2 = rule2;
    }

    public String getField52() {
        return field52;
    }

    public void setField52(String field52) {
        this.field52 = field52;
    }

    public String getSenderBIC() {
        return senderBIC;
    }

    public void setSenderBIC(String senderBIC) {
        this.senderBIC = senderBIC;
    }

    public String getIntermediataryBank() {
        return intermediataryBank;
    }

    public void setIntermediataryBank(String intermediataryBank) {
        this.intermediataryBank = intermediataryBank;
    }

    public String getBeneficiaryBank() {
        return beneficiaryBank;
    }

    public void setBeneficiaryBank(String beneficiaryBank) {
        this.beneficiaryBank = beneficiaryBank;
    }

    public String getNested() {
        return nested;
    }

    public void setNested(String nested) {
        this.nested = nested;
    }

    public String getTransactionReferenceNumber() {
        return transactionReferenceNumber;
    }

    public void setTransactionReferenceNumber(String transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }

    public String getPartitionDate() {
        return partitionDate;
    }

    public void setPartitionDate(String partitionDate) {
        this.partitionDate = partitionDate;
    }

    public String getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(String originalAmount) {
        this.originalAmount = originalAmount;
    }

    public String getThresholdAmount() {
        return thresholdAmount;
    }

    public void setThresholdAmount(String thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
