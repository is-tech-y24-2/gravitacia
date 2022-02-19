package main.java.com.company.TransactionService;

import main.java.com.company.AccountType.AccountReference;
import main.java.com.company.BankInfoBuilder.BankInformation;

public class BankTransactions {

    public BankTransactions(AccountReference acc, double amount, BankInformation bi)
    {
        Id = Integer.toString(bi.getAvailibleTransactionId());
        Amount = amount;
        FirstAcc = acc;
        IsCanceled = false;
    }

    public BankTransactions(AccountReference facc, AccountReference sacc, double amount, BankInformation bi)
    {
        Id = Integer.toString(bi.getAvailibleTransactionId());
        Amount = amount;
        FirstAcc = facc;
        SecAcc = sacc;
        IsCanceled = false;
    }

    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    private ITransaction Type; // "RNT" - Replenishment "WWL" - Withdrawal "TFR" - Transfer

    public ITransaction getType() {
        return Type;
    }

    public void setType(ITransaction type) {
        Type = type;
    }

    private AccountReference FirstAcc;

    public AccountReference getFirstAcc() {
        return FirstAcc;
    }

    public void setFirstAcc(AccountReference firstAcc) {
        FirstAcc = firstAcc;
    }

    private AccountReference SecAcc;

    public AccountReference getSecAcc() {
        return SecAcc;
    }

    public void setSecAcc(AccountReference secAcc) {
        SecAcc = secAcc;
    }

    private double Amount;

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    private boolean IsCanceled;

    public boolean isCanceled() {
        return IsCanceled;
    }

    public void setCanceled(boolean canceled) {
        IsCanceled = canceled;
    }
}
