package main.java.com.company.AccountType;

import main.java.com.company.Utilities.DateProvider;
import main.java.com.company.Utilities.EnumAccountType;

public abstract class AccountReference {
    private double Money;
    private int Id;
    private EnumAccountType AccType;
    private String ClientId;
    private double TrustLimit;
    private boolean Trustful;

    public void setTrustful(boolean trustful) {
        Trustful = trustful;
    }

    public void setMoney(double money) {
        Money = money;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public void setAccType(EnumAccountType accType) {
        AccType = accType;
    }

    public String getClientId() {
        return ClientId;
    }

    public EnumAccountType getAccType() {
        return AccType;
    }

    public boolean isTrustful() {
        return Trustful;
    }

    public double getTrustLimit() {
        return TrustLimit;
    }

    public void setTrustLimit(double trustLimit) {
        TrustLimit = trustLimit;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public double getMoney() {
        return Money;
    }

    public abstract void ReNew(DateProvider today);
    public abstract void Replenish(double amount);
    public abstract boolean Withdraw(double amount, DateProvider today);
    public boolean Withdraw(double amount) // In Case of Cancelation
    {
        if (Money >= amount)
        {
            Money -= amount;
            return true;
        }
        else
        {
            return false;
        }
    }
}
