package com.example.doctorclient.event.post;

public class BindBankPost {

    private String name;
    private String bank;
    private String bankNumber;
    private String bankSubbranch;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBank() {
        return bank == null ? "" : bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankNumber() {
        return bankNumber == null ? "" : bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankSubbranch() {
        return bankSubbranch == null ? "" : bankSubbranch;
    }

    public void setBankSubbranch(String bankSubbranch) {
        this.bankSubbranch = bankSubbranch;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\'" + name + "\'" +
                ", \"bank\":\'" + bank + "\'" +
                ", \"bankNumber\":\'" + bankNumber + "\'" +
                ", \"bankSubbranch\":\'" + bankSubbranch + "\'" +
                '}';
    }
}
