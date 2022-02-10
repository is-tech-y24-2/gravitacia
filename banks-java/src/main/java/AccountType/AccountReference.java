package AccountType;

import Utilities.DateProvider;
import Utilities.EnumAccountType;

public abstract class AccountReference {
    public double Money;
    public int Id;
    public EnumAccountType AccType;
    public String ClientId;
    public double TrustLimit;
    public boolean Trustful;

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
